package jpa.ex1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.*;
import java.util.List;

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
            //생성
//            Member member = new Member();
//            member.setId(1L);
//            member.setName("HelloA");
//            em.persist(member);
            //조회
//            Member findMember = em.find(Member.class, 1L);
//            System.out.println("findMember.getId() = " + findMember.getId());
//            System.out.println("findMember.getName() = " + findMember.getName());
            //삭제
//            Member findMember = em.find(Member.class, 1L);
//            em.remove(findMember)
            //수정
//            Member findMember = em.find(Member.class, 1L);
//            findMember.setName("HelloJPA");
            //이후 자바 컬렉션 사용하는 것처럼 이름만 바꿔 주고 persist를 해주지 않아도 된다.
            //JPA를 통해서 가져오면 JPA가 관리를 한다.(신기신기)
            //commit전에 Entity의 변경 사항이 있으면 update query를 통해 변경 후 commit을 수행한다.
            //JPQL
//            List<Member> result = em.createQuery("select m from Member as m", Member.class)
//                    .setFirstResult(5)
//                    .setMaxResults(8)
//                    .getResultList();
//
//            for (Member member : result) {
//                System.out.println("member.getName() = " + member.getName());
//            }


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

}
