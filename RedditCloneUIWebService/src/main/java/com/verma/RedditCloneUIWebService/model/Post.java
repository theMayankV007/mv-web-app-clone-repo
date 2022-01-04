package com.verma.RedditCloneUIWebService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

@Data // Lombok : Getters and Setters
@Entity // JPA : ORM annotation
@Builder // Lombok : this generates the builder methods for this class
// - this mainly uses the builder design pattern to create the object of the class
@AllArgsConstructor // Lombok : generates all args constructor of the class
@NoArgsConstructor // Lombok : generates no args constructor of the class
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @NotBlank(message = "Post Name cannot be empty")
    private String postName;

    @Nullable //can be null
    @Lob // adds the capability to handle large chunk of data
    private String description;
    private Integer voteCount;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;
    private Instant createdDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Subreddit subreddit;

}
