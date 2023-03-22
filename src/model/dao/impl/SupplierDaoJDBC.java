package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.SupplierDao;
import model.entities.Supplier;

public class SupplierDaoJDBC implements SupplierDao {
	
	private Connection conn;
	
	public SupplierDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Supplier sup) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO supplier "
					+ "(Name) "
					+ "VALUES (?) ",
					Statement.RETURN_GENERATED_KEYS);
					
			st.setString(1, sup.getName());
			
			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					sup.setId(id);
				}
				DB.closeResultSet(rs);
			}
			else {
				throw new DbException("Unexpected error! No rows affected");
			}
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void update(Supplier sup) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE supplier "
					+ "SET Name = ? "
					+ "WHERE Id = ?");
			
			st.setString(1, sup.getName());
			st.setInt(2, sup.getId());
			
			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"DELETE FROM supplier "
					+ "WHERE Id = ?");
			
			st.setInt(1, id);
			st.executeUpdate();
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public Supplier findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT * FROM supplier "
					+ "WHERE Id = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			if(rs.next()) {
				Supplier sup = new Supplier();
				sup.setId(rs.getInt("Id"));
				sup.setName(rs.getString("Name"));
				return sup;
			}
			return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

	@Override
	public List<Supplier> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT * FROM supplier "
					+ "ORDER BY Name");
			
			rs = st.executeQuery();
			
			List<Supplier> list = new ArrayList<>();
			
			while(rs.next()) {
				Supplier sup = new Supplier();
				sup.setId(rs.getInt("Id"));
				sup.setName(rs.getString("Name"));
				list.add(sup);
			}
			return list;		
		}
		catch( SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
	}
}
