package br.unisul.aula.banco;

import java.util.List;

public interface Banco<E> {
    void insert(E e);
    void remove(E e);
    void update(E e);
    List<E> findAll();
    E findById(Long id);
}
