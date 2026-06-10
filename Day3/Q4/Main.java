package Day3.Q4;
import java.util.LinkedHashMap;
import java.util.Map;

// LRU Cache Implementation
class VideoCache<K, V> extends LinkedHashMap<K, V> {

    private final int capacity;

    public VideoCache(int capacity) {

        // initialCapacity, loadFactor, accessOrder
        super(capacity, 0.75f, true);

        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {

        // Remove least recently used entry
        return size() > capacity;
    }
}

// Driver Class
public class Main {

    public static void main(String[] args) {

        VideoCache<String, String> cache = new VideoCache<>(3);

        // Add videos
        cache.put("M101", "Inception");
        cache.put("M102", "Interstellar");
        cache.put("M103", "The Dark Knight");

        System.out.println("Initial Cache:");
        System.out.println(cache);

        // Access M101 (makes it most recently used)
        cache.get("M101");

        // Adding a new movie causes eviction
        cache.put("M104", "Oppenheimer");

        System.out.println("\nAfter Accessing M101 and Adding M104:");
        System.out.println(cache);

        // Access another movie
        cache.get("M103");

        // Add another movie
        cache.put("M105", "Tenet");

        System.out.println("\nAfter Accessing M103 and Adding M105:");
        System.out.println(cache);
    }
}
