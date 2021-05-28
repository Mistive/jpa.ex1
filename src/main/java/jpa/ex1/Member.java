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

    @Embedded
    @AttributeOverrides({
                    @AttributeOverride(name="city",
                    column = @Column(name = "WORK_CITY")),
                    @AttributeOverride(name="street",
                    column = @Column(name = "WORK_STREET")),
                    @AttributeOverride(name="zipcode",
                    column = @Column(name = "WORK_ZIPCODE"))
    })

    private Address workAddress;

    public void setUserMember(String name){
        this.name = name;
    }
}
