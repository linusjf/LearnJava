package jls;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Deal {

  private Deal() {
    throw new IllegalStateException("Private constructor.");
  }

  public static void main(String... args) {
    int numHands = Integer.parseInt(args[0]);
    int cardsPerHand = Integer.parseInt(args[1]);
    List<Card> deck = Card.newDeck();
    Collections.shuffle(deck);
    for (int i = 0; i < numHands; i++)
      System.out.println(dealHand(deck, cardsPerHand));
  }

  /**
   * Returns a new ArrayList consisting of the last n elements of deck, which are removed from deck.
   * The returned list is sorted using the elements' natural ordering.
   *
   * @return list of hands
   */
  @SuppressWarnings("PMD.LawOfDemeter")
  public static <E extends Comparable<E>> List<E> dealHand(List<E> deck,
                                                           int n) {
    int deckSize = deck.size();
    List<E> handView = deck.subList(deckSize - n, deckSize);
    List<E> hand = new ArrayList<>(handView);
    handView.clear();
    Collections.sort(hand);
    return hand;
  }
}
