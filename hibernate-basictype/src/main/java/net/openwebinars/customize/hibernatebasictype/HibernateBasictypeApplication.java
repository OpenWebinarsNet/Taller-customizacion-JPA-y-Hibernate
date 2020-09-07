package net.openwebinars.customize.hibernatebasictype;

import net.openwebinars.customize.hibernatebasictype.dao.DaoCuerpoCeleste;
import net.openwebinars.customize.hibernatebasictype.model.CuerpoCeleste;
import net.openwebinars.customize.hibernatebasictype.utils.ConversorKmAniosLuz;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.math.BigInteger;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class HibernateBasictypeApplication {

    public static void main(String[] args) {
        SpringApplication.run(HibernateBasictypeApplication.class, args);
    }


    @Bean
    public CommandLineRunner init(DaoCuerpoCeleste dao, ConversorKmAniosLuz conversor) {
        return args -> {
          dao.save(CuerpoCeleste.builder()
                    .nombre("Sol")
                    .distanciaTierra(new BigInteger("150000000"))
                    .build());

          dao.save(CuerpoCeleste.builder()
                  .nombre("Próxima Centauri")
                  .distanciaTierra(conversor.aniosLuzKilometros(4L))
                  .build());

            dao.save(CuerpoCeleste.builder()
                    .nombre("Icarus")
                    .distanciaTierra(conversor.aniosLuzKilometros(9000000L))
                    .build());


        };
    }


}
