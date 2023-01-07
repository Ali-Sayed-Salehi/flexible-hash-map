package hashMap;
import java.util.ArrayList;
import java.util.Random;


public abstract class AbstractHashMap<K,V> extends AbstractMap<K,V> {
    protected int n = 0; // number of entries in the map
    protected int capacity; // length of the table
    protected char colHanType = 'L'; // collision handling type
    protected double rehashFactor = 2; // the factor by which the table with grow
    protected double loadFactor = 0.5; // load factor (size/capacity)

    private int prime; // prime factor
    private long scale, shift; // the shift and scaling factors
    public AbstractHashMap(int cap, int p) {
        prime = p;
        capacity = cap;
        Random rand = new Random( );
        scale = rand.nextInt(prime-1) + 1;
        shift = rand.nextInt(prime);
        createTable( );
    }

    /** set the faactor by which table will grow */
    public double setRehashFactor(double factor) {
        rehashFactor = factor;
        return factor;
    }
    
    public AbstractHashMap(int cap) { this(cap, 109345121); } // default prime
    public AbstractHashMap( ) { this(17); } // default capacity
    // public methods
    public int size( ) { return n; }
    public V get(K key) { return bucketGet(hashValue(key), key); }
    public V remove(K key) { return bucketRemove(hashValue(key), key); }
    public V put(K key, V value) {
        V answer = bucketPut(hashValue(key), key, value);
        if (((double) n/(double) capacity) >= loadFactor) // keep load factor <= 0.5
            resize(generateNextPrime((int) Math.ceil(rehashFactor * capacity))); 
        return answer;
    }

    public char setCollisionHandling(char type) {
        colHanType = type;
        return colHanType;
    }
 
    public int tableCapacity() {
        return capacity;
    }

    public double setRehashThreshold(double loadFactor) {
        this.loadFactor = loadFactor;
        return loadFactor;
    }

    public double loadFactor() {
        return ((double) n/(double) capacity);
    }

    // private utilities
    protected int hashValue(K key) {
        return (int) ((Math.abs(key.hashCode( )*scale + shift) % prime) % capacity);
    }
    private void resize(int newCap) {
        ArrayList<Entry<K,V>> buffer = new ArrayList<>(n);
        for (Entry<K,V> e : entrySet( ))
            buffer.add(e);
        capacity = newCap;
        createTable( ); // based on updated capacity
        n = 0; // will be recomputed while reinserting entries
        for (Entry<K,V> e : buffer)
            put(e.getKey( ), e.getValue( ));
    }

    /** generates the next prime number after the given number.
     * returns the number itself if it is already a prime number.
     */
    protected int generateNextPrime(int num) {
        while (true) {
            boolean flag = false;
            for (int i = 2; i <= num / 2; ++i) {
                // condition for nonprime number
                if (num % i == 0) {
                    flag = true;
                    break;
                }
            }
            if (!flag) return num; // it is prime
            num++;
        }
    }


    // protected abstract methods to be implemented by subclasses
    protected abstract void createTable( );
    protected abstract V bucketGet(int h, K k);
    protected abstract V bucketPut(int h, K k, V v);
    protected abstract V bucketRemove(int h, K k);
}
