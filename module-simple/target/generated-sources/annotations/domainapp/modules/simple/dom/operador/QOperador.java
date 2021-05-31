package domainapp.modules.simple.dom.operador;

import org.datanucleus.query.typesafe.*;
import org.datanucleus.api.jdo.query.*;

public class QOperador extends PersistableExpressionImpl<Operador> implements PersistableExpression<Operador>
{
    public static final QOperador jdoCandidate = candidate("this");

    public static QOperador candidate(String name)
    {
        return new QOperador(null, name, 5);
    }

    public static QOperador candidate()
    {
        return jdoCandidate;
    }

    public static QOperador parameter(String name)
    {
        return new QOperador(Operador.class, name, ExpressionType.PARAMETER);
    }

    public static QOperador variable(String name)
    {
        return new QOperador(Operador.class, name, ExpressionType.VARIABLE);
    }

    public final StringExpression nombre;
    public final StringExpression apellido;
    public final StringExpression usuario;
    public final StringExpression contraseña;

    public QOperador(PersistableExpression parent, String name, int depth)
    {
        super(parent, name);
        this.nombre = new StringExpressionImpl(this, "nombre");
        this.apellido = new StringExpressionImpl(this, "apellido");
        this.usuario = new StringExpressionImpl(this, "usuario");
        this.contraseña = new StringExpressionImpl(this, "contraseña");
    }

    public QOperador(Class type, String name, ExpressionType exprType)
    {
        super(type, name, exprType);
        this.nombre = new StringExpressionImpl(this, "nombre");
        this.apellido = new StringExpressionImpl(this, "apellido");
        this.usuario = new StringExpressionImpl(this, "usuario");
        this.contraseña = new StringExpressionImpl(this, "contraseña");
    }
}
