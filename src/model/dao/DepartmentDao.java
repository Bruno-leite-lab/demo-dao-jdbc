package model.dao;

import model.entities.Department;

import java.util.List;

public interface DepartmentDao {
    void insert(Department departamento);
    void update(Department departamento);
    void deleteById(Integer id);
    Department findById(Integer id);
    List<Department> findAll();
}
