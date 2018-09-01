package com.codecool.bfsexample;

import com.codecool.bfsexample.model.UserNode;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class BFSExample {

    public static List<UserNode> populateDB(EntityManager em) {

        RandomDataGenerator generator = new RandomDataGenerator();
        List<UserNode> users = generator.generate();

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        for (UserNode user : users) {
            em.persist(user);
        }
        transaction.commit();

        GraphPlotter.plot(users);

        return users;
    }

    public static void main(String[] args) throws CannotReachNodeException{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bfsExampleUnit");
        EntityManager em = emf.createEntityManager();

        em.clear();
        List<UserNode> population = populateDB(em);

        BreadthFirstSearch breadthFirstSearch = new BreadthFirstSearch();
        int distance;

        distance = breadthFirstSearch.distance(population.get(4), population.get(0));
        System.out.println(distance);

        distance = breadthFirstSearch.distance(population.get(0), population.get(4));
        System.out.println(distance);

        distance = breadthFirstSearch.distance(population.get(population.size() / 2), population.get(population.size() - 1));
        System.out.println(distance);

        distance = breadthFirstSearch.distance(population.get(population.size() - 1), population.get(population.size() / 2));
        System.out.println(distance);
    }
}
