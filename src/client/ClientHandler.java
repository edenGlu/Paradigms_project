package client;

import problem.ProblemCreator;
import solution.Solver;

import java.io.*;
import java.net.Socket;
import java.util.Vector;
import java.util.logging.Logger;

public class ClientHandler<P, S> implements IClientHandler {
    final private Solver<P, S>      solver;          // required
    final private ProblemCreator<P> pCreator;        // required
    private Logger                  logger;
    private String                  unsolvedMsg;

    private ClientHandler(CHBuilder<P, S> builder) {
        this.solver = builder.solver;
        this.pCreator = builder.pCreator;
        this.logger = builder.logger;
        this.unsolvedMsg = builder.unsolvedMsg.isEmpty() ? "Couldn't generate solution" : builder.unsolvedMsg;
    }

    @Override
    public void handleClient(Socket client) {
        System.out.println("Handling the client...");
        String line, solution;
        Vector<String> asString = new Vector<>();   // problem representation as a String vector

        try (BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
             DataOutputStream out = new DataOutputStream(client.getOutputStream())) {

            while (!(line = in.readLine()).equals("end")) {
                asString.add(line);
            }

            solution = solver.solve(pCreator.create(asString)).toString();

            out.writeBytes(!solution.isEmpty() ? solution : this.unsolvedMsg);
            if (logger != null)
                logAfterAnswer(solution, client.getRemoteSocketAddress().toString());

            client.close();
        } catch (IOException e) {
            if (logger != null)
                logger.warning(e.toString());
            else
                e.printStackTrace();
        }
    }

    private void logAfterAnswer(String solution, String clientAddress) {
        if (!solution.isEmpty())
            logger.info("Answer found & sent to client with address: " + clientAddress);
        else
            logger.warning("Answer didn't found. client address: " + clientAddress);

        logger.info("Closing Connection");
    }

    public static class CHBuilder<P, S> {
        final Solver<P, S>      solver;
        final ProblemCreator<P> pCreator;
        private Logger          logger;
        private String          unsolvedMsg;

        public CHBuilder(Solver<P, S> s, ProblemCreator<P> pc) {
            this.solver = s;
            this.pCreator = pc;
        }

        public CHBuilder<P, S> setUnsolvedMsg(String message) {
            this.unsolvedMsg = message;
            return this;
        }

        public CHBuilder<P, S> setLogger(Logger l){
            this.logger = l;
            return this;
        }

        public ClientHandler<P, S> build() {
            return new ClientHandler<>(this);
        }
    }
}
