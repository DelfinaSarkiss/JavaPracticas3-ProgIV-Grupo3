package main;

import java.util.ArrayList;

import dao.DaoCategoria;
import dao.DaoProducto;
import entidad.Categoria;
import entidad.Producto;

public class Principal {

    public static void main(String[] args) {
        DaoCategoria daoCategoria = new DaoCategoria();
        DaoProducto daoProducto = new DaoProducto();
        String identificador = String.valueOf(System.currentTimeMillis());

        probarAbmlCategorias(daoCategoria, identificador);

        ArrayList<Categoria> categorias = cargarDiezCategorias(daoCategoria, identificador);
        if (categorias.size() != 10) {
            System.out.println("No se pudieron cargar las 10 categorias.");
            return;
        }

        ArrayList<Producto> productos = cargarDiezProductos(daoProducto, categorias, identificador);
        probarAbmlProductos(daoProducto, productos);
    }

    private static void probarAbmlCategorias(DaoCategoria daoCategoria, String identificador) {
        System.out.println("===== ABML DE CATEGORIAS =====");

        Categoria categoria = new Categoria();
        categoria.setNombre("Categoria prueba " + identificador);
        int filas = daoCategoria.agregarCategoria(categoria);
        System.out.println(filas == 1 ? "Alta correcta." : "No se pudo realizar el alta.");

        categoria = buscarCategoria(
                daoCategoria.obtenerTodasLasCategorias(),
                categoria.getNombre()
        );

        if (categoria == null) {
            System.out.println("No se encontro la categoria de prueba.");
            return;
        }

        System.out.println("Listado: " + categoria);

        categoria.setNombre("Categoria modificada " + identificador);
        filas = daoCategoria.modificarCategoria(categoria);
        System.out.println(filas == 1 ? "Modificacion correcta." : "No se pudo modificar.");

        filas = daoCategoria.eliminarCategoria(categoria.getId());
        System.out.println(filas == 1 ? "Baja correcta." : "No se pudo realizar la baja.");
    }

    private static ArrayList<Categoria> cargarDiezCategorias(DaoCategoria daoCategoria,
                                                              String identificador) {
        System.out.println("\n===== CARGA DE 10 CATEGORIAS =====");
        ArrayList<String> nombres = new ArrayList<String>();

        for (int i = 1; i <= 10; i++) {
            String nombre = "Categoria " + i + " - " + identificador;
            nombres.add(nombre);

            Categoria categoria = new Categoria();
            categoria.setNombre(nombre);
            daoCategoria.agregarCategoria(categoria);
        }

        ArrayList<Categoria> categoriasCargadas = new ArrayList<Categoria>();
        ArrayList<Categoria> todasLasCategorias = daoCategoria.obtenerTodasLasCategorias();

        for (String nombre : nombres) {
            Categoria categoria = buscarCategoria(todasLasCategorias, nombre);
            if (categoria != null) {
                categoriasCargadas.add(categoria);
            }
        }

        System.out.println("Categorias cargadas: " + categoriasCargadas.size() + " de 10.");
        return categoriasCargadas;
    }

    private static ArrayList<Producto> cargarDiezProductos(DaoProducto daoProducto,
                                                            ArrayList<Categoria> categorias,
                                                            String identificador) {
        System.out.println("\n===== CARGA DE 10 PRODUCTOS =====");
        ArrayList<Producto> productos = new ArrayList<Producto>();

        for (int i = 1; i <= 10; i++) {
            Producto producto = new Producto(
                    "P" + identificador + i,
                    "Producto " + i,
                    100.00 * i,
                    10 * i,
                    categorias.get(i - 1).getId()
            );

            int filas;
            if (i == 10) {
                filas = daoProducto.agregarProductoConProcedimiento(producto);
                System.out.println("Producto 10 cargado mediante sp_AgregarProducto.");
            } else {
                filas = daoProducto.agregarProducto(producto);
            }

            if (filas == 1) {
                productos.add(producto);
            }
        }

        System.out.println("Productos cargados: " + productos.size() + " de 10.");
        return productos;
    }

    private static void probarAbmlProductos(DaoProducto daoProducto,
                                             ArrayList<Producto> productos) {
        System.out.println("\n===== LISTADO DE PRODUCTOS =====");
        for (Producto producto : daoProducto.obtenerTodosLosProductos()) {
            System.out.println(producto);
        }

        if (productos.isEmpty()) {
            System.out.println("No hay productos para probar modificacion y baja.");
            return;
        }

        Producto productoModificar = productos.get(0);
        productoModificar.setNombre("Producto modificado");
        productoModificar.setPrecio(999.99);
        productoModificar.setStock(99);
        int filas = daoProducto.modificarProducto(productoModificar);
        System.out.println(filas == 1
                ? "Modificacion de producto correcta."
                : "No se pudo modificar el producto.");

        Producto productoEliminar = productos.get(productos.size() - 1);
        filas = daoProducto.eliminarProducto(productoEliminar.getCodigo());
        System.out.println(filas == 1
                ? "Baja de producto correcta."
                : "No se pudo eliminar el producto.");

        System.out.println("\n===== LISTADO FINAL DE PRODUCTOS =====");
        for (Producto producto : daoProducto.obtenerTodosLosProductos()) {
            System.out.println(producto);
        }
    }

    private static Categoria buscarCategoria(ArrayList<Categoria> categorias, String nombre) {
        for (Categoria categoria : categorias) {
            if (categoria.getNombre().equals(nombre)) {
                return categoria;
            }
        }
        return null;
    }
}
