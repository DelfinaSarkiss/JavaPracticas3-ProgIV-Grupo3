package dao;

import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entidad.Producto;

public class DaoProducto {

	private String host = "jdbc:mysql://localhost:3306/";
	private String user = "root";
	private String pass = "root";
	private String dbName = "bdInventario";

	public DaoProducto()
	{
	}

	public int agregarProducto(Producto producto)
	{
		String query = "INSERT INTO Productos (Codigo, Nombre, Precio, Stock, IdCategoria) VALUES (?, ?, ?, ?, ?)";

		try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
			 PreparedStatement st = cn.prepareStatement(query))
		{
			st.setString(1, producto.getCodigo());
			st.setString(2, producto.getNombre());
			st.setDouble(3, producto.getPrecio());
			st.setInt(4, producto.getStock());
			st.setInt(5, producto.getIdCategoria());
			return st.executeUpdate();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}

	public int agregarProductoConProcedimiento(Producto producto)
	{
		String procedimiento = "{CALL sp_AgregarProducto(?, ?, ?, ?, ?)}";

		try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
			 CallableStatement st = cn.prepareCall(procedimiento))
		{
			st.setString(1, producto.getCodigo());
			st.setString(2, producto.getNombre());
			st.setDouble(3, producto.getPrecio());
			st.setInt(4, producto.getStock());
			st.setInt(5, producto.getIdCategoria());
			return st.executeUpdate();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}

	public int eliminarProducto(String codigo)
	{
		String query = "DELETE FROM Productos WHERE Codigo = ?";

		try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
			 PreparedStatement st = cn.prepareStatement(query))
		{
			st.setString(1, codigo);
			return st.executeUpdate();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	public int modificarProducto(Producto producto) {
		String query = "UPDATE Productos SET Nombre = ?, Precio = ?, Stock = ?, IdCategoria = ? WHERE Codigo = ?";
		
		try (Connection cn = DriverManager.getConnection(host + dbName, user, pass);
			 PreparedStatement st = cn.prepareStatement(query))
		{
			st.setString(1, producto.getNombre());
			st.setDouble(2, producto.getPrecio());
			st.setInt(3, producto.getStock());
			st.setInt(4, producto.getIdCategoria());
			st.setString(5, producto.getCodigo());
			
			return st.executeUpdate();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	
	public ArrayList<Producto> obtenerTodosLosProductos(){
		ArrayList<Producto> lista = new ArrayList<Producto>();
		String query = "SELECT * FROM Productos";
	
		try(Connection cn = DriverManager.getConnection(host + dbName, user, pass);
			PreparedStatement st = cn.prepareStatement(query);
			ResultSet rs = st.executeQuery())
		{
		while (rs.next())
		{
			Producto p = new Producto();
			p.setCodigo(rs.getString("Codigo"));
			p.setNombre(rs.getString("Nombre"));
			p.setPrecio(rs.getDouble("Precio"));
			p.setStock(rs.getInt("Stock"));
			p.setIdCategoria(rs.getInt("IdCategoria"));
			lista.add(p);
		}
	}
	catch (Exception e)
	{
		e.printStackTrace();
	}
	
	return lista;
	
}
}
