package services;

import model.Categoria;
import model.Producto;

import java.sql.SQLException;
import java.util.List;

public interface Servicio {
    List<Producto> listar() throws SQLException;
    Producto porId(int id) throws SQLException;
    Producto guardar(Producto producto) throws SQLException;
    void eliminar(int id) throws SQLException;
    List<Categoria> listarCategoria() throws SQLException;
    Categoria porIdCategoria(int id) throws SQLException;
    Categoria guardarCategoria(Categoria categoria) throws SQLException;
    void eliminarCategoria(int id) throws SQLException;
    void guardarProductoConCategoria(Producto producto, Categoria categoria)
            throws SQLException;

}
