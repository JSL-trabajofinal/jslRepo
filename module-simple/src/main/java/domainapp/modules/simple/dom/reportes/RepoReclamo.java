package domainapp.modules.simple.dom.reportes;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RepoReclamo {

    private String cuil;
    private String name;
    private String telefono;
    private String email;
    private String direccion;



    public RepoReclamo(String cuil, String name, String telefono, String email, String direccion){
        this.cuil=cuil;
        this.name=name;
        this.telefono=telefono;
        this.email=email;
        this.direccion=direccion;


    }

    public RepoReclamo(){}

    public String getCuil() { return this.cuil;}



    public String getName(){
        return this.name;
    }



    public String getTelefono(){
        return this.telefono;
    }



    public String getEmail(){
        return this.email;
    }



    public String getDireccion(){
        return this.direccion;
    }


}
