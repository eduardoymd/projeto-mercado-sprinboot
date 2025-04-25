package com.fiap.mercadoMedievalAPI.controller;

import com.fiap.mercadoMedievalAPI.model.Personagem;
import com.fiap.mercadoMedievalAPI.repository.PersonagemRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("personagem")
@Slf4j
public class PersonagemController {

    @Autowired
    private PersonagemRepository repository;

    @GetMapping
    @Cacheable("personagem")
    public List<Personagem> index() {
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Personagem create(@RequestBody @Valid Personagem personagem){
        return repository.save(personagem);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        repository.delete(getPersonagem(id));
        return ResponseEntity.noContent().build();
    }

    private Personagem getPersonagem(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Personagem não encontrado"));
    }
}