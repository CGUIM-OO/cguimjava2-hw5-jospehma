package HW5;


import java.util.ArrayList;
import java.util.Random;

class Deck{
	private ArrayList<Card> cards;
	private ArrayList<Card> usedCard;
	private ArrayList<Card> openCard;
	public int nUsed=0;
	public Deck(int nDeck){
		
		cards=new ArrayList<Card>();
		usedCard=new ArrayList<Card>();
		openCard=new ArrayList<Card>();
		//1 Deck have 52 cards, https://en.wikipedia.org/wiki/Poker
		//Hint: Use new Card(x,y) and 3 for loops to add card into deck
		//Sample code start
		//Card card=new Card(1,1); ->means new card as clubs ace
		//cards.add(card);
		//Sample code end
		for(int k=1;k<=nDeck;k++){
			
			for(Card.Suit s : Card.Suit.values()){
			
				for(int j=1;j<14;j++){
				Card card=new Card(s,j);
				cards.add(card);
				
				}
		
			}
			
		}
		shuffle();
	}	
	//TODO: Please implement the method to print all cards on screen (10 points)
	public void printDeck(){
		//Hint: print all items in ArrayList<Card> cards, 
		//TODO: please implement and reuse printCard method in Card class (5 points)
		for(int i=0;i<cards.size();i++){
			cards.get(i).printCard();
		}

	}
	public ArrayList<Card> getAllCards(){
		return cards;
	}

	public void shuffle(){
		
		int j ;
		
		for(int i=0;i<52;i++){
			j = (int)(Math.random()*51);
			Card tmpcard;
			tmpcard = cards.get(i);
			cards.set(i, cards.get(j));
			cards.set(j,tmpcard);
			//Random rnd = new Random();
			//j = rnd.nextInt(51);
			//Card TmpCard;
			//TmpCard = cards.get(i);
			//cards.set(i, cards.get(j));
			//cards.set(j, TmpCard);
		}
		nUsed =0;
		openCard.clear();
	}
	public Card getOneCard(boolean isOpened){
		
		
		
		nUsed+=1;
			if(nUsed==52){
				usedCard.clear(); 
			shuffle();
			
			}
				if(isOpened){
					openCard.add(cards.get(nUsed));
		
			
		}
		usedCard.add(cards.get(nUsed));
		return cards.get(nUsed);
	}
	public ArrayList<Card> getOpenedCard(){
		return openCard;
		
	}

	
}
