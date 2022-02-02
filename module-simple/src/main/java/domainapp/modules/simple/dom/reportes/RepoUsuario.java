package domainapp.modules.simple.dom.reportes;

public class RepoUsuario {
    private String dni;
    private String apellido;
    private String nombre;
    private String direccion;
    private String telefono;

    public RepoUsuario(String dni, String apellido, String nombre, String direccion, String telefono){
        this.dni = dni;
        this.apellido = apellido;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public RepoUsuario(){}

    public String getDni(){ return this.dni;}

    public String getApellido() { return this.apellido.toUpperCase();}

    public String getNombre(){ return this.nombre.substring(0,1).toUpperCase()+this.nombre.substring(1).toLowerCase();}

    public String getDireccion() {return this.direccion;}

    public String getTelefono() { return this.telefono;}
}
