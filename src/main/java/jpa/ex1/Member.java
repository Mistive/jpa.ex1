package jpa.ex1;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Member {
    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;
    @Column(name = "USERNAME")
    private String name;

    @Embedded
    private Period workPeriod;
    @Embedded
    private Address homeAddress;

    public void setUserMember(String name){
        this.name = name;
    }
}
