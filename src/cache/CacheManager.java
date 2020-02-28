package cache;

import java.util.Vector;

public interface CacheManager {
    void save(Vector<String> problem, String solution);

    String load(Vector<String> problem);

    boolean isExist(Vector<String> problem);
}
