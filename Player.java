package HW5;

import java.util.ArrayList;
public  class Player extends Person {
	private String name;
	private int chips;
	private int bet;
	private ArrayList<Card> oneRoundCard;
	public Player(String name, int chips){
		this.name=name;
		this.chips=chips;
	}
	public String getName(){
		return name;
	}
	public int makeBet(){
		bet = 1;
		if(chips<=0){
			return 0;
		}
		
		return bet;
	}
	
	
	
	public int getCurrentChips(){
		return chips;
	}
	public void increaseChips (int diff){
		chips = (chips+diff);
	}
	public void sayHello(){
		System.out.println("Hello, I am " + name + ".");
		System.out.println("I have " + chips + " chips.");
	}
	@Override
	public boolean hit_me(Table table) {
		int n=0;
		oneRoundCard = this.getOneRoundCard();
		for(Card k : oneRoundCard){
			n +=k.getRank();
		}
		if(n < 17){
			return true;
		}
		else{
			return false;
		}
	}

}
