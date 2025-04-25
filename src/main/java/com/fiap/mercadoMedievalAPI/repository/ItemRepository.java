package com.fiap.mercadoMedievalAPI.repository;

import com.fiap.mercadoMedievalAPI.model.Item;
import com.fiap.mercadoMedievalAPI.model.ItemRaridade;
import com.fiap.mercadoMedievalAPI.model.ItemTipo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByNomeContaining(String nome);
    List<Item> findByTipo(ItemTipo tipo);
    List<Item> findByPrecoBetween(double min, double max);
    List<Item> findByRaridade(ItemRaridade raridade);
}
