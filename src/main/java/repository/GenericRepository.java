package repository;

import java.sql.SQLException;
import java.util.List;

public interface GenericRepository<T,ID> {

    void save(T t) throws SQLException, ClassNotFoundException;
    List<T> findAll() throws SQLException, ClassNotFoundException;
    void update(T t) throws SQLException, ClassNotFoundException;
    void delete(ID id) throws SQLException, ClassNotFoundException;
    T getById(ID id) throws SQLException, ClassNotFoundException;

}
