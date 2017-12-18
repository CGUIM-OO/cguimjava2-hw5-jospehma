package HW5;

import java.util.ArrayList;

public class Table {
	public final int MAXPLAYER = 4;
	private Deck deck;
	private Player[]tbPlayer;
	private Dealer tbDealer = new Dealer();
	private int[] pos_betArray = new int[MAXPLAYER];
	
	public Table(int nDeck){
	    deck = new Deck(nDeck);
		tbPlayer = new Player[MAXPLAYER];
	}
	public void set_player(int pos, Player p){
		tbPlayer[pos] = p;
	}
	public Player[] get_player(){
		return tbPlayer;
	}
	public void set_dealer(Dealer d){
		tbDealer = d;
	}
	public Card get_face_up_card_of_dealer(){
		ArrayList<Card> oneRoundCard;
		oneRoundCard = tbDealer.getOneRoundCard();
		return oneRoundCard.get(1);
	
	}
	private void ask_each_player_about_bets(){
		for(int i=0;i<MAXPLAYER;i++){
			tbPlayer[i].sayHello(); 
			pos_betArray[i] =tbPlayer[i].makeBet();
			}		
	}
	private void distribute_cards_to_dealer_and_players(){
		for(int i=0;i<MAXPLAYER;i++){
			ArrayList<Card> playerCard = new ArrayList<Card>();
			playerCard.add(deck.getOneCard(true));
			playerCard.add(deck.getOneCard(true));
			tbPlayer[i].setOneRoundCard(playerCard);
		}
		ArrayList<Card> dealerCard = new ArrayList<Card>();
		dealerCard.add(deck.getOneCard(true));
		dealerCard.add(deck.getOneCard(true));
		tbDealer.setOneRoundCard(dealerCard);
		
		System.out.println("Dealer's face up card is ");
		Card c = get_face_up_card_of_dealer();
		c.printCard();
	}
	private void ask_each_player_about_hits(){
		for(int i=0;i<MAXPLAYER;i++){
			boolean hit = false;
			do{
				hit = tbPlayer[i].hit_me(this);
				if(hit){
					ArrayList<Card> playerCard = new ArrayList<Card>();
					playerCard = tbPlayer[i].getOneRoundCard();
					playerCard.add(deck.getOneCard(true));
					tbPlayer[i].setOneRoundCard(playerCard);
					System.out.println("Hit!");
					System.out.println(tbPlayer[i].getName()+"'s Cards now:");
					for(Card c : tbPlayer[i].getOneRoundCard()){
						c.printCard();
					}
				}
				else{
					System.out.println(tbPlayer[i].getName()+", Pass hit!");
					System.out.println(tbPlayer[i].getName()+"'s hit is over!");
					System.out.println(tbPlayer[i].getName()+", Cards now:");
					for(Card c : tbPlayer[i].getOneRoundCard()){
						c.printCard();
					}
				}
			}while(hit);
		}
	}
	private void ask_dealer_about_hits(){
		boolean hit = false;
		do{
			hit = tbDealer.hit_me(this);
			if(hit){
				ArrayList<Card> DealerCard = new ArrayList<Card>();
				DealerCard = tbDealer.getOneRoundCard();
				DealerCard.add(deck.getOneCard(true));
				tbDealer.setOneRoundCard(DealerCard);
				System.out.println("Hit!");
				System.out.println("Dealer's Cards now:");
				for(Card c : tbDealer.getOneRoundCard()){
					c.printCard();
				}
			}
			else{
				System.out.println("Dealer's hit is over!");
				System.out.println("Dealer's Cards now:");
				for(Card c : tbDealer.getOneRoundCard()){
					c.printCard();
				}
			}	
		}while(hit);
		
	}
	private void calculate_chips(){
		int DealerChip;
		DealerChip = tbDealer.getTotalValue();
		System.out.println("Dealer's card value is" + DealerChip + " ,Cards: ");
	    tbDealer.printAllCard();
	    for(int i=0;i<MAXPLAYER;i++){
	    	tbPlayer[i].getTotalValue();;
	    	System.out.println(tbPlayer[i].getName()+"'s Card: ");
	    	tbPlayer[i].printAllCard();
	    	System.out.println(tbPlayer[i].getName()+"'s card value is"+tbPlayer[i].getTotalValue());
	    	if(tbPlayer[i].getTotalValue()<=21 && tbDealer.getTotalValue()>21){
	    		tbPlayer[i].increaseChips(pos_betArray[i]);
	    		System.out.print(",Get "+pos_betArray[i]+"Chips, the Chips now is: ");
	    	}
	    	else if(tbPlayer[i].getTotalValue()>21 && tbDealer.getTotalValue()<=21){
	    		tbPlayer[i].increaseChips(-pos_betArray[i]);
	    		System.out.print(",Loss "+pos_betArray[i]+"Chips, the Chips now is: ");
	    	}
	    	else if(tbPlayer[i].getTotalValue() > tbDealer.getTotalValue() && tbPlayer[i].getTotalValue()<=21){
	    		tbPlayer[i].increaseChips(pos_betArray[i]);
	    		System.out.print(",Get "+pos_betArray[i]+"Chips, the Chips now is: ");
	    	}
	    	else if(tbPlayer[i].getTotalValue() < tbDealer.getTotalValue() && tbDealer.getTotalValue()<=21){
	    		tbPlayer[i].increaseChips(-pos_betArray[i]);
	    		System.out.print(",Loss "+pos_betArray[i]+"Chips, the Chips now is: ");
	    	}
	    	//else if(tbPlayer[i].getTotalValue() == 21 && tbDealer.getTotalValue() == 21){
	    		//tbPlayer[i].increaseChips(-pos_betArray[i]);
	    		//System.out.print(",Loss "+pos_betArray[i]+"Chips, the Chips now is: ");
	    	//}
	    	else{
	    		System.out.print(",chip has no change"+pos_betArray[i]+"Chips,the Chips now is: ");
	    	}
	        System.out.println(tbPlayer[i].getCurrentChips());
	    }
	}
	public int[] get_palyers_bet(){
		return pos_betArray;
	}
	public void play(){
			ask_each_player_about_bets();
			distribute_cards_to_dealer_and_players();
			ask_each_player_about_hits();
			ask_dealer_about_hits();
			calculate_chips();
		}
}