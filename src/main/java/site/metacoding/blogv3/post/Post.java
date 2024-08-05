package site.metacoding.blogv3.post;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import site.metacoding.blogv3.category.Category;
import site.metacoding.blogv3.user.User;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Entity
@Table(name = "post_tb")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;
    private String thumbnail;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @CreationTimestamp
    private LocalDateTime created_at;

    @Builder
    public Post(String title, String content, String thumbnail, User user, Category category, LocalDateTime created_at) {
        this.title = title;
        this.content = content;
        this.thumbnail = thumbnail;
        this.user = user;
        this.category = category;
        this.created_at = created_at;
    }
}
