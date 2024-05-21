package services.impl;

import config.ConexionBaseDatos;
import model.Categoria;
import model.Producto;
import repository.Repositorio;
import repository.impl.CategoriaRepositorioImpl;
import repository.impl.ProductoRepositorioImpl;
import services.Servicio;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CatalogoServicio implements Servicio {
    private Repositorio<Producto> productoRepositorio;
    private Repositorio<Categoria> categoriaRepositorio;
    public CatalogoServicio() {
        this.productoRepositorio = new ProductoRepositorioImpl();
        this.categoriaRepositorio = new CategoriaRepositorioImpl();
    }
    @Override
    public List<Producto> listar() throws SQLException {
        try (Connection conn = ConexionBaseDatos.getInstance()) {
            productoRepositorio.setConn(conn);
            return productoRepositorio.listar();
        }
    }
    @Override
    public Producto porId(int id) throws SQLException {
        try (Connection conn = ConexionBaseDatos.getInstance()) {
            productoRepositorio.setConn(conn);
            return productoRepositorio.porId(id);
        }
    }
    @Override
    public Producto guardar(Producto producto) throws SQLException {
        try (Connection conn = ConexionBaseDatos.getInstance()) {
            productoRepositorio.setConn(conn);
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            Producto nuevoProducto = null;
            try {
                nuevoProducto = productoRepositorio.guardar(producto);
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
            }
            return nuevoProducto;
        }
    }
    @Override
    public void eliminar(int id) throws SQLException {
        try (Connection conn = ConexionBaseDatos.getInstance()) {
            productoRepositorio.setConn(conn);
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            try {
                productoRepositorio.eliminar(id);
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
            }
        }
    }
    @Override
    public List<Categoria> listarCategoria() throws SQLException {
        try (Connection conn = ConexionBaseDatos.getInstance()) {
            categoriaRepositorio.setConn(conn);
            return categoriaRepositorio.listar();
        }
    }
    @Override
    public Categoria porIdCategoria(int id) throws SQLException {
        try (Connection conn = ConexionBaseDatos.getInstance()) {
            categoriaRepositorio.setConn(conn);
            return categoriaRepositorio.porId(id);
        }
    }
    @Override
    public Categoria guardarCategoria(Categoria categoria) throws SQLException
    {
        try (Connection conn = ConexionBaseDatos.getInstance()) {
            categoriaRepositorio.setConn(conn);
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            Categoria nuevaCategoria = null;
            try {
                nuevaCategoria = categoriaRepositorio.guardar(categoria);
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
            }
            return nuevaCategoria;
        }
    }
    @Override
    public void eliminarCategoria(int id) throws SQLException {
        try (Connection conn = ConexionBaseDatos.getInstance()) {
            categoriaRepositorio.setConn(conn);
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            try {
                categoriaRepositorio.eliminar(id);
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
            }
        }
    }
    @Override
    public void guardarProductoConCategoria(Producto producto, Categoria
            categoria) throws SQLException {
        try (Connection conn = ConexionBaseDatos.getInstance()) {
            productoRepositorio.setConn(conn);
            categoriaRepositorio.setConn(conn);
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            try {
                Categoria nuevaCategoria = categoriaRepositorio.guardar(categoria);
                producto.setCategoria(nuevaCategoria);
                productoRepositorio.guardar(producto);
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
            }
        }

    }
}
