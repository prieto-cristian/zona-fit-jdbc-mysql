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
    public boolean buscarClientePorId(int id) {
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
    }
}
