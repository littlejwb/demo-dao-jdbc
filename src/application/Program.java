package application;

import model.entities.Product;
import model.entities.Supplier;

public class Program {

	public static void main(String[] args) {
		
		Supplier sup = new Supplier(1, "Braskem");
		
		Product prod = new Product(7, "Toluene", 15.5, "Solvent", sup);
		
		System.out.println(prod);
		
		
		
	}

}
