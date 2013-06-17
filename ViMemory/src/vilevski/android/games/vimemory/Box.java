package vilevski.android.games.vimemory;

import android.widget.Button;

public class Box {
	protected int x;
	protected int y;
	protected Button btn;
	
	public Box(Button btn, int x, int y){
		this.btn = btn;
		this.x = x;
		this.y = y;
	}
}
