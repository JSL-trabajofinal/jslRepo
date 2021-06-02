package domainapp.modules.simple.dom.reclamos;

import org.datanucleus.query.typesafe.*;
import org.datanucleus.api.jdo.query.*;

public class QReclamo extends PersistableExpressionImpl<Reclamo> implements PersistableExpression<Reclamo>
{
    public static final QReclamo jdoCandidate = candidate("this");

    public static QReclamo candidate(String name)
    {
        return new QReclamo(null, name, 5);
    }

    public static QReclamo candidate()
    {
        return jdoCandidate;
    }

    public static QReclamo parameter(String name)
    {
        return new QReclamo(Reclamo.class, name, ExpressionType.PARAMETER);
    }

    public static QReclamo variable(String name)
    {
        return new QReclamo(Reclamo.class, name, ExpressionType.VARIABLE);
    }

    public final StringExpression nombre;
    public final StringExpression apellido;
    public final StringExpression direccion;
    public final StringExpression telefono;

    public QReclamo(PersistableExpression parent, String name, int depth)
    {
        super(parent, name);
        this.nombre = new StringExpressionImpl(this, "nombre");
        this.apellido = new StringExpressionImpl(this, "apellido");
        this.direccion = new StringExpressionImpl(this, "direccion");
        this.telefono = new StringExpressionImpl(this, "telefono");
    }

    public QReclamo(Class type, String name, ExpressionType exprType)
    {
        super(type, name, exprType);
        this.nombre = new StringExpressionImpl(this, "nombre");
        this.apellido = new StringExpressionImpl(this, "apellido");
        this.direccion = new StringExpressionImpl(this, "direccion");
        this.telefono = new StringExpressionImpl(this, "telefono");
    }
}
