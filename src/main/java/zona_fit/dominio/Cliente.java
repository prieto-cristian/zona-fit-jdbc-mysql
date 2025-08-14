package zona_fit.dominio;

import java.util.Objects;

public class Cliente {
    private int Id;
    private String nombre;
    private int membresia;
    private String apellido;

    // Se utiliza para setear los datos de la DB
    public Cliente(){}

    // Para eliminar Cliente por Id
    public Cliente(int id){
        this.Id = id;
    }

    // Se utiliza cuando se crea un Cliente desde la capa presentacion
    public Cliente(int membresia, String nombre, String apellido){
        this.membresia = membresia;
        this.apellido = apellido;
        this.nombre = nombre;
    }

    // Se utiliza para modificar un Cliente
    public Cliente(int id, int membresia, String nombre, String apellido){
        this(membresia, nombre, apellido);
        this.Id = id;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getMembresia() {
        return membresia;
    }

    public void setMembresia(int membresia) {
        this.membresia = membresia;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Id == cliente.Id && membresia == cliente.membresia && Objects.equals(nombre, cliente.nombre) && Objects.equals(apellido, cliente.apellido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, nombre, membresia, apellido);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "Id=" + Id +
                ", nombre='" + nombre + '\'' +
                ", membresia=" + membresia +
                ", apellido='" + apellido + '\'' +
                '}';
    }
}
