package net.openwebinars.customize.hibernatebasictype.database;

import org.hibernate.dialect.Dialect;
import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.type.DiscriminatorType;
import org.hibernate.type.descriptor.sql.VarcharTypeDescriptor;

import java.math.BigInteger;

public class BigIntegerStringType extends AbstractSingleColumnStandardBasicType<BigInteger>
    implements DiscriminatorType<BigInteger> {

    public static final BigIntegerStringType INSTANCE = new BigIntegerStringType();

    public BigIntegerStringType() {
        super(VarcharTypeDescriptor.INSTANCE, BigIntegerStringJavaDesciptor.INSTANCE);
    }

    @Override
    public String getName() {
        return "biginteger";
    }


    @Override
    public BigInteger stringToObject(String s) throws Exception {
        return fromString(s);
    }

    @Override
    public String objectToSQLString(BigInteger bigInteger, Dialect dialect) throws Exception {
        return toString(bigInteger);
    }
}
