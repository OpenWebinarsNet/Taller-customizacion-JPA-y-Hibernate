package net.openwebinars.customize.hibernatebasictype.database;

import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.descriptor.java.AbstractTypeDescriptor;
import org.hibernate.type.descriptor.java.BigDecimalTypeDescriptor;

import java.math.BigInteger;

public class BigIntegerStringJavaDesciptor extends AbstractTypeDescriptor<BigInteger> {

    public static final BigIntegerStringJavaDesciptor INSTANCE = new BigIntegerStringJavaDesciptor();

    public BigIntegerStringJavaDesciptor() {
        super(BigInteger.class);
    }

    @Override
    public BigInteger fromString(String s) {
        BigDecimalTypeDescriptor descriptor = new BigDecimalTypeDescriptor();
        return new BigInteger(s);
    }

    @Override
    public String toString(BigInteger bigInteger) {
        return bigInteger.toString();
    }

    @Override
    public <X> X unwrap(BigInteger bigInteger, Class<X> aClass, WrapperOptions wrapperOptions) {
        if (bigInteger == null)
            return null;

        if (String.class.isAssignableFrom(aClass))
            return (X) bigInteger.toString();

        throw unknownUnwrap(aClass);

    }

    @Override
    public <X> BigInteger wrap(X x, WrapperOptions wrapperOptions) {
        if (x == null)
            return null;

        if (String.class.isInstance(x))
            return new BigInteger((String)x);

        throw unknownWrap(x.getClass());

    }
}
