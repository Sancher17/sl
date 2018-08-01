package repositories;

import java.util.List;

public interface IRepository<T>  {

    void add(T obj);

    <T> T getById(int id);

    void deleteById(int id);

    void deleteAll(List list);
}
