package a12030638;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Objects.hash;

public class Player implements Comparable<Player> {
   private String name;
   private Queue<VehicleCard> deck = new ArrayDeque<>();

   public Player(final String name) {
       if(name.isEmpty() || name == null) {
           throw new IllegalArgumentException("Name is invalid!");
       }
       this.name = name;
   }

    public String getName() { return name; }
    public int getScore() {
       return deck.stream().mapToInt(VehicleCard::totalBonus).sum();
    }

    public void addCards(final Collection<VehicleCard> cards) {
       deck.addAll(cards);
    }
    public void addCard(final VehicleCard card) {
       deck.add(card);
    }
    public void clearDeck() { deck.clear(); }
    public List<VehicleCard> getDeck() {
       return new ArrayList<>(deck);
    }
    protected VehicleCard peekNextCard() {
       return deck.peek();
    }
    public VehicleCard playNextCard() {
       return deck.poll();
    }

    public int compareTo(final Player other) {
       return getName().compareToIgnoreCase(other.getName());
    }

    @Override
    public int hashCode() { return hash(name.toLowerCase()); }
    @Override
    public boolean equals(Object obj) {
        return obj.getClass() == this.getClass()
                && Objects.equals(name, ((Player) obj).getName());
    }

    @Override
    public String toString() {
       return getName() + "(" + getScore() + "):\n" + deck.stream()
               .map(x -> "" + x.toString())
               .collect(Collectors.joining("\n"));
     }

     public boolean challengePlayer(Player p) {
        if (p == null || p.equals(this)) {
            throw new IllegalArgumentException("Player invalid!");
        }

        Collection<VehicleCard> table = new ArrayList<>();

        while(true) {
            int winner = this.peekNextCard().compareTo(p.peekNextCard());

            table.add(p.playNextCard());
            table.add(playNextCard());

            // logic
            if (winner < 0) p.addCards(table); // this lost
            else if (winner > 0) addCards(table); // this won
            else continue; // tie

            table.clear();
            return winner > 0;
        }
     }

    public static Comparator<Player> compareByScore() {
        return Comparator.comparingInt(Player::getScore);
    }
    public static Comparator<Player> compareByDeckSize() {
        return Comparator.comparingInt(o -> o.getDeck().size());
    }
}