package main;

import java.util.ArrayList;
import dao.DaoCategoria;
import dao.DaoProducto;
import entidad.Categoria;
import entidad.Producto;

public class Principal {

    public static void main(String[] args) {
        DaoCategoria daoCategoria = new DaoCategoria();

        // ==========================
        // ABML DE CATEGORÍAS
        // ==========================

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
        /*
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

        System.out.println("\n===== LISTADO FINAL CATEGORÍAS =====");

        categorias = daoCategoria.obtenerTodasLasCategorias();

        for (Categoria c : categorias) {
            System.out.println(c);
        }
		*/
        // ==========================
        // ABML DE PRODUCTOS
        // ==========================

        DaoProducto daoProducto = new DaoProducto();

        System.out.println("\n================================");
        System.out.println("        ABML DE PRODUCTOS");
        System.out.println("================================");

        // ==========================
        // OBTENER UNA CATEGORÍA
        // ==========================

        ArrayList<Categoria> categoriasDisponibles = daoCategoria.obtenerTodasLasCategorias();

        if (categoriasDisponibles.isEmpty()) {
            System.out.println("No hay categorías disponibles.");
            System.out.println("Debe existir al menos una categoría para agregar un producto.");
            return;
        }

        int idCategoria = categoriasDisponibles.get(0).getId();

        System.out.println("Categoría utilizada: " + categoriasDisponibles.get(0).getNombre());

        // ==========================
        // ALTA DE PRODUCTO
        // ==========================

        System.out.println("\n===== ALTA DE PRODUCTO =====");

        String codigo = "P" + System.currentTimeMillis();

        Producto producto = new Producto(
                codigo,
                "Shampoo",
                1500.99,
                18,
                idCategoria
        );

        int filasProducto = daoProducto.agregarProducto(producto);

        if (filasProducto == 1) {
            System.out.println("Producto agregado correctamente.");
            System.out.println(producto);
        } else {
            System.out.println("No se pudo agregar el producto.");
        }

        // ==========================
        // LISTADO DE PRODUCTOS
        // ==========================

        System.out.println("\n===== LISTADO DE PRODUCTOS =====");

        ArrayList<Producto> productos = daoProducto.obtenerTodosLosProductos();

        for (Producto p : productos) {
            System.out.println(p);
        }

        // ==========================
        // MODIFICACIÓN DE PRODUCTO
        // ==========================

        System.out.println("\n===== MODIFICACIÓN DE PRODUCTO =====");

        producto.setNombre("Shampoo Modificado");
        producto.setPrecio(1800.50);
        producto.setStock(25);

        filasProducto = daoProducto.modificarProducto(producto);

        if (filasProducto == 1) {
            System.out.println("Producto modificado correctamente.");
        } else {
            System.out.println("No se pudo modificar el producto.");
        }

        // ==========================
        // LISTADO DESPUÉS DE MODIFICAR
        // ==========================

        System.out.println("\n===== LISTADO DESPUÉS DE MODIFICAR =====");

        productos = daoProducto.obtenerTodosLosProductos();

        for (Producto p : productos) {
            System.out.println(p);
        }

        // ==========================
        // BAJA DE PRODUCTO
        // ==========================
        
        System.out.println("\n===== BAJA DE PRODUCTO =====");

        filasProducto = daoProducto.eliminarProducto(producto.getCodigo());

        if (filasProducto == 1) {
            System.out.println("Producto eliminado correctamente.");
        } else {
            System.out.println("No se pudo eliminar el producto.");
        }

        // ==========================
        // LISTADO FINAL DE PRODUCTOS
        // ==========================

        System.out.println("\n===== LISTADO FINAL PRODUCTOS =====");

        productos = daoProducto.obtenerTodosLosProductos();

        for (Producto p : productos) {
            System.out.println(p);
        }
    }
}