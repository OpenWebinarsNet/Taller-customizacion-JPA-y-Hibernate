package net.openwebinars.customize.attributeconverter;

import net.openwebinars.customize.attributeconverter.dao.PersonaRepository;
import net.openwebinars.customize.attributeconverter.model.Aficiones;
import net.openwebinars.customize.attributeconverter.model.Persona;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.MonthDay;
import java.util.EnumSet;

@SpringBootApplication
public class AttributeConverterApplication {

    public static void main(String[] args) {
        SpringApplication.run(AttributeConverterApplication.class, args);
    }


    @Bean
    public CommandLineRunner run(PersonaRepository repo) {
        return args -> {

            EnumSet<Aficiones> misAficiones = EnumSet.noneOf(Aficiones.class);
            misAficiones.add(Aficiones.LECTURA);
            misAficiones.add(Aficiones.VIAJAR);

            Persona persona = Persona.builder()
                                .nombre("Luismi")
                                .apellidos("López Magaña")
                                .fechaNacimiento(LocalDate.of(1982, 9, 18))
                                .fechaOnomastica(MonthDay.of(8, 25))
                                .aficiones(misAficiones)
                                .build();

            repo.save(persona);


        };
    }

}
