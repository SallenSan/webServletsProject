package org.academy.model;

import org.academy.model.Categoria;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Livro {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Integer isbn;
    @Column(nullable = false)
    private String titulo;
    @Column(nullable = false)
    private Integer quantidade;
    @ManyToOne
    private List<Categoria> categoria;

    public Livro(Long id, Integer isbn, String titulo, Integer quantidade) {
        this.id = id;
        this.isbn = isbn;
        this.titulo = titulo;
        this.quantidade = quantidade;
    }
}
