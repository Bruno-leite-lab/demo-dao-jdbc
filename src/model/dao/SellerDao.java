package model.dao;

import model.entities.Department;
import model.entities.Seller;

import java.util.List;

public interface SellerDao {
    void insert(Seller departamento);
    void update(Seller departamento);
    void deleteById(Integer id);
    Department findById(Integer id);
    List<Seller> findAll();
}
