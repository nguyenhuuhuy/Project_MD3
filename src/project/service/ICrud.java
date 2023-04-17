package project.service;

import java.util.List;

public interface ICrud<T> {
    List<T> findAll();
    void save(T t);
    T findById(int id);
    void deleteById(int id);
}
