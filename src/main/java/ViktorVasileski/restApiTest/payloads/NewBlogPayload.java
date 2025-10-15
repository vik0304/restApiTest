package ViktorVasileski.restApiTest.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class NewBlogPayload {
    private String category;
    private String title;
    private String content;
    private int readingTime;
    private UUID authorId;
}
