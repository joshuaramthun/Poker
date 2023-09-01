package edu.neu.cs5004.poker;

import java.util.Arrays;
import java.util.List;

public class Poker {


    /**
     * This function returns the highest hand in the given array.
     * @param hands An array of hands to compare
     * @return The highest hand
     */
    public Hand highestHand(Hand... hands) {
        List<Hand> sortedHands = Arrays.stream(hands).sorted().toList();

        return sortedHands.get(sortedHands.size() - 1);
    }
}
