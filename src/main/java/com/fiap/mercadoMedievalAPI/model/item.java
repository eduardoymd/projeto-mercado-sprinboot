package com.fiap.mercadoMedievalAPI.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome do item é obrigatório")
    private String nome;

    @NotBlank(message = "Tipo do item é obrigatório")
    private String tipo; // arma, armadura, poção, acessório

    @NotBlank(message = "Raridade do item é obrigatória")
    private String raridade; // comum, raro, épico, lendário

    @Min(value = 0, message = "O preço não pode ser negativo")
    private double preco;
}
