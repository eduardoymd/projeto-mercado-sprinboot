package com.fiap.mercadoMedievalAPI.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Personagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome não pode estar em branco")
    private String nome;

    @Enumerated(EnumType.STRING)
    private PersonagemClasse classe;

    @Min(value = 1, message = "O nível deve ser no mínimo 1.")
    @Max(value = 99, message = "O nível deve ser no máximo 99.")
    private int nivel;

    @Min(value = 0, message = "O saldo de moedas não pode ser negativo.")
    private int moedas;

    @OneToMany(mappedBy = "dono", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("dono")
    private List<Item> itens;
}