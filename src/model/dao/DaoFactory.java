package model.dao;

import db.DB;
import model.dao.impl.ProductDaoJDBC;

public class DaoFactory {

	public static ProductDao createProductDao() {
		return new ProductDaoJDBC(DB.getConnection());
	}
	
}
