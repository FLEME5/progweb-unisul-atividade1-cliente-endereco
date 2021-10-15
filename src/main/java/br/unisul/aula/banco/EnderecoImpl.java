package br.unisul.aula.banco;

import br.unisul.aula.modelo.Endereco;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class EnderecoImpl implements Banco<Endereco> {
    @Override
    public void insert(Endereco endereco) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(endereco);
        entityManager.getTransaction().commit();
    }

    @Override
    public void remove(Endereco endereco) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(endereco);
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(Endereco endereco) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(endereco);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Endereco> findAll() {
        EntityManager entityManager = JPAUtil.getEntityManager();

        return entityManager
                .createQuery("SELECT e FROM Endereco e", Endereco.class)
                .getResultList();
    }

    @Override
    public Endereco findById(Long id) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        TypedQuery<Endereco> query = entityManager
            .createQuery("SELECT e FROM Endereco e WHERE id = :id", Endereco.class);

        return query.setParameter("id", id).getSingleResult();
    }

    public Endereco findByCep(Integer cep) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        TypedQuery<Endereco> query = entityManager
            .createQuery("SELECT e FROM Endereco e WHERE cep = :cep", Endereco.class);

        return query.setParameter("cep", cep).getSingleResult();
    }
}
