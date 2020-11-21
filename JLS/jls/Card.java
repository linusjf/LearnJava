package jls;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

public final class Card implements Comparable<Card>, java.io.Serializable {
  private static final List<Card> PROTOTYPE_DECK = new ArrayList<>(52);
  private static final long serialVersionUID = 1L;
  private final Rank thisRank;
  private final Suit thisSuit;

  static {
    for (Suit suit: Suit.values())
      for (Rank rank: Rank.values())
        PROTOTYPE_DECK.add(new Card(rank, suit));
  }

  public enum Rank {
    DEUCE,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    NINE,
    TEN,
    JACK,
    QUEEN,
    KING,
    ACE
  }

  public enum Suit { CLUBS, DIAMONDS, HEARTS, SPADES }

  private Card(Rank rank, Suit suit) {
    requireNonNull(rank);
    requireNonNull(suit);
    thisRank = rank;
    thisSuit = suit;
  }

  public Rank rank() {
    return thisRank;
  }

  public Suit suit() {
    return thisSuit;
  }

  @Override
  public String toString() {
    return thisRank + " of " + thisSuit;
  }

  // Primary sort on suit, secondary sort on rank
  @Override
  public int compareTo(Card c) {
    int suitCompare = thisSuit.compareTo(c.thisSuit);
    return suitCompare == 0 ? thisRank.compareTo(c.thisRank) : suitCompare;
  }

  // Returns a new deck
  public static List<Card> newDeck() {
    return new ArrayList<Card>(PROTOTYPE_DECK);
  }
}
