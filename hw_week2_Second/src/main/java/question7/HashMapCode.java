package question7;

import java.util.ArrayList;
import java.util.List;

public class HashMapCode {
    private int[] keys;
    private Object[] values;
    private int size;

    public HashMapCode() {
        keys = new int[50];
        values = new Object[50];
        size = 0;
    }

    public void put(int key, Object value) {
        int index = getIndex(key);
        keys[index] = key;
        values[index] = value;
        size++;
    }

    public boolean containsKey(int key) {
        for (int i = 0; i < size; i++) {
            if (keys[i] == key) {
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Object[] getAllValues() {
        Object[] allValues = new Object[size];
        for (int i = 0; i < size; i++) {
            allValues[i] = values[i];
        }
        return allValues;
    }

    public void replace(int key, Object newValue) {
        int index = getIndex(key);
        values[index] = newValue;
    }

    private int getIndex(int key) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (keys[i] == key) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            index = size;
        }
        return index;
    }


}