package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.SupplierDao;
import model.entities.Supplier;

public class Program2 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		SupplierDao supplierDao = DaoFactory.createSupplierDao();
		
		System.out.println("- TEST 1: supplier findById -");
		Supplier sup = supplierDao.findById(1);
		System.out.println(sup);
		
		List<Supplier> list = new ArrayList<>();
		
		System.out.println("\n- TEST 2: supplier findAll -");
		list = supplierDao.findAll();
		for(Supplier obj : list) {
			System.out.println(obj);
		}
		
		System.out.println("\n- TEST 3: supplier insert -");
		Supplier newSup = new Supplier(null, "Quimidrol");
		supplierDao.insert(newSup);
		System.out.println("Inserted! New id = " + newSup.getId());
		
		System.out.println("\n- TEST 4: supplier update -");
		sup = supplierDao.findById(1);
		sup.setName("MCQuimica");
		supplierDao.update(sup);
		System.out.println("Update completed!");
		
		System.out.println("\n- TEST 5: product delete -");
		System.out.print("Enter id for delete test: ");
		int id = sc.nextInt();
		supplierDao.deleteById(id);
		System.out.print("Delete completed.");

		sc.close();
	}

}
