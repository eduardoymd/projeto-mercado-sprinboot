package com.fiap.mercadoMedievalAPI.controller;

import com.fiap.mercadoMedievalAPI.model.Item;
import com.fiap.mercadoMedievalAPI.model.Personagem;
import com.fiap.mercadoMedievalAPI.repository.ItemRepository;
import com.fiap.mercadoMedievalAPI.repository.PersonagemRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
@Slf4j
public class ItemController {

    private final ItemRepository itemRepository;
    private final PersonagemRepository personagemRepository;

    // Listar todos os itens
    @GetMapping
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    // Buscar item por ID
    @GetMapping("/{id}")
    public Item findById(@PathVariable Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado"));
    }

    // Criar item com ID do personagem (dono)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Item create(@RequestBody @Valid Item item) {
        if (item.getDono() != null && item.getDono().getId() != null) {
            Personagem dono = personagemRepository.findById(item.getDono().getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Personagem dono não encontrado"));
            item.setDono(dono);
        }
        return itemRepository.save(item);
    }

    // Atualizar um item
    @PutMapping("/{id}")
    public Item update(@PathVariable Long id, @RequestBody @Valid Item itemAtualizado) {
        Item itemExistente = itemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado"));

        itemExistente.setNome(itemAtualizado.getNome());
        itemExistente.setTipo(itemAtualizado.getTipo());
        itemExistente.setRaridade(itemAtualizado.getRaridade());
        itemExistente.setPreco(itemAtualizado.getPreco());

        if (itemAtualizado.getDono() != null && itemAtualizado.getDono().getId() != null) {
            Personagem novoDono = personagemRepository.findById(itemAtualizado.getDono().getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Novo dono não encontrado"));
            itemExistente.setDono(novoDono);
        }

        return itemRepository.save(itemExistente);
    }

    // Deletar item
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado"));
        itemRepository.delete(item);
        return ResponseEntity.noContent().build();
    }
}

