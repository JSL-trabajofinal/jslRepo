package domainapp.modules.simple.dom.reportes;

public class RepoTecnico {

    private String dni;
    private String apellido;
    private String nombre;
    private String telefono;
    private String direccion;


    public RepoTecnico(String dni, String apellido, String nombre, String telefono, String direccion){

        this.dni = dni;
        this.apellido = apellido;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public RepoTecnico(){}

        public String getDni(){ return this.dni; }
        public String getApellido(){ return this.apellido.toUpperCase(); }
        public String getNombre(){ return this.nombre.substring(0,1).toUpperCase()+this.nombre.substring(1).toLowerCase(); }
        public String getTelefono(){ return this.telefono; }
        public String getDireccion(){ return this.direccion; }
}
