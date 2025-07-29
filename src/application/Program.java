package application;

import java.time.LocalDate;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		System.out.println("=== Test 1: Seller findById ===");
		System.out.println(sellerDao.findById(2));
		
		System.out.println("=== Test 2: Seller findByDepartment ===");
		Department department = new Department(2, null);
		sellerDao.findByDepartment(department).forEach(System.out::println);
		
		System.out.println("=== Test 3: Seller findAll ===");
		sellerDao.findAll().forEach(System.out::println);
		
		System.out.println("=== Test 4: Seller insert ===");
		Seller newSeller = new Seller(null, "Greg", "greg@gmail.com", LocalDate.now(), 4000.00, department);
		sellerDao.insert(newSeller);
		System.out.println("Inserted! new id = " + newSeller.getId());
		
		System.out.println("=== Test 5: Seller update ===");
		Seller seller = sellerDao.findById(1);
		seller.setName("Martha Wayne");
		sellerDao.update(seller);
		System.out.println("Updated! seller has updated");
		
		System.out.println("=== Test 6: Seller delete ===");
		sellerDao.deleteById(2);
		System.out.println("Deleted!");

	}

}
