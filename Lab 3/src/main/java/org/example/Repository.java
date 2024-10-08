package org.example;

import java.util.List;

public interface Repository <T, K>{
    boolean add(T p);
    T get(K i);
    List<T> getAll();
    boolean remove(K id);
    boolean update(T p);
}
