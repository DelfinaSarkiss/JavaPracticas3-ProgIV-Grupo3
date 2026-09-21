package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

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
}
