package br.unisul.aula.banco;

import br.unisul.aula.modelo.Cliente;

import java.util.List;

import javax.persistence.EntityManager;

public class ClienteImpl implements Banco<Cliente> {
    @Override
    public void insert(Cliente cliente) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();
    }

    @Override
    public void remove(Cliente cliente) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(cliente);
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(Cliente cliente) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(cliente);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Cliente> findAll() {
        EntityManager entityManager = JPAUtil.getEntityManager();

        return entityManager
                .createQuery("SELECT e FROM Cliente e", Cliente.class)
                .getResultList();
    }

    @Override
    public Cliente findById(Long id) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        return entityManager.getReference(Cliente.class, id);
    }

    public void deleteById(Long id) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        Cliente cliente = entityManager.getReference(Cliente.class, id);
        entityManager.getTransaction().begin();
        entityManager.remove(cliente);
        entityManager.getTransaction().commit();
    }
}
