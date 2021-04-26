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
            //movie 등록
            Movie movie = new Movie();
            movie.setDirector("aaaa");
            movie.setActor("bbbb");
            movie.setName("바람과함께사라지다.");
            movie.setPrice(10000);
            em.persist(movie);

            //영속성 컨텍스트 클리어
            em.flush();
            em.clear();

            Movie findMovie = em.find(Movie.class, movie.getId());
            System.out.println("findMovie = " + findMovie);


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
