package hashMap;
import java.util.ArrayList;

public class FlexibleHashMap<K,V> extends AbstractHashMap<K,V> {
    protected int totlaCollisions = 0;
    protected int totalProbes = 0;
    private MapEntry<K,V>[ ] table; // a fixed array of entries (all initially null)
    private MapEntry<K,V> DEFUNCT = new MapEntry<>(null, null); //sentinel
    public FlexibleHashMap( ) { super( ); }
    public FlexibleHashMap(int cap) { super(cap); }
    public FlexibleHashMap(int cap, int p) { super(cap, p); }
    /** Creates an empty table having length equal to current capacity. */
    protected void createTable( ) {
        table = (MapEntry<K,V>[ ]) new MapEntry[capacity]; // safe cast
    }
    /** Returns true if location is either empty or the ”defunct” sentinel. */
    private boolean isAvailable(int j) {
    return (table[j] == null || table[j] == DEFUNCT);
    }

    /** Returns index with key k, or −(a+1) such that k could be added at index a. */
    private int findSlot(int h, K k) {

        if (colHanType == 'L') { // linear probing open addressing
            int avail = -1; // no slot available (thus far)
            int j = h; // index while scanning table
            do {
                if (isAvailable(j)) { // may be either empty or defunct
                    if (avail == -1) avail = j; // this is the first available slot!
                    if (table[j] == null) break; // if empty, search fails immediately
                } else if (table[j].getKey( ).equals(k))
                    return j; // successful match
                j = (j+1) % capacity; // keep looking (cyclically)
                totalProbes++;
                } while (j != h); // stop if we return to the start
            return -(avail + 1); // search has failed

        } else { // double hashing open addressing
            int h_prime = 67 - (Integer.parseInt((String) k) % 67); // secondary hash function
            int i = 0;
            int avail = -1; // no slot available (thus far)
            int j = h; // index while scanning table
            do {
                i ++;
                if (isAvailable(j)) { // may be either empty or defunct
                    if (avail == -1) avail = j; // this is the first available slot!
                    if (table[j] == null) break; // if empty, search fails immediately
                } else if (table[j].getKey( ).equals(k))
                    return j; // successful match
                // j = (j+1) % capacity; // keep looking (cyclically)
                j = (h + (i * h_prime)) % capacity; // keep looking (doublehash)
                totalProbes++;
                } while (j != h); // stop if we return to the start
            return -(avail + 1); // search has failed
        }
    }
        
    /** Returns value associated with key k in bucket with hash value h, or else null. */
    protected V bucketGet(int h, K k) {
        //totalProbes += probeCounter(k);
        int j = findSlot(h, k);
        if (j < 0) return null; // no match found
        return table[j].getValue( );   
    }
    /** Associates key k with value v in bucket with hash value h; returns old value. */
    protected V bucketPut(int h, K k, V v) {
        if (!isAvailable(h)) totlaCollisions++;
        //totalProbes += probeCounter(k);
        int j = findSlot(h, k);
        if (j >= 0) // this key has an existing entry
            return table[j].setValue(v);
        table[-(j+1)] = new MapEntry<>(k, v); // convert to proper index
        n++;
        return null;
    }

    /** Removes entry having key k from bucket with hash value h (if any). */
    protected V bucketRemove(int h, K k) {
        //totalProbes += probeCounter(k);
        int j = findSlot(h, k);
        if (j < 0) return null; // nothing to remove
        V answer = table[j].getValue( );
        table[j] = DEFUNCT; // mark this slot as deactivated
        n--;
        return answer;
    }
    /** Returns an iterable collection of all key-value entries of the map. */
    public Iterable<Entry<K,V>> entrySet( ) {
        ArrayList<Entry<K,V>> buffer = new ArrayList<>( );
        for (int h=0; h < capacity; h++)
            if (!isAvailable(h)) buffer.add(table[h]);
        return buffer;
    }


    /** returns the map as a list so it can be printed */
    public ArrayList<ArrayList<String>> toList() {
        ArrayList<ArrayList<String>> buffer = new ArrayList<>(n);
        ArrayList<String> entry = new ArrayList<>(10);
        for (Entry<K,V> e : entrySet( )){
            entry.add(e.getKey().toString());
            entry.add(e.getValue().toString());
            buffer.add(entry);
            entry.clear();
        }
        return buffer;
    }

    /** return the total number of collisions in the map */
    public int totalCollisionCounter() {
        return totlaCollisions;
    }

    /** return the total number of probes in the map */
    public int totalProbeCounter() {
        return totalProbes;
    }
    @Override
    public char collisionHandlingType() {
        return colHanType;
    }
    @Override
    public double rehashFactor() {
        return rehashFactor;
    }
    @Override
    public double RehashThreshold() {
        return loadFactor;
    }

}
