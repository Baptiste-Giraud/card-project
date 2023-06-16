package icard.demo.user.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import icard.demo.card.model.CardEntity;


@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int age;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("user")
    private List<CardEntity> cards = new ArrayList<>();


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
    public List<CardEntity> getCards() {
        return cards;
    }

    public void setCards(List<CardEntity> cards) {
        this.cards = cards;
    }
    
    public void addCard(CardEntity card) {
        cards.add(card);
        card.setUser(this);
    }

    public void removeCard(CardEntity card) {
        cards.remove(card);
        card.setUser(null);
    }
}
