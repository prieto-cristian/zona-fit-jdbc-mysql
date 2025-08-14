package zona_fit.datos;

import zona_fit.conexion.Conexion;
import zona_fit.dominio.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static zona_fit.conexion.Conexion.getConexion;

public class ClienteDAO implements IClienteDAO{

    @Override
    public List<Cliente> listarClientes() {
        String consultaSQL = "SELECT * FROM cliente";
        List<Cliente> listaClientes = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection con = Conexion.getConexion();
        try{
            ps = con.prepareStatement(consultaSQL);
            rs = ps.executeQuery();
            while(rs.next()){
                var cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setMembresia(rs.getInt("membresia"));

                listaClientes.add(cliente);
            }
        }catch (Exception e) {
            System.out.println("Error al listar clientes: " + e.getMessage());
        }finally {
            try {
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerrar conexion: " e.getMessage());
            }
        }
        return listaClientes;
    }

    @Override
    public boolean buscarClientePorId(Cliente cliente) {
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        String consultaSQL = "SELECT * FROM cliente WHERE id = ?;";
        try{
            ps = con.prepareStatement(consultaSQL);
            ps.setInt(1, cliente.getId());
            rs = ps.executeQuery();
            if(rs.next()){
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setMembresia(rs.getInt("membresia"));
                return true;
            }
        }catch (Exception e){
            System.out.println("Error al eliminar cliente: " + e.getMessage());
        }
        finally {
            try {
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerrar la conexion: " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean registrarCliente(Cliente cliente) {
        PreparedStatement ps;
        ResultSet rs;
        Connection con = Conexion.getConexion();
        String consultaSQL = "INSERT INTO cliente(nombre, apellido, membresia) " +
                "VALUES (?, ?, ?);";
        try{
            ps = con.prepareStatement(consultaSQL);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setInt(3, cliente.getMembresia());
            var resultado = ps.executeUpdate();
            return resultado == 1;
        }catch (Exception e){
            System.out.println("Error al registrar cliente: " + e.getMessage());
        }finally {
            try {
                con.close();
            }
            catch (Exception e){
                System.out.println("Error al cerrar conexion: " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean modificarCliente(Cliente cliente) {
        PreparedStatement ps;
        String consultaSQL = "UPDATE cliente SET nombre= ?, apellido= ?, membresia= ? WHERE (id = ?)";
        Connection con = getConexion();
        try{
            ps = con.prepareStatement(consultaSQL);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setInt(3, cliente.getMembresia());
            ps.setInt(4, cliente.getId());
            var resultado = ps.executeUpdate();
            return resultado == 1;
        }catch (Exception e){
            System.out.println("Error al modificar cliente: " + e.getMessage());
        }finally {
            try {
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerrar conexion: " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean eliminarCliente(Cliente cliente) {
        PreparedStatement ps;
        String consultaSQL = "DELETE FROM cliente WHERE (id = ?)";
        Connection con = Conexion.getConexion();
        try {
            ps = con.prepareStatement(consultaSQL);
            ps.setInt(1, cliente.getId());
            var resultado = ps.executeUpdate();
            return resultado == 1;
        } catch (Exception e) {
            System.out.println("Error al eliminar cliente: " + e.getMessage());
        }finally {
            try {
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerrar conexion: " + e.getMessage());
            }
        }
        return false;
    }
}
