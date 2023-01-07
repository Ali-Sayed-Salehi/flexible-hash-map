package hashMap;

import java.util.ArrayList;

public interface Map<K,V> {

    int size( );
    boolean isEmpty( );
    V get(K key); 
    V put(K key, V value);
    V remove(K key);
    Iterable<K> keySet( );
    Iterable<V> values( );
    Iterable<Entry<K,V>> entrySet( );
    ArrayList<ArrayList<String>> toList();
    //int probeCounter(K key);
    char setCollisionHandling(char type);
    double setRehashFactor(double factor);
    int tableCapacity();
    double setRehashThreshold(double loadFactor);
    double loadFactor();
    int totalCollisionCounter ();
    int totalProbeCounter();

    char collisionHandlingType();
    double rehashFactor();
    double RehashThreshold();

}
