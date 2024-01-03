// EvictionPolicy.java
public enum EvictionPolicy {
    FIRST_IN_FIRST_OUT,
    LAST_IN_FIRST_OUT,
    LEAST_RECENTLY_USED,
}

// MemoryCache.java
package academy.pocu.comp2500.lab4;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class MemoryCache {
    private static final HashMap<String, MemoryCache> DISK = new HashMap<>();
    private static final Queue<String> DISK_USE_LOG = new LinkedList<>();
    private static int maxInstanceCount;
    private EvictionPolicy evictionPolicy = EvictionPolicy.LEAST_RECENTLY_USED;
    private HashMap<String, String> cache = new HashMap<>();
    private final Deque<String> cacheCreatedLog = new LinkedList<>();
    private final Queue<String> cacheUseLog = new LinkedList<>();
    private int maxEntryCount;

    private MemoryCache() {
    }

    public static MemoryCache getInstance(String diskName) {
        if (!DISK.containsKey(diskName)) {
            DISK.put(diskName, new MemoryCache());
        } else {
            DISK_USE_LOG.remove(diskName);
        }
        DISK_USE_LOG.add(diskName);

        if (maxInstanceCount != 0 && maxInstanceCount < DISK.size()) {
            DISK.remove(DISK_USE_LOG.remove());
        }

        return DISK.get(diskName);
    }

    public static void clear() {
        DISK.clear();
        DISK_USE_LOG.clear();
    }

    public static void setMaxInstanceCount(int count) {
        maxInstanceCount = count;
        if (maxInstanceCount >= DISK.size()) {
            return;
        }
        while (maxInstanceCount < DISK.size()) {
            DISK.remove(DISK_USE_LOG.remove());
        }
    }

    public void setEvictionPolicy(EvictionPolicy evictionPolicy) {
        this.evictionPolicy = evictionPolicy;
    }

    private void removeCache() {
        String log = null;
        switch (evictionPolicy) {
            case FIRST_IN_FIRST_OUT:
                log = cacheCreatedLog.removeFirst();
                cacheUseLog.remove(log);
                break;
            case LAST_IN_FIRST_OUT:
                log = cacheCreatedLog.removeLast();
                cacheUseLog.remove(log);
                break;
            case LEAST_RECENTLY_USED:
                log = cacheUseLog.remove();
                cacheCreatedLog.remove(log);
                break;
            default:
                assert false;
        }
        cache.remove(log);
    }

    public void addEntry(String key, String value) {
        if (!cache.containsKey(key) && maxEntryCount != 0 && maxEntryCount == cache.size()) {
            removeCache();
        }

        if (cache.containsKey(key)) {
            cacheUseLog.remove(key);
        } else {
            cacheCreatedLog.addLast(key);
        }
        cacheUseLog.add(key);
        cache.put(key, value);
    }

    public String getEntryOrNull(String key) {
        if (cache.containsKey(key)) {
            cacheUseLog.remove(key);
            cacheUseLog.add(key);
            return cache.get(key);
        }
        return null;
    }

    public void setMaxEntryCount(int count) {
        maxEntryCount = count;
        while (maxEntryCount < cache.size()) {
            removeCache();
        }
    }
}