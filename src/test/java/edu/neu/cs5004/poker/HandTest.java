package edu.neu.cs5004.poker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HandTest {
    private List<Card> cards;
    private Hand hand;
    private Hand hand2;

    @BeforeEach
    void setUp() {
        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.CLUB, Card.Value.TWO));
        cards.add(new Card(Card.Suit.CLUB, Card.Value.THREE));
        cards.add(new Card(Card.Suit.CLUB, Card.Value.FOUR));
        cards.add(new Card(Card.Suit.CLUB, Card.Value.FIVE));
        cards.add(new Card(Card.Suit.CLUB, Card.Value.SIX));
        hand = new Hand(cards);
        hand2 = new Hand(cards);
    }

    @Test
    void getCardsTestSize() {
        assertEquals(5, hand.getCards().size());
    }

    @Test
    void testIllegalArgumentException() {
        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.DIAMOND, Card.Value.THREE));
        cards.add(new Card(Card.Suit.CLUB, Card.Value.ACE));
        cards.add(new Card(Card.Suit.SPADE, Card.Value.TEN));
        cards.add(new Card(Card.Suit.HEART, Card.Value.FIVE));
        cards.add(new Card(Card.Suit.CLUB, Card.Value.TWO));
        cards.add(new Card(Card.Suit.CLUB, Card.Value.JACK));

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> new Hand(cards));
        assertTrue(thrown.getMessage().contentEquals("All hands must have exactly 5 cards."));

    }

    @Test
    void getCardsTestSort() {
        for (int i = 0; i < hand.getCards().size() - 1; i++) {
            assertTrue(hand.getCards().get(i).getValue().compareTo(hand.getCards().get(i + 1).getValue()) >= 0);
        }
    }

    @Test
    void getTypeTestStraightFlush() {
        assertEquals(Hand.Type.STRAIGHT_FLUSH, hand.getType());
    }

    @Test
    void getTypeTestRoyalFlush() {
        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.CLUB, Card.Value.ACE));
        cards.add(new Card(Card.Suit.CLUB, Card.Value.KING));
        cards.add(new Card(Card.Suit.CLUB, Card.Value.QUEEN));
        cards.add(new Card(Card.Suit.CLUB, Card.Value.JACK));
        cards.add(new Card(Card.Suit.CLUB, Card.Value.TEN));
        hand = new Hand(cards);

        assertEquals(Hand.Type.ROYAL_FLUSH, hand.getType());
    }

    @Test
    void getTypeTestFourOfAKind() {
        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.CLUB, Card.Value.TWO));
        cards.add(new Card(Card.Suit.DIAMOND, Card.Value.TWO));
        cards.add(new Card(Card.Suit.HEART, Card.Value.TWO));
        cards.add(new Card(Card.Suit.SPADE, Card.Value.TWO));
        cards.add(new Card(Card.Suit.CLUB, Card.Value.SIX));
        hand = new Hand(cards);

        assertEquals(Hand.Type.FOUR_OF_A_KIND, hand.getType());
    }

    @Test
    void getTypeTestFullHouse() {
        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.CLUB, Card.Value.TWO));
        cards.add(new Card(Card.Suit.DIAMOND, Card.Value.TWO));
        cards.add(new Card(Card.Suit.HEART, Card.Value.TWO));
        cards.add(new Card(Card.Suit.SPADE, Card.Value.SIX));
        cards.add(new Card(Card.Suit.CLUB, Card.Value.SIX));
        hand = new Hand(cards);

        assertEquals(Hand.Type.FULL_HOUSE, hand.getType());
    }

    @Test
    void getTypeTestFlush() {
        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.CLUB, Card.Value.JACK));
        cards.add(new Card(Card.Suit.CLUB, Card.Value.THREE));
        cards.add(new Card(Card.Suit.CLUB, Card.Value.ACE));
        cards.add(new Card(Card.Suit.CLUB, Card.Value.FIVE));
        cards.add(new Card(Card.Suit.CLUB, Card.Value.SIX));
        hand = new Hand(cards);

        assertEquals(Hand.Type.FLUSH, hand.getType());
    }

    @Test
    void getTypeTestStraight() {
        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.CLUB, Card.Value.TWO));
        cards.add(new Card(Card.Suit.DIAMOND, Card.Value.THREE));
        cards.add(new Card(Card.Suit.HEART, Card.Value.FOUR));
        cards.add(new Card(Card.Suit.SPADE, Card.Value.FIVE));
        cards.add(new Card(Card.Suit.CLUB, Card.Value.SIX));
        hand = new Hand(cards);

        assertEquals(Hand.Type.STRAIGHT, hand.getType());
    }

    @Test
    void getTypeTestLowStraight() {
        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.CLUB, Card.Value.FIVE));
        cards.add(new Card(Card.Suit.DIAMOND, Card.Value.FOUR));
        cards.add(new Card(Card.Suit.CLUB, Card.Value.ACE));
        cards.add(new Card(Card.Suit.HEART, Card.Value.TWO));
        cards.add(new Card(Card.Suit.SPADE, Card.Value.THREE));
        hand = new Hand(cards);

        assertEquals(Hand.Type.STRAIGHT, hand.getType());
    }

    @Test
    void getTypeTestThreeOfAKind() {
        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.CLUB, Card.Value.TWO));
        cards.add(new Card(Card.Suit.DIAMOND, Card.Value.TWO));
        cards.add(new Card(Card.Suit.HEART, Card.Value.TWO));
        cards.add(new Card(Card.Suit.SPADE, Card.Value.FIVE));
        cards.add(new Card(Card.Suit.CLUB, Card.Value.SIX));
        hand = new Hand(cards);

        assertEquals(Hand.Type.THREE_OF_A_KIND, hand.getType());
    }

    @Test
    void getTypeTestTwoPair() {
        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.CLUB, Card.Value.TWO));
        cards.add(new Card(Card.Suit.DIAMOND, Card.Value.TWO));
        cards.add(new Card(Card.Suit.HEART, Card.Value.FIVE));
        cards.add(new Card(Card.Suit.SPADE, Card.Value.FIVE));
        cards.add(new Card(Card.Suit.CLUB, Card.Value.SIX));
        hand = new Hand(cards);

        assertEquals(Hand.Type.TWO_PAIR, hand.getType());
    }

    @Test
    void getTypeTestPair() {
        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.CLUB, Card.Value.TWO));
        cards.add(new Card(Card.Suit.DIAMOND, Card.Value.TWO));
        cards.add(new Card(Card.Suit.HEART, Card.Value.FOUR));
        cards.add(new Card(Card.Suit.SPADE, Card.Value.FIVE));
        cards.add(new Card(Card.Suit.CLUB, Card.Value.SIX));
        hand = new Hand(cards);

        assertEquals(Hand.Type.PAIR, hand.getType());
    }

    @Test
    void getTypeTestHighCard() {
        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.CLUB, Card.Value.TWO));
        cards.add(new Card(Card.Suit.DIAMOND, Card.Value.THREE));
        cards.add(new Card(Card.Suit.HEART, Card.Value.ACE));
        cards.add(new Card(Card.Suit.SPADE, Card.Value.FIVE));
        cards.add(new Card(Card.Suit.CLUB, Card.Value.SIX));
        hand = new Hand(cards);

        assertEquals(Hand.Type.SINGLE, hand.getType());
    }

    @Test
    void testToString() {
        assertEquals("Hand{cards=[Card{suit=CLUB, value=SIX}, Card{suit=CLUB, value=FIVE}, Card{suit=CLUB, value=FOUR}, Card{suit=CLUB, value=THREE}, Card{suit=CLUB, value=TWO}], type=STRAIGHT_FLUSH}\n", hand.toString());
    }

    @Test
    void testHashCode() {
        assertEquals(hand.hashCode(), hand.hashCode());
    }

    @Test
    void compareToSameHand() {
        assertEquals(0, hand.compareTo(hand2));
    }
    
    @Test
    void compareToTwoDifferentHands() {
        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.CLUB, Card.Value.TWO));
        cards.add(new Card(Card.Suit.DIAMOND, Card.Value.THREE));
        cards.add(new Card(Card.Suit.HEART, Card.Value.ACE));
        cards.add(new Card(Card.Suit.SPADE, Card.Value.FIVE));
        cards.add(new Card(Card.Suit.CLUB, Card.Value.SIX));
        Hand testHand = new Hand(cards);
        
        assertEquals(8,hand.compareTo(testHand));
    }

    @Test
    void compareToSameHandsDifferentValue() {
        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.CLUB, Card.Value.THREE));
        cards.add(new Card(Card.Suit.CLUB, Card.Value.FOUR));
        cards.add(new Card(Card.Suit.CLUB, Card.Value.FIVE));
        cards.add(new Card(Card.Suit.CLUB, Card.Value.SIX));
        cards.add(new Card(Card.Suit.CLUB, Card.Value.SEVEN));
        Hand testHand = new Hand(cards);

        assertEquals(-1, hand.compareTo(testHand));
    }

    @Test
    void compareToTwoRoyalFlushes() {
        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.CLUB, Card.Value.ACE));
        cards.add(new Card(Card.Suit.CLUB, Card.Value.KING));
        cards.add(new Card(Card.Suit.CLUB, Card.Value.QUEEN));
        cards.add(new Card(Card.Suit.CLUB, Card.Value.JACK));
        cards.add(new Card(Card.Suit.CLUB, Card.Value.TEN));
        Hand testHand = new Hand(cards);

        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.DIAMOND, Card.Value.ACE));
        cards.add(new Card(Card.Suit.DIAMOND, Card.Value.KING));
        cards.add(new Card(Card.Suit.DIAMOND, Card.Value.QUEEN));
        cards.add(new Card(Card.Suit.DIAMOND, Card.Value.JACK));
        cards.add(new Card(Card.Suit.DIAMOND, Card.Value.TEN));

        Hand testHand2 = new Hand(cards);

        assertEquals(0, testHand.compareTo(testHand2));
    }

    @Test
    void compareToTwoFourOfAKind() {
        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.CLUB, Card.Value.ACE));
        cards.add(new Card(Card.Suit.DIAMOND, Card.Value.ACE));
        cards.add(new Card(Card.Suit.HEART, Card.Value.ACE));
        cards.add(new Card(Card.Suit.SPADE, Card.Value.ACE));
        cards.add(new Card(Card.Suit.CLUB, Card.Value.TEN));
        Hand testHand = new Hand(cards);

        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.CLUB, Card.Value.KING));
        cards.add(new Card(Card.Suit.DIAMOND, Card.Value.KING));
        cards.add(new Card(Card.Suit.HEART, Card.Value.KING));
        cards.add(new Card(Card.Suit.SPADE, Card.Value.KING));
        cards.add(new Card(Card.Suit.CLUB, Card.Value.TEN));
        Hand testHand2 = new Hand(cards);

        assertEquals(1, testHand.compareTo(testHand2));
    }

    @Test
    void compareToTwoFullHouse() {
        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.CLUB, Card.Value.ACE));
        cards.add(new Card(Card.Suit.DIAMOND, Card.Value.ACE));
        cards.add(new Card(Card.Suit.HEART, Card.Value.ACE));
        cards.add(new Card(Card.Suit.SPADE, Card.Value.TEN));
        cards.add(new Card(Card.Suit.CLUB, Card.Value.TEN));
        Hand testHand = new Hand(cards);

        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.CLUB, Card.Value.KING));
        cards.add(new Card(Card.Suit.DIAMOND, Card.Value.KING));
        cards.add(new Card(Card.Suit.HEART, Card.Value.KING));
        cards.add(new Card(Card.Suit.SPADE, Card.Value.TEN));
        cards.add(new Card(Card.Suit.CLUB, Card.Value.TEN));
        Hand testHand2 = new Hand(cards);

        assertEquals(1, testHand.compareTo(testHand2));
    }

    @Test
    void compareToTwoFlushes() {
        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.CLUB, Card.Value.NINE));
        cards.add(new Card(Card.Suit.CLUB, Card.Value.KING));
        cards.add(new Card(Card.Suit.CLUB, Card.Value.THREE));
        cards.add(new Card(Card.Suit.CLUB, Card.Value.JACK));
        cards.add(new Card(Card.Suit.CLUB, Card.Value.TEN));
        Hand testHand = new Hand(cards);

        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.DIAMOND, Card.Value.ACE));
        cards.add(new Card(Card.Suit.DIAMOND, Card.Value.KING));
        cards.add(new Card(Card.Suit.DIAMOND, Card.Value.FIVE));
        cards.add(new Card(Card.Suit.DIAMOND, Card.Value.JACK));
        cards.add(new Card(Card.Suit.DIAMOND, Card.Value.TEN));
        Hand testHand2 = new Hand(cards);

        assertEquals(-1, testHand.compareTo(testHand2));
    }

    @Test
    void compareToTwoStraightsSameValue() {
        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.CLUB, Card.Value.NINE));
        cards.add(new Card(Card.Suit.DIAMOND, Card.Value.TEN));
        cards.add(new Card(Card.Suit.HEART, Card.Value.JACK));
        cards.add(new Card(Card.Suit.SPADE, Card.Value.QUEEN));
        cards.add(new Card(Card.Suit.CLUB, Card.Value.KING));
        Hand testHand = new Hand(cards);

        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.CLUB, Card.Value.TEN));
        cards.add(new Card(Card.Suit.DIAMOND, Card.Value.JACK));
        cards.add(new Card(Card.Suit.HEART, Card.Value.QUEEN));
        cards.add(new Card(Card.Suit.SPADE, Card.Value.KING));
        cards.add(new Card(Card.Suit.CLUB, Card.Value.NINE));
        Hand testHand2 = new Hand(cards);

        assertEquals(0, testHand.compareTo(testHand2));
    }

    @Test
    void compareToTwoStraightsDifferentValue() {
        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.CLUB, Card.Value.NINE));
        cards.add(new Card(Card.Suit.DIAMOND, Card.Value.TEN));
        cards.add(new Card(Card.Suit.HEART, Card.Value.JACK));
        cards.add(new Card(Card.Suit.SPADE, Card.Value.QUEEN));
        cards.add(new Card(Card.Suit.CLUB, Card.Value.KING));
        Hand testHand = new Hand(cards);

        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.CLUB, Card.Value.TEN));
        cards.add(new Card(Card.Suit.DIAMOND, Card.Value.JACK));
        cards.add(new Card(Card.Suit.HEART, Card.Value.QUEEN));
        cards.add(new Card(Card.Suit.SPADE, Card.Value.KING));
        cards.add(new Card(Card.Suit.CLUB, Card.Value.ACE));
        Hand testHand2 = new Hand(cards);

        assertEquals(-1, testHand.compareTo(testHand2));
    }

    @Test
    void compareToTwoThreeOfAKind() {
        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.CLUB, Card.Value.ACE));
        cards.add(new Card(Card.Suit.DIAMOND, Card.Value.ACE));
        cards.add(new Card(Card.Suit.HEART, Card.Value.ACE));
        cards.add(new Card(Card.Suit.SPADE, Card.Value.TEN));
        cards.add(new Card(Card.Suit.CLUB, Card.Value.TWO));
        Hand testHand = new Hand(cards);

        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.CLUB, Card.Value.KING));
        cards.add(new Card(Card.Suit.DIAMOND, Card.Value.KING));
        cards.add(new Card(Card.Suit.HEART, Card.Value.KING));
        cards.add(new Card(Card.Suit.SPADE, Card.Value.TEN));
        cards.add(new Card(Card.Suit.CLUB, Card.Value.TWO));
        Hand testHand2 = new Hand(cards);

        assertEquals(1, testHand.compareTo(testHand2));
    }

    @Test
    void compareToTwoTwoPairSameValue() {
        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.HEART, Card.Value.ACE));
        cards.add(new Card(Card.Suit.SPADE, Card.Value.ACE));
        cards.add(new Card(Card.Suit.DIAMOND, Card.Value.TEN));
        cards.add(new Card(Card.Suit.CLUB, Card.Value.TEN));
        cards.add(new Card(Card.Suit.HEART, Card.Value.TWO));
        Hand testHand = new Hand(cards);

        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.CLUB, Card.Value.ACE));
        cards.add(new Card(Card.Suit.DIAMOND, Card.Value.ACE));
        cards.add(new Card(Card.Suit.HEART, Card.Value.TEN));
        cards.add(new Card(Card.Suit.SPADE, Card.Value.TEN));
        cards.add(new Card(Card.Suit.CLUB, Card.Value.TWO));
        Hand testHand2 = new Hand(cards);

        assertEquals(0, testHand.compareTo(testHand2));
    }

    @Test
    void compareToTwoTwoPairDifferentLowPairValue() {
        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.HEART, Card.Value.ACE));
        cards.add(new Card(Card.Suit.SPADE, Card.Value.ACE));
        cards.add(new Card(Card.Suit.DIAMOND, Card.Value.TEN));
        cards.add(new Card(Card.Suit.CLUB, Card.Value.TEN));
        cards.add(new Card(Card.Suit.HEART, Card.Value.TWO));
        Hand testHand = new Hand(cards);

        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.CLUB, Card.Value.ACE));
        cards.add(new Card(Card.Suit.DIAMOND, Card.Value.ACE));
        cards.add(new Card(Card.Suit.HEART, Card.Value.KING));
        cards.add(new Card(Card.Suit.SPADE, Card.Value.KING));
        cards.add(new Card(Card.Suit.CLUB, Card.Value.TWO));
        Hand testHand2 = new Hand(cards);

        assertEquals(-3, testHand.compareTo(testHand2));
    }

    @Test
    void compareToTwoTwoPairDifferentHighPairValue() {
        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.HEART, Card.Value.ACE));
        cards.add(new Card(Card.Suit.SPADE, Card.Value.ACE));
        cards.add(new Card(Card.Suit.DIAMOND, Card.Value.TEN));
        cards.add(new Card(Card.Suit.CLUB, Card.Value.TEN));
        cards.add(new Card(Card.Suit.HEART, Card.Value.TWO));
        Hand testHand = new Hand(cards);

        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.CLUB, Card.Value.KING));
        cards.add(new Card(Card.Suit.DIAMOND, Card.Value.KING));
        cards.add(new Card(Card.Suit.HEART, Card.Value.TEN));
        cards.add(new Card(Card.Suit.SPADE, Card.Value.TEN));
        cards.add(new Card(Card.Suit.CLUB, Card.Value.TWO));
        Hand testHand2 = new Hand(cards);

        assertEquals(1, testHand.compareTo(testHand2));
    }
    @Test
    void compareToTwoPairHandSamePairValuesDifferentSingleValue() {
        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.HEART, Card.Value.ACE));
        cards.add(new Card(Card.Suit.SPADE, Card.Value.ACE));
        cards.add(new Card(Card.Suit.DIAMOND, Card.Value.TEN));
        cards.add(new Card(Card.Suit.CLUB, Card.Value.TEN));
        cards.add(new Card(Card.Suit.HEART, Card.Value.TWO));
        Hand testHand = new Hand(cards);

        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.CLUB, Card.Value.ACE));
        cards.add(new Card(Card.Suit.DIAMOND, Card.Value.ACE));
        cards.add(new Card(Card.Suit.HEART, Card.Value.TEN));
        cards.add(new Card(Card.Suit.SPADE, Card.Value.TEN));
        cards.add(new Card(Card.Suit.CLUB, Card.Value.THREE));
        Hand testHand2 = new Hand(cards);

        assertEquals(-1, testHand.compareTo(testHand2));
    }

    @Test
    void compareToPairHandDifferentValue() {
        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.HEART, Card.Value.QUEEN));
        cards.add(new Card(Card.Suit.SPADE, Card.Value.KING));
        cards.add(new Card(Card.Suit.CLUB, Card.Value.THREE));
        cards.add(new Card(Card.Suit.DIAMOND, Card.Value.TWO));
        cards.add(new Card(Card.Suit.DIAMOND, Card.Value.QUEEN));
        Hand testHand = new Hand(cards);

        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.DIAMOND, Card.Value.TEN));
        cards.add(new Card(Card.Suit.DIAMOND, Card.Value.KING));
        cards.add(new Card(Card.Suit.HEART, Card.Value.TEN));
        cards.add(new Card(Card.Suit.SPADE, Card.Value.THREE));
        cards.add(new Card(Card.Suit.CLUB, Card.Value.TWO));
        Hand testHand2 = new Hand(cards);

        assertEquals(2, testHand.compareTo(testHand2));
    }

    @Test
    void compareToPairHandsSameValue() {
        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.HEART, Card.Value.QUEEN));
        cards.add(new Card(Card.Suit.SPADE, Card.Value.KING));
        cards.add(new Card(Card.Suit.CLUB, Card.Value.TEN));
        cards.add(new Card(Card.Suit.DIAMOND, Card.Value.THREE));
        cards.add(new Card(Card.Suit.SPADE, Card.Value.QUEEN));
        Hand testHand = new Hand(cards);

        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.DIAMOND, Card.Value.QUEEN));
        cards.add(new Card(Card.Suit.DIAMOND, Card.Value.KING));
        cards.add(new Card(Card.Suit.HEART, Card.Value.TEN));
        cards.add(new Card(Card.Suit.SPADE, Card.Value.THREE));
        cards.add(new Card(Card.Suit.CLUB, Card.Value.QUEEN));
        Hand testHand2 = new Hand(cards);

        assertEquals(0, testHand.compareTo(testHand2));
    }

    @Test
    void compareToSingleCardSameValue() {
        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.HEART, Card.Value.QUEEN));
        cards.add(new Card(Card.Suit.SPADE, Card.Value.KING));
        cards.add(new Card(Card.Suit.CLUB, Card.Value.TEN));
        cards.add(new Card(Card.Suit.DIAMOND, Card.Value.THREE));
        cards.add(new Card(Card.Suit.SPADE, Card.Value.TWO));
        Hand testHand = new Hand(cards);

        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.DIAMOND, Card.Value.QUEEN));
        cards.add(new Card(Card.Suit.DIAMOND, Card.Value.KING));
        cards.add(new Card(Card.Suit.HEART, Card.Value.TEN));
        cards.add(new Card(Card.Suit.SPADE, Card.Value.THREE));
        cards.add(new Card(Card.Suit.CLUB, Card.Value.TWO));
        Hand testHand2 = new Hand(cards);

        assertEquals(0, testHand.compareTo(testHand2));
    }

    @Test
    void compareToSingleCardDifferentValue() {
        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.HEART, Card.Value.QUEEN));
        cards.add(new Card(Card.Suit.SPADE, Card.Value.KING));
        cards.add(new Card(Card.Suit.CLUB, Card.Value.TEN));
        cards.add(new Card(Card.Suit.DIAMOND, Card.Value.THREE));
        cards.add(new Card(Card.Suit.SPADE, Card.Value.TWO));
        Hand testHand = new Hand(cards);

        cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.DIAMOND, Card.Value.QUEEN));
        cards.add(new Card(Card.Suit.DIAMOND, Card.Value.KING));
        cards.add(new Card(Card.Suit.HEART, Card.Value.TEN));
        cards.add(new Card(Card.Suit.SPADE, Card.Value.THREE));
        cards.add(new Card(Card.Suit.CLUB, Card.Value.ACE));
        Hand testHand2 = new Hand(cards);

        assertEquals(-1, testHand.compareTo(testHand2));
    }

    @Test
    void testEquals() {
        assertEquals(hand, hand2);
    }
}