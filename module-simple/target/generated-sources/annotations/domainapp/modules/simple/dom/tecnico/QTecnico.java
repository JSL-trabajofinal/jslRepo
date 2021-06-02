package domainapp.modules.simple.dom.tecnico;

import org.datanucleus.query.typesafe.*;
import org.datanucleus.api.jdo.query.*;

public class QTecnico extends PersistableExpressionImpl<Tecnico> implements PersistableExpression<Tecnico>
{
    public static final QTecnico jdoCandidate = candidate("this");

    public static QTecnico candidate(String name)
    {
        return new QTecnico(null, name, 5);
    }

    public static QTecnico candidate()
    {
        return jdoCandidate;
    }

    public static QTecnico parameter(String name)
    {
        return new QTecnico(Tecnico.class, name, ExpressionType.PARAMETER);
    }

    public static QTecnico variable(String name)
    {
        return new QTecnico(Tecnico.class, name, ExpressionType.VARIABLE);
    }

    public final StringExpression dni;
    public final StringExpression apellido;
    public final StringExpression nombre;

    public QTecnico(PersistableExpression parent, String name, int depth)
    {
        super(parent, name);
        this.dni = new StringExpressionImpl(this, "dni");
        this.apellido = new StringExpressionImpl(this, "apellido");
        this.nombre = new StringExpressionImpl(this, "nombre");
    }

    public QTecnico(Class type, String name, ExpressionType exprType)
    {
        super(type, name, exprType);
        this.dni = new StringExpressionImpl(this, "dni");
        this.apellido = new StringExpressionImpl(this, "apellido");
        this.nombre = new StringExpressionImpl(this, "nombre");
    }
}
