package icard.demo.card.model;

import icard.demo.user.model.UserEntity;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "cards")
public class CardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    
    @JsonIgnoreProperties("cards")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
