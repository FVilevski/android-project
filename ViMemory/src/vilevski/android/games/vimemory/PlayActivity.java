package vilevski.android.games.vimemory;


import vilevski.android.games.vimemory.classes.ImageAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class PlayActivity extends Activity {
	protected int [] [] cubes;
	protected Box firstBox;
	protected Box secondBox;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play);
		int flag = getIntent().getFlags();

		 

		   
		
		
		switch (flag) {
		case 0:
			makeGame(3,2);
			break;
		case 1:
			makeGame(4,2);
			break;
		case 2:
			makeGame(3,3);
			break;
		case 3:
			makeGame(3,4);
			break;
		case 4:
			makeGame(4,4);
			break;
		}
	
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.play, menu);
		return true;
	}
	
	private void makeGame(int rows, int columns){
		cubes = new int [rows] [columns];
		GridView gridview = (GridView) findViewById(R.id.gridview);
	    gridview.setAdapter(new ImageAdapter(this));
	   
	    
	    
	    
	    gridview.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	        	Integer[] img = {
	    				
	    	            R.drawable.image1, R.drawable.image1,
	    	            R.drawable.image2, R.drawable.image2,
	    	            R.drawable.image3, R.drawable.image3,
	    	            R.drawable.image4, R.drawable.image4,
	    	            R.drawable.image5, R.drawable.image5,
	    	            R.drawable.image6, R.drawable.image6,
	    	            R.drawable.image7, R.drawable.image7,
	    	            R.drawable.image8, R.drawable.image8,
	    	    };
	        	v.setBackgroundResource(img[position]);
	        }
	    });
	    
	    
	}
}
