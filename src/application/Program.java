package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;

public class Program {

	public static void main(String[] args) {
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		System.out.println("=== Test 1: Seller findById ===");
		System.out.println(sellerDao.findById(2));
		
		System.out.println("=== Test 2: Seller findByDepartment ===");
		Department department = new Department(2, null);
		sellerDao.findByDepartment(department).forEach(System.out::println);

	}

}
