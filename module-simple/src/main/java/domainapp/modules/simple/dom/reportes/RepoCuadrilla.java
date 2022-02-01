package domainapp.modules.simple.dom.reportes;

import domainapp.modules.simple.dom.ayudante.Ayudante;
import domainapp.modules.simple.dom.tecnico.Tecnico;

public class RepoCuadrilla {

    private String nombre;
    private Tecnico tecnico;
    private Ayudante ayudante;


    public RepoCuadrilla(String nombre, Tecnico tecnico, Ayudante ayudante){
        this.nombre = nombre;
        this.tecnico = tecnico;
        this.ayudante = ayudante;
    }

    public RepoCuadrilla(){}

    public String getNombre(){ return this.nombre;}

    public String getTecnico(){ return tecnico.getApellido().toUpperCase().toString()+","+tecnico.getNombre().toString();}

    public String getAyudante(){ return ayudante.getApellido().toUpperCase().toString()+","+ayudante.getNombre().toString();}
}
