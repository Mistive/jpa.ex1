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
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Member member1 = new Member();
            member1.setUserMember("member1");
            em.persist(member1);

            em.flush();
            em.clear();

            Member refMember = em.getReference(Member.class, member1.getId());
            System.out.println("refMember = " + refMember.getClass());  //Proxy
            System.out.println("isLoaded(초기화 x) = " + (emf.getPersistenceUnitUtil().isLoaded(refMember)));  //초기화 안된 상태
            System.out.println("refMember = " + refMember.getName());
            System.out.println("isLoaded(초기화 o) = " + (emf.getPersistenceUnitUtil().isLoaded(refMember)));  //초기화 안된 상태

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
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
