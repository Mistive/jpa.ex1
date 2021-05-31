package jpa.ex1;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Set;

public class jpaMain {

    public static void main(String[] args) {
//        System.out.println("Hello World");
        //write your code
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Member member = new Member();
            member.setName("member1");
            member.setHomeAddress(new Address("homeCity", "street", "1234"));

            //HashSet에 추가
            member.getFavorateFoods().add("치킨");
            member.getFavorateFoods().add("족발");
            member.getFavorateFoods().add("피자");
            member.getAddressHistory().add(new AddressEntity("old1", "street", "1234"));
            member.getAddressHistory().add(new AddressEntity("old2", "street", "1234"));

            em.persist(member);

            em.flush();
            em.clear();
//조회
            System.out.println("============START============");
            Member findMember = em.find(Member.class, member.getId());
//            //지연 로딩
//            List<AddressEntity> addressHistory = findMember.getAddressHistory();
//            for (AddressEntity addressEntity : addressHistory) {
//                System.out.println("addressEntity.getAddress().getCity() = " + addressEntity.getAddress().getCity());
//            }
//
//            Set<String> favorateFoods = findMember.getFavorateFoods();
//            for (String favorateFood : favorateFoods) {
//                System.out.println("favorateFood = " + favorateFood);
//            }

// 수정
            //homeCity -> newCity
//            findMember.getHomeAddress().setCity("newCity");   //이렇게 수정할 경우 SideEffect 발생
//            Address a = findMember.getHomeAddress();
//            findMember.setHomeAddress(new Address("newCity", a.getStreet(), a.getZipcode()));
//
//            //치킨 -> 한식
//            findMember.getFavorateFoods().remove("치킨");
//            findMember.getFavorateFoods().add("한식");

            //History 정보 수정 : equals를 이용해서 조회를 수행하게 되는데 여기서 equals가 제대로 override가 안되있다면? List에서 원하는 값을 못찾게 되는 것이다.
            findMember.getAddressHistory().remove(new AddressEntity("old1", "street", "1234"));
            findMember.getAddressHistory().add(new AddressEntity("newCity1", "street", "1234"));

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
