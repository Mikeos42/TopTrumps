package a11920352;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

public class Player implements Comparable<Player> {
	private String name;
	private Queue<VehicleCard> deck = new ArrayDeque<>();
	private Strategy strategy;
	
	public Player(final String name) {//throw IllegalArgumentsException if the name is null or empty
		if (name == null || name.isEmpty())
			throw new IllegalArgumentException("Player: name is empty or null.");
		this.name = name;
		this.strategy = new RndStrategy();
	}

	public Player(final String name, final Strategy strategy) {//throw IllegalArgumentsException if the name is null or empty
		if (name == null || name.isEmpty())
			throw new IllegalArgumentException("Player: name is empty or null.");
		this.name = name;
		this.strategy = strategy;
	}
	
	public String getName() {return name;}
	public int getScore() {/*return sum of totalBonus of deck's cards (maybe negative)*/
		int res = 0;
		for(VehicleCard el : deck)
			res += el.totalBonus();
		return res; //deck.stream().mapToInt(VehicleCard::totalBonus).sum();
	}
	
	public void addCards(final Collection<VehicleCard> cards) {/*add cards to end*/
		if(cards == null || cards.stream().anyMatch(Objects::isNull))
			throw new IllegalArgumentException("cards is null or contains null");
		deck.addAll(cards);
	}
	public void addCard(final VehicleCard card) {/*add card to end*/
		if (card == null)
			throw new IllegalArgumentException("card is null");
		deck.add(card);
	}
	public void clearDeck() {/*clear Queue*/
		deck.clear();
	}
	public List<VehicleCard> getDeck(){/*returns a shallow (!) copy of this.deck*/
		return new ArrayList<>(deck);
	}
	protected VehicleCard peekNextCard() {/*peek next card*/
		return deck.peek();
	}
	public VehicleCard playNextCard() {/*poll next card from deck*/
		return deck.poll();
	}
	public int compareTo(final Player other) {//compare by name[case insensitive]
		return name.compareToIgnoreCase(other.name); //oder .getName()
	}
	@Override
	public int hashCode() {/* hash ( name [ case insensitive ]) */
		return name.toLowerCase().hashCode();
	}
	@Override
	public boolean equals(Object obj) {/*auto generate but cmp name case insensitive*/
		return obj != null && getClass() == obj.getClass() && name.equalsIgnoreCase(((Player) obj).getName());
	}
	
	@Override
	public String toString() {
		String retstr = name + "(" + this.getScore() + "):\n";
		boolean first = true;
		for(VehicleCard el : deck) {
			if(first) {
				first = false;
			} else {
				retstr += "\n";
			}
			retstr += el.toString();
		}
		return retstr;
	}
	public boolean challengePlayer(Player p) {
		if (p==null || this.compareTo(p)==0)
			throw new IllegalArgumentException("challengePlayer: invalid p");
		if(p.getDeck().isEmpty() || this.getDeck().isEmpty()) {
			return false;
		}
		
		Collection<VehicleCard> tCards = new ArrayList<>();
		Collection<VehicleCard> pCards = new ArrayList<>();
		
		while(true) {
			int diff = this.peekNextCard().compareTo(p.peekNextCard());
			
			tCards.add(this.playNextCard());
			pCards.add(p.playNextCard());
			
			if(diff > 0) { //this won
				this.addCards(tCards);
				this.addCards(pCards);
				return true;
			} 
			else if(diff < 0) { //p won
				p.addCards(pCards);
				p.addCards(tCards);
				return false;
			}
			else { //tie
				if(p.getDeck().isEmpty() || this.getDeck().isEmpty()) {
					p.addCards(pCards);
					this.addCards(tCards);
					return false;
				}
			}
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
		return new Comparator<Player>() {
			@Override
			public int compare(Player p1, Player p2) {
				return Integer.compare(p1.getDeck().size(),p2.getDeck().size());
			}
		};
	}
}