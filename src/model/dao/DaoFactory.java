package model.dao;

import model.dao.impl.ProductDaoJDBC;

public class DaoFactory {

	public static ProductDao createProductDao() {
		return new ProductDaoJDBC();
	}
	
}
