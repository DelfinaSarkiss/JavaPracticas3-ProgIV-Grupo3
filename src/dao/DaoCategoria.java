package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import entidad.Categoria;

public class DaoCategoria {
	
	private String host = "jdbc:mysql://localhost:3306/";
	private String user = "root";
	private String pass = "root";
	private String dbName = "bdInventario";
	
	public DaoCategoria()
	{
	}
	
	public int agregarCategoria(Categoria categoria)
	{
		String query = "Insert into Categorias(Nombre) values ('"+categoria.getNombre()+"')";
		
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
			String query = "Select * from Categorias where IdCategoria="+id;
			ResultSet rs = st.executeQuery(query);
			rs.next();
			c.setNombre(rs.getString("Nombre"));
			c.setId(rs.getInt("IdCategoria"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return c;
	}
	
	public ArrayList<Categoria> obtenerTodasLasCategorias()
	{
		ArrayList<Categoria> lCategoria = new ArrayList<Categoria>();
		Connection cn = null;
		try
		{
			cn = DriverManager.getConnection(host+dbName,user,pass);
			String query = "select * from Categorias";
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next())
			{
				Categoria c = new Categoria();
				c.setId(rs.getInt("IdCategoria"));
				c.setNombre(rs.getString("Nombre"));
				lCategoria.add(c);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return lCategoria;
	} 
	
	public int eliminarCategoria(int id)
	{
		String query = "Delete from Categorias where IdCategoria=" + id;
		
		Connection cn = null;
		int filas = 0;
		try
		{
			cn = DriverManager.getConnection(host + dbName, user, pass);
			Statement st = cn.createStatement();
			filas = st.executeUpdate(query);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return filas;
	}
	
	public int modificarCategoria(Categoria categoria)
	{
		String query = "Update Categorias set Nombre='" + categoria.getNombre() + "' where IdCategoria=" + categoria.getId();
		
		Connection cn = null;
		int filas = 0;
		try
		{
			cn = DriverManager.getConnection(host + dbName, user, pass);
			Statement st = cn.createStatement();
			filas = st.executeUpdate(query);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return filas;
	}
}
