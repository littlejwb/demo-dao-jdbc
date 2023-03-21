package application;

import model.dao.DaoFactory;
import model.dao.ProductDao;
import model.entities.Product;

public class Program {

	public static void main(String[] args) {
		
		ProductDao productDao = DaoFactory.createProductDao();
		
		System.out.println("=== TESTE 1: product findById ===");
		Product prod = productDao.findById(3);
		System.out.println(prod);
		
	}

}
