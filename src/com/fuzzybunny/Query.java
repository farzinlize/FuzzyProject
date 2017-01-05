package com.fuzzybunny;

public class Query implements Element {

	private boolean answered;
	private int answer;
	private Node start;
	private Node end;
	private int piority;
	
	public Query(Node s, Node e, int p){
		start = s;
		end = e;
		piority = p;
		answered = false;
	}
	
	@Override
	public int getIntValue() {
		return start.number;
	}
	
	public void makeAnswer(int ans){
		answer = ans;
		answered = true;
	}
	
	public int returnAnswer() throws Exception{
		if(answered)
			return answer;
		else
			throw new Exception("Not answered yet");
	}
	
	public int getPiority(){
		return piority;
	}

}
