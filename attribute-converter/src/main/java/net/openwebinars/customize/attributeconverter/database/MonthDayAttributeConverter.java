package net.openwebinars.customize.attributeconverter.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Date;
import java.time.LocalDate;
import java.time.MonthDay;

@Converter(autoApply = true)
public class MonthDayAttributeConverter implements AttributeConverter<MonthDay, Date> {

    /**
     * Método encargado de convertir el valor de la entidad en un valor válido para la base de datos
     * @param attribute
     * @return
     */
    @Override
    public Date convertToDatabaseColumn(MonthDay attribute) {
        if (attribute != null) {
            LocalDate diaMes = attribute.atYear(1);
            Date result = Date.valueOf(diaMes);
            return result;
        }
        return null;
    }

    /**
     * Método encargado de convertir el valor de la base de datos en un valor válido para la entidad
     * @param dbData
     * @return
     */
    @Override
    public MonthDay convertToEntityAttribute(Date dbData) {
        if (dbData != null) {
            LocalDate desdeBD = dbData.toLocalDate();
            MonthDay result = MonthDay.from(desdeBD);
            return result;
        }
        return null;
    }
}
