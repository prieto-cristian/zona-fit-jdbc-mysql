package zona_fit.datos;

import zona_fit.conexion.Conexion;
import zona_fit.dominio.Cliente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements IClienteDAO{

    @Override
    public List<Cliente> listarClientes() {
        String consultaSQL = "SELECT * FROM cliente";
        List<Cliente> listaClientes = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        try{
            ps = Conexion.getConexion().prepareStatement(consultaSQL);
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
            System.out.println("Error al consultar a la base de datos: " + e.getMessage());
        }
        return listaClientes;
    }

    @Override
    public boolean buscarClientePorId(Cliente cliente) {
        PreparedStatement ps;
        ResultSet rs;
        String consultaSQL = "SELECT * FROM cliente WHERE id = ?;";
        try{
            ps = Conexion.getConexion().prepareStatement(consultaSQL);
            ps.setInt(1, cliente.getId());
            rs = ps.executeQuery();
            if(rs.next()){
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setMembresia(rs.getInt("membresia"));
                return true;
            }
        }catch (Exception e){
            System.out.println("Error al conectarse a la DB: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean registrarCliente(Cliente cliente) {
        return false;
    }

    @Override
    public boolean modificarCliente(Cliente cliente) {
        return false;
    }

    @Override
    public boolean eliminarCliente(Cliente cliente) {
        return false;
    }

    public static void main(String[] args) {
        System.out.println("Listar Clientes");
        var clienteNegocio = new ClienteDAO();
        var listaClientes = clienteNegocio.listarClientes();
        listaClientes.forEach(System.out::println);

        // Buscar Usuario por ID
        System.out.println("Busqueda por ID");
        var clienteABuscar = new Cliente(6);
        var encontrado = clienteNegocio.buscarClientePorId(clienteABuscar);
        if(encontrado) {
            System.out.println("Encontramos al cliente: " + clienteABuscar);
        }else{
            System.out.println("No encontramos al cliente" + clienteABuscar);
        }
    }
}
