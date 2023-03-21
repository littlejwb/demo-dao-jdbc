package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.ProductDao;
import model.entities.Product;
import model.entities.Supplier;

public class Program {

	public static void main(String[] args) {
		
		ProductDao productDao = DaoFactory.createProductDao();
		
		System.out.println("=== TESTE 1: product findById ===");
		Product prod = productDao.findById(3);
		System.out.println(prod);
	
		System.out.println("=== TESTE 2: product findBySupplier ===");
		Supplier sup = new Supplier(1, null);
		List<Product> list = productDao.findBySupplier(sup);
		for(Product obj : list) {
			System.out.println(obj);
		}
		
		System.out.println("=== TESTE 3: product findAll ===");
		list = productDao.findAll();
		for(Product obj : list) {
			System.out.println(obj);
		}
	}
}
