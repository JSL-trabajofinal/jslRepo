package domainapp.modules.impl.reclamos.fixture;

import javax.inject.Inject;

import org.apache.isis.applib.fixturescripts.FixtureScript;
import org.apache.isis.applib.services.jdosupport.IsisJdoSupport;

import domainapp.modules.impl.reclamos.dom.Usuario;
import domainapp.modules.impl.reclamos.dom.Usuarios;
import domainapp.modules.impl.reclamos.dom.Reclamo;
import domainapp.modules.impl.reclamos.dom.TipoDeReclamo;
import lombok.Data;

public class RecreateUsuariosyReclamos extends FixtureScript {

    public RecreateUsuariosyReclamos() {
        super(null, null, Discoverability.DISCOVERABLE);
    }

    @Data
    static class ReclamoData {
        private final String name;
        private final TipoDeReclamo tipoDeReclamo;
    }

    @Override
    protected void execute(final ExecutionContext ec) {

        isisJdoSupport.deleteAll(Reclamo.class);
        isisJdoSupport.deleteAll(Usuario.class);
        ec.addResult(this, this.createUsuario("Parker", "Peter", "2994277455", null));
        ec.addResult(this, this.createUsuario("Gonzalez", "Mariano", "11452542", new ReclamoData("Aguas", TipoDeReclamo.CorteDeAgua)));
    }



    private Usuario createUsuario(
            final String lastName,
            final String firstName,
            final String phoneNumber,
            final ReclamoData... reclamos) {
        Usuario usuario = this.usuarios.create(lastName, firstName, phoneNumber);
        for (ReclamoData reclamo : reclamos) {
            usuario.newReclamo(reclamo.name, reclamo.tipoDeReclamo);
        }
        return usuario;
    }

    @Inject
    Usuarios usuarios;
    @Inject
    IsisJdoSupport isisJdoSupport;
}
