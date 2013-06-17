package vilevski.android.games.vimemory.classes;


import vilevski.android.games.vimemory.R;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter{
	private Context ctx;
	
	
	public ImageAdapter(Context c) {
		ctx = c;
	}
	
	@Override
	public int getCount() {
		return img.length;
		
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView;
		if(convertView==null){
			imageView = new ImageView(ctx);
			imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
			
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
            
        } else {
            imageView = (ImageView) convertView;
        }
		
		imageView.setBackgroundResource(img[position]);
		imageView.setBackgroundResource(blank);
		return imageView;
	}
	private Integer blank = R.drawable.blank;
	private Integer[] img = {
			
            R.drawable.image1, R.drawable.image1,
            R.drawable.image2, R.drawable.image2,
            R.drawable.image3, R.drawable.image3,
            R.drawable.image4, R.drawable.image4,
            R.drawable.image5, R.drawable.image5,
            R.drawable.image6, R.drawable.image6,
            R.drawable.image7, R.drawable.image7,
            R.drawable.image8, R.drawable.image8,
    };
}
