package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import entidad.Categoria;

public class DaoCategoria {
	
	private String host = "jdbc:mysql://localhost:3306/";
	private String user = "root";
	private String pass = "root";
	private String dbName = "dbInventario";
	
	public DaoCategoria()
	{
	}
	
	public int agregarCategoria(Categoria categoria)
	{
		String query = "Insert into Categoria(nombre) values ('"+categoria.getNombre()+"')";
		
		Connection cn = null;
		int filas = 0;
		try
		{
			cn = DriverManager.getConnection(host+dbName,user,pass);
			Statement st = cn.createStatement();
			filas = st.executeUpdate(query);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return filas;
	}
	
	public Categoria obtenerCategoria(int id)
	{
		Categoria c = new Categoria();
		
		Connection cn = null;
		
		try
		{
			cn = DriverManager.getConnection(host+dbName,user,pass);
			Statement st = cn.createStatement();
			String query = "Select * from Categoria where id="+id;
			ResultSet rs = st.executeQuery(query);
			rs.next();
			c.setNombre(rs.getString("nombre"));
			c.setId(rs.getInt("id"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return c;
	}
}
