package net.openwebinars.customize.hibernatecompositeusertype.database;

import net.openwebinars.customize.hibernatecompositeusertype.model.Direccion;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;
import org.hibernate.usertype.CompositeUserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Objects;

public class DireccionCompositeUserType implements CompositeUserType {


    /**
     * Nombre con el que se exponen las columnas
     * @return
     */
    @Override
    public String[] getPropertyNames() {
        return new String[] {"linea1","linea2","codigoPostal","poblacion","provincia"};
    }

    /**
     * Tipo de las columnas
     * @return
     */
    @Override
    public Type[] getPropertyTypes() {
        return new Type[]{
                StringType.INSTANCE, StringType.INSTANCE, StringType.INSTANCE,
                StringType.INSTANCE, StringType.INSTANCE
        };
    }

    /**
     * Devuelve el valor individual de una de las columnas de la propiedad
     * @param o Objeto de tipo Direccion
     * @param i Componente del atributo de tipo direccion
     * @return
     * @throws HibernateException
     */
    @Override
    public Object getPropertyValue(Object o, int i) throws HibernateException {
        Direccion direccion = (Direccion) o;
        switch (i) {
            case 0: return direccion.getLinea1();
            case 1: return direccion.getLinea2();
            case 2: return direccion.getCodigoPostal();
            case 3: return direccion.getPoblacion();
            case 4: return direccion.getProvincia();

        }

        throw new IllegalArgumentException("No se puede acceder al índice");

    }

    /**
     * Establece el valor individual de una de las columnas de la propiedad
     * @param o Objeto de tipo dirección
     * @param i Componente del atributo de tipo dirección
     * @param o1 Valor a establecer
     * @throws HibernateException
     */
    @Override
    public void setPropertyValue(Object o, int i, Object o1) throws HibernateException {
        Direccion direccion = (Direccion) o;
        String value = (String) o1;
        switch (i) {
            case 0: direccion.setLinea1(value);
            case 1: direccion.setLinea2(value);
            case 2: direccion.setCodigoPostal(value);
            case 3: direccion.setPoblacion(value);
            case 4: direccion.setProvincia(value);
        }
        throw new IllegalArgumentException("No se puede acceder al índice");
    }

    @Override
    public Class returnedClass() {
        return Direccion.class;
    }

    @Override
    public boolean equals(Object o, Object o1) throws HibernateException {
        return Objects.equals(o, o1);
    }

    @Override
    public int hashCode(Object o) throws HibernateException {
        return Objects.hashCode(o);
    }

    /**
     * Método que obtiene desde la base de datos un objeto de tipo Direccion
     * @param resultSet
     * @param strings
     * @param sharedSessionContractImplementor
     * @param o
     * @return
     * @throws HibernateException
     * @throws SQLException
     */
    @Override
    public Object nullSafeGet(ResultSet resultSet, String[] strings, SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException, SQLException {
        // ¿Cuándo consideramos que una dirección, como propiedad compuesta, debe ser nula?
        // En este caso, cuando todos los componentes que la conforman sean nulos

        Direccion result = null;

        final String linea1 = resultSet.getString(strings[0]);
        final String linea2 = resultSet.getString(strings[1]);
        final String codigoPostal = resultSet.getString(strings[2]);
        final String poblacion = resultSet.getString(strings[3]);
        final String provincia = resultSet.getString(strings[4]);

        if (Objects.nonNull(linea1) || Objects.nonNull(linea2) || Objects.nonNull(codigoPostal)
                || Objects.nonNull(poblacion) || Objects.nonNull(provincia)) {
            result = Direccion.builder()
                        .linea1(linea1)
                        .linea2(linea2)
                        .codigoPostal(codigoPostal)
                        .poblacion(poblacion)
                        .provincia(provincia)
                        .build();
        }

        return result;

    }

    /**
     * Método que almacena en la base de datos un objeto de tipo Direccion
     * @param preparedStatement
     * @param o
     * @param i
     * @param sharedSessionContractImplementor
     * @throws HibernateException
     * @throws SQLException
     */
    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Object o, int i, SharedSessionContractImplementor sharedSessionContractImplementor) throws HibernateException, SQLException {
        if (o == null) {
            preparedStatement.setNull(i, Types.VARCHAR);
        } else {
            Direccion direccion = (Direccion) o;
            preparedStatement.setString(i, direccion.getLinea1());
            preparedStatement.setString(i+1, direccion.getLinea2());
            preparedStatement.setString(i+2, direccion.getCodigoPostal());
            preparedStatement.setString(i+3, direccion.getPoblacion());
            preparedStatement.setString(i+4, direccion.getProvincia());
        }
    }

    @Override
    public Object deepCopy(Object o) throws HibernateException {
        return o;
    }

    @Override
    public boolean isMutable() {
        return true;
    }

    /**
     * Método invocado cuando un objeto se guarda en caché de segundo nivel
     * @param o
     * @param sharedSessionContractImplementor
     * @return
     * @throws HibernateException
     */
    @Override
    public Serializable disassemble(Object o, SharedSessionContractImplementor sharedSessionContractImplementor) throws HibernateException {
        return (Serializable) o;
    }

    /**
     * Método invocado cuando se rescata un objeto de la caché
     * @param serializable
     * @param sharedSessionContractImplementor
     * @param o
     * @return
     * @throws HibernateException
     */
    @Override
    public Object assemble(Serializable serializable, SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        return serializable;
    }

    /**
     * Método invocado cuando se mergean dos objetos
     * @param o
     * @param o1
     * @param sharedSessionContractImplementor
     * @param o2
     * @return
     * @throws HibernateException
     */
    @Override
    public Object replace(Object o, Object o1, SharedSessionContractImplementor sharedSessionContractImplementor, Object o2) throws HibernateException {
        return o;
    }
}
