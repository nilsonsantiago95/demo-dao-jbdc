package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJdbc implements DepartmentDao {

	private Connection conn;

	public DepartmentDaoJdbc(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Department department) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			ps = conn.prepareStatement("INSERT INTO department (Name) VALUES (?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, department.getName());

			int rowsAffeted = ps.executeUpdate();

			if (rowsAffeted <= 0) {
				throw new DbException("Unexpectd error! insert failed");
			}

			rs = ps.getGeneratedKeys();

			if (rs.next()) {
				int id = rs.getInt(1);
				department.setId(id);
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public void update(Department department) {

		PreparedStatement ps = null;

		try {

			ps = conn.prepareStatement("UPDATE department SET name = ? WHERE id = ?");
			ps.setString(1, department.getName());
			ps.setInt(2, department.getId());

			int rows = ps.executeUpdate();

			if (rows <= 0) {
				throw new DbException("Unexpected error: update failed!");
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}

	}

	@Override
	public void deleteById(Integer id) {

		PreparedStatement ps = null;

		try {

			ps = conn.prepareStatement("DELETE FROM department WHERE id = ?");
			ps.setInt(1, id);
			int rows = ps.executeUpdate();
			
			if(rows <= 0) {
				throw new DbException("Unexpected error: delete failed");
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}

	}

	@Override
	public Department findById(Integer id) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			ps = conn.prepareStatement("SELECT * FROM department WHERE id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				return new Department(rs.getInt("Id"), rs.getString("Name"));
			}

			throw new DbException("Department not found!");

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}

	}

	@Override
	public List<Department> findAll() {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			ps = conn.prepareStatement("SELECT * FROM department");
			rs = ps.executeQuery();

			List<Department> listDepartments = new ArrayList<>();

			while (rs.next()) {
				listDepartments.add(new Department(rs.getInt("Id"), rs.getString("Name")));
			}

			return listDepartments;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}

	}

}
