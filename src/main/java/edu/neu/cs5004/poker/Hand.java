package edu.neu.cs5004.poker;

import java.util.*;

public class Hand implements Comparable<Hand> {
    private final List<Card> cards;
    private final Type type;

    public Hand(List<Card> cards) {
        if (cards.size() != 5) {
            throw new IllegalArgumentException("All hands must have exactly 5 cards.");
        };
        this.cards = new ArrayList<>(cards);
        this.cards.sort(Comparator.comparing(Card::getValue).reversed());
        this.type = determineType();
    }

    private Type determineType() {
        if (verifyRoyalFlush()) {
            return Type.ROYAL_FLUSH;
        } else if (verifyStraightFlush()) {
            return Type.STRAIGHT_FLUSH;
        } else if (verifyFourOfAKind()) {
            return Type.FOUR_OF_A_KIND;
        } else if (verifyFullHouse()) {
            return Type.FULL_HOUSE;
        } else if (verifyFlush()) {
            return Type.FLUSH;
        } else if (verifyStraight()) {
            return Type.STRAIGHT;
        } else if (verifyThreeOfAKind()) {
            return Type.THREE_OF_A_KIND;
        } else if (verifyTwoPair()) {
            return Type.TWO_PAIR;
        } else if (verifyPair()) {
            return Type.PAIR;
        } else {
            return Type.SINGLE;
        }
    }
    private boolean verifyRoyalFlush() {
        return verifyStraightFlush() && cards.get(0).getValue() == Card.Value.ACE;
    }

    private boolean verifyStraightFlush() {
        return verifyFlush() && verifyStraight();
    }

    private boolean verifyFourOfAKind() {
        return cards.get(0).getValue() == cards.get(3).getValue() || cards.get(1).getValue() == cards.get(4).getValue();
    }

    private boolean verifyFullHouse() {
        return (cards.get(0).getValue() == cards.get(2).getValue() && cards.get(3).getValue() == cards.get(4).getValue())
                || (cards.get(0).getValue() == cards.get(1).getValue() && cards.get(2).getValue() == cards.get(4).getValue());
    }

    private boolean verifyFlush() {
        for (int i = 0; i < cards.size() - 1; i++) {
            if(cards.get(i).getSuit() != cards.get(i + 1).getSuit()) {
                return false;
            }
        }
        return true;
    }

    private boolean verifyStraight() {
        boolean result = true;
        for (int i = 0; i < cards.size() - 1; i++) {
            if (cards.get(i).getValue().compareTo(cards.get(i + 1).getValue()) != 1){
                result = false;
            }
        }
        if (result){
            return true;
        } else {
            return cards.get(0).getValue() == Card.Value.ACE && cards.get(1).getValue() == Card.Value.FIVE
            && cards.get(2).getValue() == Card.Value.FOUR && cards.get(3).getValue() == Card.Value.THREE
            && cards.get(4).getValue() == Card.Value.TWO;
        }
    }

    private boolean verifyThreeOfAKind() {
        int firstPairIndex = findPair(0);
        if (firstPairIndex < cards.size() - 1 && firstPairIndex >= 0) {
            return cards.get(firstPairIndex).getValue() == cards.get(firstPairIndex + 1).getValue();
        }
        return false;
    }

    private boolean verifyTwoPair() {
        int firstPairIndex = findPair(0);
        return findPair(firstPairIndex + 1) >= 0;
    }

    private boolean verifyPair() {
        return findPair(0) >= 0;
    }

    private int findPair(int startingIndex) {
        for (int i = startingIndex; i < cards.size()-1; i++) {
            if (cards.get(i).getValue() == cards.get(i+1).getValue()) {
                return i + 1;
            }
        }
        return -1;
    }

    public List<Card> getCards() {
        return cards;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Hand{" +
                "cards=" + cards +
                ", type=" + type +
                "}\n";
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards, type);
    }

    @Override
    public int compareTo(Hand o) {
        if (this.getType() != o.getType()){
            return this.getType().compareTo(o.getType());
        } else {
            return compareHandsWithSameType(this, o);
        }
    }

