package model.dao;

import java.util.List;

import model.entities.Supplier;

public interface SupplierDao {
	
	void insert(Supplier sup);
	void update(Supplier sup);
	void deleteById(Integer id);
	Supplier findById(Integer id);
	List<Supplier> findAll();

}
