package ViktorVasileski.restApiTest.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
public class Author {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;
    private String name;
    private String surname;
    private String email;
    private LocalDate birthDate;
    private String avatar;

    public Author (String name, String surname, String email, LocalDate birthDate){
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.birthDate = birthDate;
        this.avatar = "https://ui-avatars.com/api/?name=" + name + "+" + surname;
    }
}
