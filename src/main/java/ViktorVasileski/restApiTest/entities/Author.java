package ViktorVasileski.restApiTest.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Random;

@Getter
@Setter
@ToString
public class Author {
    @Setter(AccessLevel.NONE)
    private long id;
    private String name;
    private String surname;
    private String email;
    private LocalDate birthDate;
    private String avatar;

    public Author (String name, String surname, String email, LocalDate birthDate){
        Random rndm = new Random();
        this.id = rndm.nextInt(1, 1000);
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.birthDate = birthDate;
        this.avatar = "https://ui-avatars.com/api/?name=" + name + "+" + surname;
    }
}
