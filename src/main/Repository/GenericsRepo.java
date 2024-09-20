package main.Repository;

import java.util.List;
import java.util.Optional;

public interface GenericsRepo<T> {
    T create(T entity);
    Optional<T> findById(int id);
    List<T> findAll();
    T update(T entity);
    void deleteById(int id);
}
