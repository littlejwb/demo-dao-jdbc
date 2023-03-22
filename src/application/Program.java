package application;

import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.ProductDao;
import model.entities.Product;
import model.entities.Supplier;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		ProductDao productDao = DaoFactory.createProductDao();
		
		System.out.println("- TEST 1: product findById -");
		Product prod = productDao.findById(3);
		System.out.println(prod);
		
		System.out.println("\n- TEST 2: product findBySupplier -");
		Supplier sup = new Supplier(1, null);
		List<Product> list = productDao.findBySupplier(sup);
		for(Product obj : list) {
			System.out.println(obj);
		}
		
		System.out.println("\n- TEST 3: product findAll -");
		list = productDao.findAll();
		for(Product obj : list) {
			System.out.println(obj);
		}
		
		System.out.println("\n- TEST 4: product insert -");
		//Product newProd = new Product(null, "Toluene", 12.5, "Solvent", sup);
		//productDao.insert(newProd);
		//System.out.println("Inserted! New id = " + newProd.getId());
		
		System.out.println("\n- TEST 5: product update -");
		prod = productDao.findById(1);
		prod.setName("Alcohol 70");
		productDao.update(prod);
		System.out.println("Update completed!");
		
		System.out.println("\n- TEST 6: product delete -");
		System.out.print("Enter id for delete test: ");
		int id = sc.nextInt();
		productDao.deleteById(id);
		System.out.print("Delete completed.");
		
		sc.close();
	}
}
