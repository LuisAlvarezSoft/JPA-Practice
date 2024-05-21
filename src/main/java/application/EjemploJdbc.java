package application;

import model.Categoria;
import model.Producto;
import services.Servicio;
import services.impl.CatalogoServicio;

import java.sql.SQLException;
import java.util.Date;

public class EjemploJdbc {
    public static void main(String[] args) throws SQLException {
        Servicio servicio = new CatalogoServicio();
        System.out.println("============= listar =============");
        servicio.listar().forEach(System.out::println);
        Categoria categoria = new Categoria();
        categoria.setCategoria("Iluminación");
        Producto producto = new Producto();
        producto.setNombre("Lámpara led escritorio");
        producto.setPrecio(990);
        producto.setFecha_registro(new Date());
        producto.setSku("abcdefgh12");
        servicio.guardarProductoConCategoria(producto, categoria);
        System.out.println("Producto guardado con éxito: " +
                producto.getId());
        servicio.listar().forEach(System.out::println);
    }

}

