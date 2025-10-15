package ViktorVasileski.restApiTest.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@Entity
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;
    private String category;
    private String title;
    private String cover;
    private String content;
    private int readingTime;

    public Blog(String category, String title, String content, int readingTime){
        this.category = category;
        this.title = title;
        this.cover = "https://picsum.photos/200/300";
        this.content = content;
        this.readingTime = readingTime;
    }
}
