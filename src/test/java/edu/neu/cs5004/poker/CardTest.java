package edu.neu.cs5004.poker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {
    private Card card;
    private Card card2;
    @BeforeEach
    void setUp() {
        card = new Card(Card.Suit.CLUB, Card.Value.TWO);
        card2 = new Card(Card.Suit.CLUB, Card.Value.TWO);

    }

    @Test
    void testEquals() {
        assertEquals(card, card);
    }

    @Test
    void testNoCardEquals() {
        assertNotEquals(card, null);
    }

    @Test
    void testingEqualityBetweenCards() {
        assertEquals(card, card2);
    }

    @Test
    void testHashCode() {
        assertEquals(card.hashCode(), card.hashCode());
    }

    @Test
    void getSuit() {
        assertEquals(Card.Suit.CLUB, card.getSuit());
    }

    @Test
    void getValue() {
        assertEquals(Card.Value.TWO, card.getValue());
    }

    @Test
    void testToString() {
        assertEquals("Card{suit=CLUB, value=TWO}", card.toString());
    }
}