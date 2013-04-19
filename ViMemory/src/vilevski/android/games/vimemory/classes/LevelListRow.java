package vilevski.android.games.vimemory.classes;

public class LevelListRow {
	private int imageID;
	private String level;
	
	public LevelListRow(int imageID, String level) {
		this.setImageID(imageID);
		this.setLevel(level);
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public int getImageID() {
		return imageID;
	}

	public void setImageID(int imageID) {
		this.imageID = imageID;
	}
	
}
