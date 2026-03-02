package org.example.model.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;
import org.example.enums.Roles;
import org.example.model.support.IdBoSupport;

@Entity
@Setter
@Getter
public class User extends IdBoSupport {

    @Id
    @GeneratedValue(generator = "User")
    @SequenceGenerator(sequenceName = "User", initialValue = 1)
    private long id;

    private String name;
    private String email;
    private Roles role;
    private String password;
}
