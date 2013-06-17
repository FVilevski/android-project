package vilevski.android.games.vimemory.classes;

public class HighScore {
	private Long id;
	private String gamer;
	private int score;
	private String gameLevel;
	
	public HighScore(){ 
	}
	
	public HighScore(String gamer, int score, String gameLevel) {
		super();
		this.gamer = gamer;
		this.score = score;
		this.gameLevel = gameLevel;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getGamer() {
		return gamer;
	}
	public void setGamer(String gamer) {
		this.gamer = gamer;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getGameLevel() {
		return gameLevel;
	}
	public void setGameLevel(String gameLevel) {
		this.gameLevel = gameLevel;
	}
	
}