    private int compareHandsWithSameType(Hand handOne, Hand handTwo) {
        Hand.Type handType = handOne.getType();
        if (handType == Type.ROYAL_FLUSH){
            return 0;
        } else if (handType == Type.STRAIGHT_FLUSH){
            return compareAtIndex(handOne, handTwo, 0);
        } else if (handType == Type.FOUR_OF_A_KIND) {
            return compareAtIndex(handOne, handTwo, 2);
        } else if (handType == Type.FULL_HOUSE) {
            return compareAtIndex(handOne, handTwo, 2);
        } else if (handType == Type.FLUSH) {
            return compareEachValue(handOne, handTwo);
        } else if (handType == Type.STRAIGHT) {
            return compareAtIndex(handOne, handTwo, 0);
        } else if (handType == Type.THREE_OF_A_KIND) {
            return compareAtIndex(handOne, handTwo, 2);
        } else if (handType == Type.TWO_PAIR) {
            return compareTwoPairHands(handOne, handTwo);
        } else if (handType == Type.PAIR) {
            return comparePairHands(handOne, handTwo);
        } else {
            return compareEachValue(handOne, handTwo);
        }
    }

    private int compareAtIndex(Hand handOne, Hand handTwo, int index) {
        return handOne.getCards().get(index).getValue().compareTo(handTwo.getCards().get(index).getValue());
    }

    private int compareEachValue(Hand handOne, Hand handTwo) {
        for (int i = 0; i < handOne.getCards().size(); i++) {
            int result = handOne.getCards().get(i).getValue().compareTo(handTwo.getCards().get(i).getValue());
            if (result != 0){
                return result;
            }
        }
        return 0;
    }

    private int compareTwoPairHands(Hand handOne, Hand handTwo) {
        Card.Value handOneHighPairValue = handOne.getCards().get(1).getValue();
        Card.Value handTwoHighPairValue = handTwo.getCards().get(1).getValue();
        int resultHighPair = handOneHighPairValue.compareTo(handTwoHighPairValue);
        if (resultHighPair != 0){
            return resultHighPair;
        }
        Card.Value handOneLowPairValue = handOne.getCards().get(3).getValue();
        Card.Value handTwoLowPairValue = handTwo.getCards().get(3).getValue();
        int resultLowPair = handOneLowPairValue.compareTo(handTwoLowPairValue);
        if (resultLowPair != 0){
            return resultLowPair;
        }
        Card.Value handOneSingleValue = findSingleCardOfTwoPairHand(handOne);
        Card.Value handTwoSingleValue = findSingleCardOfTwoPairHand(handTwo);
        return handOneSingleValue.compareTo(handTwoSingleValue);
    }

    private int comparePairHands(Hand handOne, Hand handTwo) {
        Card.Value handOnePairValue = findValueOfPair(handOne);
        Card.Value handTwoPairValue = findValueOfPair(handTwo);
        int result = handOnePairValue.compareTo(handTwoPairValue);
        if (result == 0){
            return compareEachValue(handOne, handTwo);
        }
        return result;
    }

    private Card.Value findValueOfPair(Hand hand) {
        for (int i = 0; i < hand.getCards().size() - 1; i++) {
            int result = hand.getCards().get(i).getValue().compareTo(hand.getCards().get(i + 1).getValue());
            if (result == 0){
                return hand.getCards().get(i).getValue();
            }
        }
        // Did not test as it should never happen
        throw new IllegalStateException("No pair found in hand");
    }

    private Card.Value findSingleCardOfTwoPairHand(Hand hand) {
        for (int i = 0; i < hand.getCards().size(); i += 2) {
            if (hand.getCards().get(i).getValue() != hand.getCards().get(1).getValue()
                    && hand.getCards().get(i).getValue() != hand.getCards().get(3).getValue()) {
                return hand.getCards().get(i).getValue();
            }
        }
        // Did not test as it should never happen
        throw new IllegalStateException("No single card found in two pair hand");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hand hand = (Hand) o;
        return Objects.equals(cards, hand.cards) && type == hand.type;
    }

    public enum Type {
        SINGLE,
        PAIR,
        TWO_PAIR,
        THREE_OF_A_KIND,
        STRAIGHT,
        FLUSH,
        FULL_HOUSE,
        FOUR_OF_A_KIND,
        STRAIGHT_FLUSH,
        ROYAL_FLUSH
    }




}
