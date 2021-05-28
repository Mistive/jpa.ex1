package jpa.ex1;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
@Getter
@Setter
public class Period {

    public Period() {
    }

    private LocalDateTime startDate;
    private LocalDateTime endDate;

}
