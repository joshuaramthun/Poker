package edu.neu.cs5004.poker;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PokerTest {



    @Test
    void highestHand() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.DIAMOND, Card.Value.KING));
        cards.add(new Card(Card.Suit.DIAMOND, Card.Value.JACK));
        cards.add(new Card(Card.Suit.CLUB, Card.Value.JACK));
        cards.add(new Card(Card.Suit.CLUB, Card.Value.KING));
        cards.add(new Card(Card.Suit.CLUB, Card.Value.QUEEN));
        Hand handA = new Hand(cards);

        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.SPADE, Card.Value.QUEEN));
        cards.add(new Card(Card.Suit.SPADE, Card.Value.JACK));
        cards.add(new Card(Card.Suit.SPADE, Card.Value.TEN));
        cards.add(new Card(Card.Suit.SPADE, Card.Value.NINE));
        cards.add(new Card(Card.Suit.SPADE, Card.Value.EIGHT));
        Hand handB = new Hand(cards);

        Poker poker = new Poker();
        Hand winningHand = poker.highestHand(handA, handB);
        assertEquals(handB, winningHand);
    }
}