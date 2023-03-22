package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.ProductDao;
import model.entities.Product;
import model.entities.Supplier;

public class ProductDaoJDBC implements ProductDao {
	
	private Connection conn;
	
	public ProductDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Product prod) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO product "
					+ "(Name, Price, Section, Supplier_id) "
					+ "VALUES "
					+ "(?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, prod.getName());
			st.setDouble(2, prod.getPrice());
			st.setString(3, prod.getSection());
			st.setInt(4, prod.getSupplier().getId());
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					prod.setId(id);
				}
				DB.closeResultSet(rs);
			}
			else {
				throw new DbException("Unexpected error! No rows affected.");
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
	public void update(Product prod) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE product "
					+ "SET Name = ?, Price = ?, Section = ?, Supplier_id = ? "
					+ "WHERE Id = ?");
			
			st.setString(1, prod.getName());
			st.setDouble(2, prod.getPrice());
			st.setString(3, prod.getSection());
			st.setInt(4, prod.getSupplier().getId());
			st.setInt(5, prod.getId());
			
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public Product findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT p.Id, p.Name, p.Price, p.Section, p.Supplier_id, "
			        + "s.Name as SupName "
			        + "FROM product p INNER JOIN supplier s "
			        + "ON s.Id = p.Supplier_id "
			        + "WHERE p.Id = ?");
			
			//Instanciar um objeto product associado ao supplier
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Supplier sup = instantiateSupplier(rs);
				Product obj = instantiateProduct(rs, sup);
				return obj;
			}
			return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	private Product instantiateProduct(ResultSet rs, Supplier sup) throws SQLException {
		Product obj = new Product();
		obj.setId(rs.getInt("Id"));
		obj.setName(rs.getString("Name"));
		obj.setPrice(rs.getDouble("Price"));
		obj.setSection(rs.getString("Section"));
		obj.setSupplier(sup);
		return obj;
	}

	private Supplier instantiateSupplier(ResultSet rs) throws SQLException {
		Supplier sup = new Supplier();
		sup.setId(rs.getInt("Supplier_Id"));
		sup.setName(rs.getString("SupName"));
		return sup;
	}

	@Override
	public List<Product> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT p.Id, p.Name, p.Price, p.Section, p.Supplier_id, "
			        + "s.Name as SupName "
			        + "FROM product p INNER JOIN supplier s "
			        + "ON s.Id = p.Supplier_id "
			        + "ORDER BY Name");
			
			rs = st.executeQuery();
			
			List<Product> list = new ArrayList<>();
			Map<Integer, Supplier> map = new HashMap<>();
			
			while (rs.next()) {
				
				Supplier sup = map.get(rs.getInt("Supplier_id"));
				
				if (sup == null) {
					sup = instantiateSupplier(rs);
					map.put(rs.getInt("Supplier_id"), sup);
				}
				
				Product obj = instantiateProduct(rs, sup);
				list.add(obj);
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Product> findBySupplier(Supplier supplier) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT p.Id, p.Name, p.Price, p.Section, p.Supplier_id, "
			        + "s.Name as SupName "
			        + "FROM product p INNER JOIN supplier s "
			        + "ON s.Id = p.Supplier_id "
			        + "WHERE Supplier_id = ? "
			        + "ORDER BY Name");
			
			st.setInt(1, supplier.getId());
			rs = st.executeQuery();
			
			List<Product> list = new ArrayList<>();
			Map<Integer, Supplier> map = new HashMap<>();
			
			while (rs.next()) {
				
				Supplier sup = map.get(rs.getInt("Supplier_id"));
				
				if (sup == null) {
					sup = instantiateSupplier(rs);
					map.put(rs.getInt("Supplier_id"), sup);
				}
				
				Product obj = instantiateProduct(rs, sup);
				list.add(obj);
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
}
