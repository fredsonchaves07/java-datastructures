package com.fredson.datastructures.map;

import com.fredson.datastructures.DataStructure;
import com.fredson.datastructures.iterator.Iterator;
import com.fredson.datastructures.list.List;
import com.fredson.models.KeyValue;

public interface Map<E extends Comparable<E>, T extends Comparable<T>> extends DataStructure<E>, Comparable<Map<E, T>> {

    void set(E key, T value);

    void remove(E key);

    boolean hasKey(E key);

    T get(E key);

    void clear();

    List<T> values();

    List<E> keys();

    List<KeyValue<E, T>> keysValues();

    Iterator<KeyValue<E, T>> forEach();
}
