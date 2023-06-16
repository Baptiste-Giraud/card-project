package icard.demo.card.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import icard.demo.card.model.CardEntity;
import icard.demo.card.repository.CardRepository;
import icard.demo.user.model.UserEntity;
import icard.demo.user.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardController {
    @Autowired
    private CardRepository cardRepository;
    
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<CardEntity> getAllCards() {
        return cardRepository.findAll();
    }

    @GetMapping("/{id}")
    public CardEntity getCardById(@PathVariable("id") int id) {
        return cardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid card ID"));
    }

    @PostMapping("/{userId}")
    public CardEntity createCard(@PathVariable("userId") int userId, @RequestBody CardEntity card) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));

        user.addCard(card);
        return userRepository.save(user).getCards().get(user.getCards().size() - 1);
    }

    @PutMapping("/{id}")
    public CardEntity updateCard(@PathVariable("id") int id, @RequestBody CardEntity updatedCard) {
        CardEntity card = cardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid card ID"));

        card.setTitle(updatedCard.getTitle());
        return cardRepository.save(card);
    }

    @DeleteMapping("/{id}")
    public void deleteCard(@PathVariable("id") int id) {
        cardRepository.deleteById(id);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleIllegalArgumentException(IllegalArgumentException ex) {
        return ex.getMessage();
    }
}
