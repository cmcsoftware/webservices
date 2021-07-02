package com.fortech.dao;


import com.fortech.model.Animal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class AnimalDao {

    @PersistenceContext(unitName = "restapi_PU")
    EntityManager em;

    public List getAll() {
        return em.createNamedQuery("Animal.findAll", Animal.class).getResultList();
    }

    public Animal findById(Long id) {
        return em.find(Animal.class, id);
    }

    public void update(Animal animal) {
        em.merge(animal);
    }

    public void create(Animal animal) {
        em.persist(animal);
    }

    public void delete(Animal animal) {
        if (!em.contains(animal)) {
            animal = em.merge(animal);
        }

        em.remove(animal);
    }
}
