package model.dao;

import db.DB;
import model.dao.impl.ProductDaoJDBC;
import model.dao.impl.SupplierDaoJDBC;

public class DaoFactory {

	public static ProductDao createProductDao() {
		return new ProductDaoJDBC(DB.getConnection());
	}
	
	public static SupplierDao createSupplierDao() {
		return new SupplierDaoJDBC(DB.getConnection());
	}
	
}
