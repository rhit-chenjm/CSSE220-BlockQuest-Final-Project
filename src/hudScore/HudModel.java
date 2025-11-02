package hudScore;

public class HudModel {
	
	private int score = 0;
	
	
	public int getScore(){
		return score;
	}
	
	public void addScore(int delta) {
		score += delta;
	}
	
	public void setScore(int start) {
		score = start;
	}

}
