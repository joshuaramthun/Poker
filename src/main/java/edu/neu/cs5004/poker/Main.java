package edu.neu.cs5004.poker;


import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        Card cardA = new Card(Card.Suit.DIAMOND, Card.Value.KING);
//        Card cardB = new Card(Card.Suit.DIAMOND, Card.Value.JACK);
//        Card cardC = new Card(Card.Suit.CLUB, Card.Value.JACK);
//        Card cardD = new Card(Card.Suit.CLUB, Card.Value.KING);
//        Card cardE = new Card(Card.Suit.CLUB, Card.Value.QUEEN);
//        Hand pokerHand = new Hand(List.of(cardA, cardB, cardC, cardD, cardE));
//        System.out.println(pokerHand);

//        Card.Value x = Card.Value.SIX;
//        Card.Value y = Card.Value.ACE;
//        int value = y.compareTo(x);
//        System.out.println(Card.Value.NINE.compareTo(Card.Value.EIGHT) == 1);


        Poker poker = new Poker();
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

        Hand winningHand = poker.highestHand(handA, handB);
        System.out.println(winningHand);


//        List<Hand> newGame = poker.startNewGame(3);
//        System.out.println(newGame);
//        System.out.println(poker.highestHand(newGame.get(0), newGame.get(1), newGame.get(2)));


    }



}
