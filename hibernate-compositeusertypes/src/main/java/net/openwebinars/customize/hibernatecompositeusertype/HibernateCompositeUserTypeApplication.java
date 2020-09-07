package net.openwebinars.customize.hibernatecompositeusertype;

import net.openwebinars.customize.hibernatecompositeusertype.dao.DaoPersona;
import net.openwebinars.customize.hibernatecompositeusertype.model.Direccion;
import net.openwebinars.customize.hibernatecompositeusertype.model.Persona;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class HibernateCompositeUserTypeApplication {

    public static void main(String[] args) {
        SpringApplication.run(HibernateCompositeUserTypeApplication.class, args);
    }



    @Bean
    public CommandLineRunner run(DaoPersona daoPersona) {
        return args -> {
            Persona persona = new Persona();
            persona.setNombre("Luismi");
            persona.setApellidos("López Magaña");

            Direccion direccion = new Direccion();
            direccion.setLinea1("Calle Rue del Percebe");
            direccion.setCodigoPostal("41000");
            direccion.setPoblacion("Sevilla");
            direccion.setProvincia("Sevilla");

            persona.setDireccion(direccion);

            daoPersona.save(persona);

            Optional<Persona> p2 = daoPersona.getById(1L);
            p2.ifPresent(System.out::println);



        };
    }


}
