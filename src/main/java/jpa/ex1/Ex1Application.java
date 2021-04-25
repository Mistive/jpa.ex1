package jpa.ex1;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

@SpringBootApplication
public class Ex1Application {

    public static void main(String[] args) {
//        System.out.println("Hello World");
        //write your code
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em =   emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Member member = saveMember(em);
            System.out.println("===========================");
            Team team = new Team();
            team.setName("teamA");
            //연관관계가 바뀌는 것이기 때문에 외래키가 바뀌어야 하는데 현재 외래키는 MEMBER table에서 관리하고 있으므로,
            team.getMembers().add(member);

            em.persist(team);



            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }

        System.out.println("END");
//        SpringApplication.run(Ex1Application.class, args);
    }

    private static Member saveMember(EntityManager em) {
        Member member = new Member();
        member.setUserMember("member1");

        em.persist(member);
        return member;
    }

}
