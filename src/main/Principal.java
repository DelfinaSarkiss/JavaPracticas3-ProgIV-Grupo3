package main;
import java.util.ArrayList;

import dao.DaoCategoria;
import entidad.Categoria;

public class Principal {
	  public static void main(String[] args) {
	DaoCategoria daoCategoria = new DaoCategoria();
	
	System.out.println("===== ALTA DE CATEGORÍA =====");

	Categoria categoria = new Categoria();
	categoria.setNombre("Limpieza");

	int filas = daoCategoria.agregarCategoria(categoria);

	if (filas == 1) {
		System.out.println("Categoría agregada correctamente.");
	} else {
		System.out.println("No se pudo agregar la categoría.");
	}
	System.out.println("\n===== LISTADO DE CATEGORÍAS =====");

	ArrayList<Categoria> categorias = daoCategoria.obtenerTodasLasCategorias();

	for (Categoria c : categorias) {
		System.out.println(c);
	}
	
	System.out.println("\n===== MODIFICACIÓN DE CATEGORÍA =====");

	if (!categorias.isEmpty()) {

		Categoria categoriaModificar = categorias.get(categorias.size() - 1);

		categoriaModificar.setNombre("Productos de Limpieza");

		filas = daoCategoria.modificarCategoria(categoriaModificar);

		if (filas == 1) {
			System.out.println("Categoría modificada correctamente.");
		} else {
			System.out.println("No se pudo modificar la categoría.");
		}
	}
	
	System.out.println("\n===== LISTADO DESPUÉS DE MODIFICAR =====");

	categorias = daoCategoria.obtenerTodasLasCategorias();

	for (Categoria c : categorias) {
		System.out.println(c);
	}



	System.out.println("\n===== BAJA DE CATEGORÍA =====");

	if (!categorias.isEmpty()) {

		Categoria categoriaEliminar = categorias.get(categorias.size() - 1);

		filas = daoCategoria.eliminarCategoria(categoriaEliminar.getId());

		if (filas == 1) {
			System.out.println("Categoría eliminada correctamente.");
		} else {
			System.out.println("No se pudo eliminar la categoría.");
		}
	}
	System.out.println("\n===== LISTADO FINAL =====");

	categorias = daoCategoria.obtenerTodasLasCategorias();

	for (Categoria c : categorias) {
		System.out.println(c);
	}
}
}
