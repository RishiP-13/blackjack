import java.util.*;
public class Blackjack {
	ArrayList<Cards> deck;
	Cards card1, card2;
	ArrayList<Cards> player_hand = new ArrayList<Cards>();
	ArrayList<Cards> dealer_hand = new ArrayList<Cards>();
    Cards dealer_upcard, dealer_downcard;
    int handvalue = 0;
    int dealervalue = 0;
    int bet;
    int insuranceBet = 0;
	  public void setDeck() {
		//sets card deck
		  deck = new ArrayList<Cards>();
		  String[] ranks = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
		  String[] suites = {"D","S","H","C"};
		  for(int i=0; i<suites.length; i++) { 
			  for(int j = 0; j<ranks.length; j++) {
				  deck.add(new Cards(suites[i],ranks[j]));
				// shuffles deck 
		Collections.shuffle(deck);
			  }
		  }
      System.out.println(deck);
      
	 }
	  //returns a singular card
	  public Cards getNewCard() {
		  Cards c;
		  c = deck.remove((int)(Math.random()*deck.size()));
		  return c;
	  }
	  //adds card for player
	  public void setHand(Cards card) {
			 player_hand.add(card);
	
			
		 }
	  // adds card for dealer
	  public void setDealerHand(Cards dealercard) {
		  dealer_hand.add(dealercard);
	  }
	  public ArrayList<Cards> getHand(){
		  return player_hand;
	  }
	  public ArrayList<Cards> getDealerHand(){
		  return dealer_hand;
	  }
	  // sets hand for player
	  public void setPlayerHand() {
		  card1 = deck.remove((int)(Math.random()*deck.size()));
		  card2 = deck.remove((int)(Math.random()*deck.size()));
		  System.out.println(card1);
		  System.out.println(card2);
	  }
	  //sets hand for dealer
	  public void setDealerValue() {
		  dealer_upcard = deck.remove((int)(Math.random()*deck.size()));
		  dealer_downcard = deck.remove((int)(Math.random()*deck.size()));
		  System.out.println(dealer_upcard);
	  }
	  //gets the value for the card
	  public int getCardValue(Cards card) {
	    if (card.getRank()== "J" || card.getRank() == "Q" || card.getRank()=="K") {
	    	return 10;
	    }
	    else if( card.getRank()== "A"){
	    	Scanner input = new Scanner(System.in);
	    	int acevalue = 0;
	    	while(acevalue != 1 && acevalue != 11) {
	    	 System.out.print("Enter 11 or 1 for ace: ");
	    	 acevalue = input.nextInt();
	    	}
	    	return acevalue;
	    }
	    else {
	    	int value = Integer.parseInt(card.getRank());
	    	return value;
	    }
	  }
	//sets current hand value for player
	 public int getHandvalue(ArrayList<Cards> cardDeck) {
		 int i = 0;
		 while(i<cardDeck.size()) {
			cardDeck.get(i).getRank();
			handvalue += getCardValue(cardDeck.get(i));
			 i++;
		 }
		 return handvalue;
	 }
	 //sets current hand value for dealer
	 public int getDealervalue(ArrayList<Cards> cardDeck) {
		 int i = 0;
		 while(i<cardDeck.size()) {
			cardDeck.get(i).getRank();
			dealervalue += getCardValue(cardDeck.get(i));
			 i++;
		 }
		 return dealervalue;
	 }
	 //determines in player busts
	 public boolean isPlayerBust() {
		 if(handvalue > 21) {
			 return true;
		 }
		 else {
			 return false;
		 }
	 }
	 //determines if dealer busts
	 public boolean isDealerBust() {
		 if(dealervalue > 21) {
			 return true;
		 }
		 else {
			 return false;
		 }
	 }
	 // adds another card for the player
	 public void Hit(ArrayList<Cards> cardDeck) {
		 
		 if(!isPlayerBust()) {
			 Cards c;
			 c = deck.remove((int)(Math.random()*deck.size()));
			 cardDeck.add(c);
			 System.out.println(cardDeck);
			 //adds new rank to current hand value
			 handvalue += getCardValue(c);
			 
         System.out.println(handvalue);
			 }
		

		 else {
			 System.out.println("Player busted");
		 }
			 
	 
}	
	 //once the players stays, a new card is drawn for the dealer
	 public void Stand(ArrayList<Cards> cardDeck) {
	  dealervalue = 0;
	  getDealervalue(cardDeck);
	  //dealer can only draw a new card if there value is less than 17 and the player hand is not 21
	  while(dealervalue<17&&handvalue!=21) {
		  Cards c;
			 c = deck.remove((int)(Math.random()*deck.size()));
			 cardDeck.add(c);
			 dealervalue += getCardValue(c);
			 System.out.println(cardDeck);
			
	  }
	  System.out.println(dealervalue);
	 }
	 //allows player to place bet
	 public void placeBet() {
		 Scanner input = new Scanner(System.in);
		 System.out.println("Enter a bet between 2 and 500$$: ");
		 bet = input.nextInt();
		 while(bet<2 && bet>500) {
			 System.out.println("Enter a bet between 2 and 500$$: ");
			 bet = input.nextInt(); 
		 }
	 }
	 public int getBet() {
		 return bet;
	 }
	 //allows player to place an Insurance Bet
	 public int Insurance() {
		 //if the dealers face up card is ace they can place a bet
		 if (dealer_upcard.getRank()=="A") {
			 Scanner input = new Scanner(System.in);
			 System.out.println("Enter a bet less than half of the original bet: ");
			 insuranceBet = input.nextInt();
			 while(insuranceBet<0 && insuranceBet<bet/2) {
				 System.out.println("Enter a bet less than half of the original bet: ");
				 insuranceBet = input.nextInt();	 
			 }
			 //if dealer facedown card has a value of 10 they win the bet
			 if(dealer_downcard.getRank()=="Q" || dealer_downcard.getRank()=="K" || dealer_downcard.getRank()=="J") {
				 insuranceBet *= 2;
			 }
			 else {
				 insuranceBet = 0;
			 }
		  return insuranceBet;
		 }
		 else {
			return insuranceBet;
		 }
	 }
	//determines winner of the game
	 public void determineWinner() {
	  if (isPlayerBust() == true && isDealerBust() == false || dealervalue == 21 && handvalue< 21) {
		  bet -= bet;
		  bet += insuranceBet;
		  System.out.println("Dealer wins");
		  System.out.println(bet);
	  }
	  else if (isPlayerBust()==false && isDealerBust()==true || handvalue == 21 && dealervalue<21) {
		  bet *= 1.5;
		  bet += insuranceBet;
		  System.out.println("Player wins");
		  System.out.println(bet);
	  }
	  else if(dealervalue == 21 && handvalue == 21) {
		  bet += insuranceBet;
		  System.out.println("It is a push, your bet is returned");
		  System.out.println(bet);
	  }
	  else if(dealervalue >21 && handvalue>21) {
		  bet = 0;
		  System.out.println("Player loses");
		  System.out.println(bet);
	  }
	 }
	
}
