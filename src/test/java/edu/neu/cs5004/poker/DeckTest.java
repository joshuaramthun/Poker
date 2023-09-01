package edu.neu.cs5004.poker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {
    private Deck testDeck;
    private Deck comparisonDeck;

    @BeforeEach
    void setUp() {
        testDeck = new Deck();
        comparisonDeck = new Deck();
    }

    @Test
    void dealHands() {
        assertEquals(1, testDeck.dealHands(1).size());
    }
    @Test
    void dealHandsOfInvalidQuantity() {
        assertThrows(IllegalArgumentException.class, () -> testDeck.dealHands(0));
    }

    @Test
    void dealHandsOfValidQuantity() {
        List<Hand> hands = testDeck.dealHands(3);
        assertEquals(3, hands.size());
        for (Hand hand : hands) {
            assertEquals(5, hand.getCards().size());
        }
    }

    @Test
    void testEquals() {
        assertEquals(testDeck, testDeck);
    }

    @Test
    void testNoDeckEquals() {
        assertNotEquals(testDeck, null);
    }

    @Test
    void testingEqualityBetweenDecks() {
        assertEquals(testDeck, comparisonDeck);
    }

    @Test
    void testHashCode() {
        assertEquals(testDeck.hashCode(), testDeck.hashCode());
    }

    @Test
    void testToString() {
        assertEquals("Deck{cards=[Card{suit=CLUB, value=TWO}, Card{suit=CLUB, value=THREE}, Card{suit=CLUB, value=FOUR}, Card{suit=CLUB, value=FIVE}, Card{suit=CLUB, value=SIX}, Card{suit=CLUB, value=SEVEN}, Card{suit=CLUB, value=EIGHT}, Card{suit=CLUB, value=NINE}, Card{suit=CLUB, value=TEN}, Card{suit=CLUB, value=JACK}, Card{suit=CLUB, value=QUEEN}, Card{suit=CLUB, value=KING}, Card{suit=CLUB, value=ACE}, Card{suit=DIAMOND, value=TWO}, Card{suit=DIAMOND, value=THREE}, Card{suit=DIAMOND, value=FOUR}, Card{suit=DIAMOND, value=FIVE}, Card{suit=DIAMOND, value=SIX}, Card{suit=DIAMOND, value=SEVEN}, Card{suit=DIAMOND, value=EIGHT}, Card{suit=DIAMOND, value=NINE}, Card{suit=DIAMOND, value=TEN}, Card{suit=DIAMOND, value=JACK}, Card{suit=DIAMOND, value=QUEEN}, Card{suit=DIAMOND, value=KING}, Card{suit=DIAMOND, value=ACE}, Card{suit=HEART, value=TWO}, Card{suit=HEART, value=THREE}, Card{suit=HEART, value=FOUR}, Card{suit=HEART, value=FIVE}, Card{suit=HEART, value=SIX}, Card{suit=HEART, value=SEVEN}, Card{suit=HEART, value=EIGHT}, Card{suit=HEART, value=NINE}, Card{suit=HEART, value=TEN}, Card{suit=HEART, value=JACK}, Card{suit=HEART, value=QUEEN}, Card{suit=HEART, value=KING}, Card{suit=HEART, value=ACE}, Card{suit=SPADE, value=TWO}, Card{suit=SPADE, value=THREE}, Card{suit=SPADE, value=FOUR}, Card{suit=SPADE, value=FIVE}, Card{suit=SPADE, value=SIX}, Card{suit=SPADE, value=SEVEN}, Card{suit=SPADE, value=EIGHT}, Card{suit=SPADE, value=NINE}, Card{suit=SPADE, value=TEN}, Card{suit=SPADE, value=JACK}, Card{suit=SPADE, value=QUEEN}, Card{suit=SPADE, value=KING}, Card{suit=SPADE, value=ACE}]}", testDeck.toString());
    }
}