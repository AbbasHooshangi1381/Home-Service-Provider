package question7;

import java.util.ArrayList;
import java.util.List;

public class Main7<K, V> {
    private static final int SIZE = 16;
    private Entry<K, V>[] table;

    public Main7() {
        table = new Entry[SIZE];
    }

    public void put(K key, V value) {
        int hash = key.hashCode() % SIZE;
        Entry<K, V> newEntry = new Entry<>(key, value, null);

        if (table[hash] == null) {
            table[hash] = newEntry;
        } else {
            Entry<K, V> current = table[hash];
            while (current.next != null) {
                if (current.key.equals(key)) {
                    current.value = value;
                    return;
                }
                current = current.next;
            }
            if (current.key.equals(key)) {
                current.value = value;
            } else {
                current.next = newEntry;
            }
        }
    }

    public boolean containsKey(K key) {
        int hash = key.hashCode() % SIZE;
        if (table[hash] == null) {
            return false;
        } else {
            Entry<K, V> current = table[hash];
            while (current != null) {
                if (current.key.equals(key)) {
                    return true;
                }
                current = current.next;
            }
            return false;
        }
    }

    public boolean isEmpty() {
        for (int i = 0; i < SIZE; i++) {
            if (table[i] != null) {
                return false;
            }
        }
        return true;
    }

    public List<V> values() {
        List<V> values = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            Entry<K, V> current = table[i];
            while (current != null) {
                values.add(current.value);
                current = current.next;
            }
        }
        return values;
    }

    public void replace(K key, V value) {
        int hash = key.hashCode() % SIZE;
        if (table[hash] != null) {
            Entry<K, V> current = table[hash];
            while (current != null) {
                if (current.key.equals(key)) {
                    current.value = value;
                    return;
                }
                current = current.next;
            }
        }
    }

    private static class Entry<K, V> {
        private final K key;
        private V value;
        private Entry<K, V> next;

        public Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}