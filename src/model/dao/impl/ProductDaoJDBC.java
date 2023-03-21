package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Product prod) {
		// TODO Auto-generated method stub
		
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
				Supplier sup = instantiateDepartment(rs);
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

	private Supplier instantiateDepartment(ResultSet rs) throws SQLException {
		Supplier sup = new Supplier();
		sup.setId(rs.getInt("Supplier_Id"));
		sup.setName(rs.getString("SupName"));
		return sup;
	}

	@Override
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
