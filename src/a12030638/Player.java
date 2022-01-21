package a12030638;

import java.util.*;
import java.util.stream.Collectors;

public class Player implements Comparable<Player> {
	private String name;
	private Strategy strategy;
	private Queue<VehicleCard> deck = new ArrayDeque<>();

	public Player(final String name) {
		if (name == null || name.isEmpty())
			throw new IllegalArgumentException("Player: name invalid");
		this.name = name;
		this.strategy = new RndStrategy();
	}
	public Player(final String name, final Strategy strategy) {
		if (name == null || name.isEmpty())
			throw new IllegalArgumentException("Player: name invalid");
		if (strategy == null)
			throw new IllegalArgumentException("Player: strategy invalid");
		this.name = name;
		this.strategy = strategy;
	}

	public String getName() {
		return name;
	}

	public int getScore() {
		return deck.stream().mapToInt(VehicleCard::totalBonus).sum();
	}

	public void addCards(final Collection<VehicleCard> cards) {
		deck.addAll(cards);
	}

	public void addCard(final VehicleCard card) {
		deck.add(card);
	}

	public void clearDeck() {
		deck.clear();
	}

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
	public int hashCode() {
		return getName().toLowerCase().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return obj.getClass() == this.getClass() && getName().equalsIgnoreCase(((Player) obj).getName());
	}

	@Override
	public String toString() {
		return getName() + "(" + getScore() + "):\n"
				+ deck.stream().map(x -> "" + x.toString()).collect(Collectors.joining("\n"));
	}

	public boolean challengePlayer(Player p) {
		if (p == null || p.equals(this)) {
			throw new IllegalArgumentException("Player: player null or equal to other p");
		}

		Collection<VehicleCard> table = new ArrayList<>();
		Collection<VehicleCard> this_temp = new ArrayList<>();
		Collection<VehicleCard> p_temp = new ArrayList<>();

		while (true) {
			if (getDeck().isEmpty() || p.getDeck().isEmpty()) {
				addCards(this_temp);
				p.addCards(p_temp);
				return false;
			}
			int winner = this.peekNextCard().compareTo(p.peekNextCard());

			this_temp.add(playNextCard());
			p_temp.add(p.playNextCard());

			if(winner != 0) {
				table.addAll(this_temp);
				table.addAll(p_temp);
			}

			// logic
			if (winner < 0)
				p.addCards(table); // this lost
			else if (winner > 0)
				addCards(table); // this won
			else
				continue; // tie

			return winner > 0;
		}
	}

	public VehicleCard.Category chooseNextCategory() {
		return strategy.chooseCategory(peekNextCard());
	}

	public Strategy getStrategy() {
		return strategy;
	}

	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}

	public static Comparator<Player> compareByScore() {
		return Comparator.comparingInt(Player::getScore);
	}

	public static Comparator<Player> compareByDeckSize() {
		return Comparator.comparingInt(o -> o.getDeck().size());
	}
}
