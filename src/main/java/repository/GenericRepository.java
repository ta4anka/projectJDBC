package repository;

import java.sql.SQLException;
import java.util.List;

public interface GenericRepository<T,ID> {

    void save(T t) throws SQLException, ClassNotFoundException;
    List<T> findAll();
    void update(T t);
    void delete(ID id);
    T getById(ID id) throws SQLException, ClassNotFoundException;

}
