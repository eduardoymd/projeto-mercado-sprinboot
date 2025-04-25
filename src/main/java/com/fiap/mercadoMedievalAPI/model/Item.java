package com.fiap.mercadoMedievalAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome do item é obrigatório")
    private String nome;

    @NotBlank(message = "Tipo do item é obrigatório")
    private String tipo;

    @NotBlank(message = "Raridade do item é obrigatória")
    private String raridade;

    @Min(value = 0, message = "O preço não pode ser negativo")
    private double preco;

    @ManyToOne
    @JsonIgnoreProperties("itens")
    private Personagem dono;
}