package net.openwebinars.customize.hibernateusertype.database;

import net.openwebinars.customize.hibernateusertype.model.Color;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.StringType;
import org.hibernate.usertype.UserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Objects;

public class ColorUserType implements UserType {

    private Logger logger =  LoggerFactory.getLogger(ColorUserType.class);

    // Indica los tipos de las columnas en base de datos
    @Override
    public int[] sqlTypes() {
        return new int[] {StringType.INSTANCE.sqlType()};
    }

    // Tipo de clase que devuelve
    @Override
    public Class returnedClass() {
        return Color.class;
    }

    // Define cuando dos objetos son iguales
    @Override
    public boolean equals(Object o, Object o1) throws HibernateException {
        return Objects.equals(o, o1);
    }

    // Define el hashCode. Debe ir en consonancia con el método anterior.
    @Override
    public int hashCode(Object o) throws HibernateException {
        return Objects.hashCode(o);
    }

    // A través de este método implementamos el mecanismo necesario para leer el valor de la base de datos
    // y transformarlo al tipo de dato utilizado en la entidad.
    // En nuestro caso, leemos un valor hexadecimal almacenado como una cadena de caracteres,
    // y lo transformamos en el valor de una enumeración
    @Override
    public Object nullSafeGet(ResultSet resultSet, String[] strings,
                              SharedSessionContractImplementor sharedSessionContractImplementor,
                              Object o) throws HibernateException, SQLException {
        String value = resultSet.getString(strings[0]);
        logger.info("Color: " +  value);

        // Solo nos interesan las componentes RGB
        int color = Integer.parseInt(value.trim().substring(4,10), 16);

        int r = (color >> 16) & 0xFF;
        int g = (color >> 8) & 0xFF;
        int b = (color >> 0) & 0xFF;
        String rgb = r + ", " + g + ", " + b;

        logger.info("Color : " + rgb);
        Color result = Color.valueOfRGB(rgb);
        logger.info("Color: " + result);
        return result;
    }

    // Proceso inverso al método anterior. Obtenemos el valor de una propiedad de una entidad, y lo transformamos
    // para guardarlo en la base de datos.
    // En nuestro caso, tomamos el valor de una enumeración, o lo transformamos en un valor hexadecimal que almacenamos
    // como una cadena de caracteres.
    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Object o, int i,
                            SharedSessionContractImplementor sharedSessionContractImplementor)
            throws HibernateException, SQLException {
        if (Objects.isNull(o))
            preparedStatement.setNull(i, Types.VARCHAR);
        else {
            Color c = (Color) o;
            logger.info("Color: " + c);
            logger.info("Color como RGB en hexadecimal: " + c.getARGB());
            preparedStatement.setString(i,c.getARGB());
        }
    }


    @Override
    public boolean isMutable() {
        return true;
    }

    // Para el resto de métodos no necesitamos una implementación compleja.
    @Override
    public Object deepCopy(Object o) throws HibernateException {
        return o;
    }


    @Override
    public Serializable disassemble(Object o) throws HibernateException {
        return (Serializable) o;
    }

    @Override
    public Object assemble(Serializable serializable, Object o) throws HibernateException {
        return serializable;
    }

    @Override
    public Object replace(Object o, Object o1, Object o2) throws HibernateException {
        return o;
    }
}
