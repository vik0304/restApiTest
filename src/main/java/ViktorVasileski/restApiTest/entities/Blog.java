package ViktorVasileski.restApiTest.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Random;

@Getter
@Setter
@ToString
public class Blog {
    @Setter(AccessLevel.NONE)
    private long id;
    private String category;
    private String title;
    private String cover;
    private String content;
    private int readingTime;

    public Blog(String category, String title, String content, int readingTime){
        Random rndm = new Random();
        this.id = rndm.nextInt(1, 1000);
        this.category = category;
        this.title = title;
        this.cover = "https://picsum.photos/200/300";
        this.content = content;
        this.readingTime = readingTime;
    }
}
