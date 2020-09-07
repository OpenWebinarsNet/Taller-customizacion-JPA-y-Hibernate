package net.openwebinars.customize.hibernatebasictype.utils;

import net.openwebinars.customize.hibernatebasictype.model.CuerpoCeleste;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

@Service
public class ConversorKmAniosLuz {

    private static final BigInteger FACTOR_CONVERSION = BigInteger.valueOf(9_461_000_000_000L);
    private static final BigDecimal BD_FACTOR_CONVERSION = new BigDecimal(FACTOR_CONVERSION);

    public BigDecimal kilometrosAniosLuz(BigInteger km) {
        return new BigDecimal(km).divide(BD_FACTOR_CONVERSION, 7, RoundingMode.HALF_UP);
    }

    public BigInteger aniosLuzKilometros(BigDecimal aniosLuz) {
        return aniosLuz.multiply(BD_FACTOR_CONVERSION).toBigInteger();
    }

    public BigInteger aniosLuzKilometros(Long aniosLuz) {
        return FACTOR_CONVERSION.multiply(BigInteger.valueOf(aniosLuz));
    }




}
