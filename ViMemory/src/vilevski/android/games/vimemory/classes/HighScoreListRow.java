package vilevski.android.games.vimemory.classes;

public class HighScoreListRow {
	private String txtGamer;
	private String txtLevel;
	private String txtScore;
	public String getTxtGamer() {
		return txtGamer;
	}
	public HighScoreListRow(String txtGamer, String txtLevel, String txtScore) {
		super();
		this.txtGamer = txtGamer;
		this.txtLevel = txtLevel;
		this.txtScore = txtScore;
	}
	public void setTxtGamer(String txtGamer) {
		this.txtGamer = txtGamer;
	}
	public String getTxtLevel() {
		return txtLevel;
	}
	public void setTxtLevel(String txtLevel) {
		this.txtLevel = txtLevel;
	}
	public String getTxtScore() {
		return txtScore;
	}
	public void setTxtScore(String txtScore) {
		this.txtScore = txtScore;
	}
}
