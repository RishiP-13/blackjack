import java.util.*;
public class Game {
 public static void main(String[] args) {
	 //see if game works
	Blackjack b1 = new Blackjack();
	b1.placeBet();
	b1.setDeck();
	b1.setPlayerHand();
	b1.getCardValue(b1.card1);
	b1.getCardValue(b1.card2);
	b1.setHand(b1.card1);
	b1.setHand(b1.card2);
	System.out.println(b1.getHandvalue(b1.player_hand));
	b1.setDealerValue();
	b1.getCardValue(b1.dealer_upcard);
	b1.getCardValue(b1.dealer_downcard);
	b1.setDealerHand(b1.dealer_upcard);
	b1.setDealerHand(b1.dealer_downcard);
	System.out.println(b1.isPlayerBust());
	b1.Stand(b1.dealer_hand);
	while(b1.dealervalue<21 && b1.handvalue<21) {
		b1.Hit((b1.player_hand));
	}
	b1.determineWinner();
	System.out.println("The dealer value is " + b1.dealervalue);
	System.out.println("The player value is " + b1.handvalue);
   
 }
  
}
