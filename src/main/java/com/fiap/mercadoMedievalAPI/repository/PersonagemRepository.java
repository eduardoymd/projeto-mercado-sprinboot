package com.fiap.mercadoMedievalAPI.repository;

import com.fiap.mercadoMedievalAPI.model.Personagem;
import com.fiap.mercadoMedievalAPI.model.PersonagemClasse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonagemRepository extends JpaRepository<Personagem, Long> {
    List<Personagem> findByNomeContaining(String nome);
    List<Personagem> findByClasse(PersonagemClasse classe);
}
