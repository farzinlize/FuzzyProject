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
	
	public boolean checkTopology() throws Exception{
		if(answered){
			throw new Exception("answered Query: " + piority);
		}
		if(start.getTopologyID()<end.getTopologyID()){
			this.makeAnswer(-1);
			return true;	//no road
		}
		return false;		//can't know
	}
	
	public Node getStart(){
		return start;
	}
	
	@Override
	public int getIntValue() {
		return start.number;
	}
	
	public int getEndCity() throws Exception{
		return end.getTopologyID();
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
