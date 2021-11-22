package domainapp.modules.simple.dom.reportes;

import domainapp.modules.simple.dom.cuadrilla.Cuadrilla;
import domainapp.modules.simple.dom.reclamo.TipoReclamo;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter @Setter
public class RepoReclamo {

    private BigInteger numeroReclamo;
    private String nombreCompleto;
    private String telefono;
    private String descripcion;
    private String direccion;
    private TipoReclamo tipoReclamo;
    private String cuadrilla;



    public RepoReclamo(BigInteger numeroReclamo, String nombreCompleto, String telefono, String descripcion, String direccion, TipoReclamo tipoReclamo, String cuadrilla){
        this.numeroReclamo=numeroReclamo;
        this.nombreCompleto=nombreCompleto;
        this.telefono=telefono;
        this.descripcion=descripcion;
        this.direccion=direccion;
        this.tipoReclamo=tipoReclamo;
        this.cuadrilla=cuadrilla;
    }

    public RepoReclamo(BigInteger numeroReclamo, String nombreCompleto, String telefono, String descripcion, String direccion, TipoReclamo tipoReclamo, Cuadrilla cuadrilla) {

    }

    public RepoReclamo() {

    }

    public BigInteger getNumeroReclamo() {
        return this.numeroReclamo;
    }

    public String getNombreCompleto() {
        return this.nombreCompleto;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public TipoReclamo getTipoReclamo() {
        return this.tipoReclamo;
    }

    public String getCuadrilla() {
        return this.cuadrilla;
    }
}
