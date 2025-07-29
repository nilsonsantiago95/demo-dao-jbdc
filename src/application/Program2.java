package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {
		
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		
		System.out.println("=== Test 1: Department findById ===");
		System.out.println(departmentDao.findById(1));
		
		System.out.println("=== Test 2: Department findAll ===");
		departmentDao.findAll().forEach(System.out::println);
		
////		System.out.println("=== Test 3: Department insert ===");
////		Department newDepartment = new Department(null, "Foods");
////		departmentDao.insert(newDepartment);
////		System.out.println("Insert completed!");
//		
////		System.out.println("=== Test 4: Department update ===");
////		Department department = departmentDao.findById(7);
////		department.setName("Vehicles");
////		departmentDao.update(department);
////		System.out.println("update completed!");
		
		System.out.println("=== Test 5: Department delete ===");
		Department department = departmentDao.findById(9);
		departmentDao.deleteById(department.getId());
		System.out.println("delete completed");

	}

}
