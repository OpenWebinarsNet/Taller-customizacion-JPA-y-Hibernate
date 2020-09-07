package net.openwebinars.customize.attributeconverter.dao;


import net.openwebinars.customize.attributeconverter.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
}
