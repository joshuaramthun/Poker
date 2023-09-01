package edu.neu.cs5004.poker;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Deck {
    private final List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        generateDeck();
    }

    private void generateDeck() {
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Value value : Card.Value.values()) {
                cards.add(new Card(suit, value));
            }
        }
    }

    public List<Hand> dealHands(int numHands) {
        if (numHands < 1 || numHands > 10) {
            throw new IllegalArgumentException("Number of hands must be between 1 and 10");
        }
        List<Hand> playersHand = new ArrayList<>();
        for (int i = 0; i < numHands; i++) {
            playersHand.add(dealCards());
        }

        return playersHand;
    }

    private Hand dealCards() {
        List<Card> playersHand = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Random rand = new Random();
            playersHand.add(cards.remove(rand.nextInt(cards.size())));
        }
        return new Hand(playersHand);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deck deck = (Deck) o;
        return cards.equals(deck.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }

    @Override
    public String toString() {
        return "Deck{" +
                "cards=" + cards +
                '}';
    }
}
