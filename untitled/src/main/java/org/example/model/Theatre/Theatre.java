package org.example.model.Theatre;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;
import org.example.model.support.IdBoSupport;

@Getter
@Setter
@Entity
public class Theatre extends IdBoSupport {
    @Id
    @GeneratedValue(generator = "Theatre")
    @SequenceGenerator(sequenceName = "Theatre", initialValue = 1)
    private long id;
    private String name;
    private String city;
}
