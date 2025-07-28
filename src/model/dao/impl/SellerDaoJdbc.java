package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJdbc implements SellerDao {
	
	private Connection conn;
	
	public SellerDaoJdbc(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Seller seller) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Seller seller) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Seller findById(Integer id) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			
			ps = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName\n" + "FROM seller INNER JOIN department\n"
							+ "ON seller.DepartmentId = department.Id\n" + "WHERE seller.Id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				
				Department department = instantiateDepartment(rs);
				
				Seller seller = instantiateSeller(rs, department);
				
				return seller;
				
			}

			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			DB.closeConnection();
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
		
		return null;

	}

	private Seller instantiateSeller(ResultSet rs, Department department) throws SQLException {
		
		Seller seller = new Seller();
		seller.setId(rs.getInt("Id"));
		seller.setName(rs.getString("Name"));
		seller.setEmail(rs.getString("email"));
		seller.setBirthDate(rs.getDate("BirthDate").toLocalDate());
		seller.setBaseSalary(rs.getDouble("BaseSalary"));
		seller.setDepartment(department);
		
		return seller;
		
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department department = new Department();
		department.setId(rs.getInt("DepartmentId"));
		department.setName(rs.getString("DepName"));
		
		return department;
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
