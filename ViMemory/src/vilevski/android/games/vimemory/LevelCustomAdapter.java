package vilevski.android.games.vimemory;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class LevelCustomAdapter extends BaseAdapter {
	private List<LevelListRow> levels;
	private Context ctx;
	private LayoutInflater inflater;
	
	public LevelCustomAdapter(Context ctx, List<LevelListRow> lvls){
		this.ctx = ctx;
		this.levels = lvls;
	}
	
	private class ViewHolder{
		ImageView imageView;
		TextView txtLevel;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		inflater = (LayoutInflater) ctx
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		if(convertView == null){
			convertView = inflater.inflate(R.layout.rowlayoutitem, null);
			holder = new ViewHolder();
			holder.txtLevel = (TextView) convertView.findViewById(R.id.title);
			holder.imageView = (ImageView) convertView.findViewById(R.id.icon);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		LevelListRow rowItem = (LevelListRow) getItem(position);
		holder.txtLevel.setText(rowItem.getLevel());
		holder.imageView.setImageResource(rowItem.getImageID());

		return convertView;
	}
	
	@Override
	public int getCount() {
		return levels.size();
	}

	@Override
	public Object getItem(int position) {
		return levels.get(position);
	}

	@Override
	public long getItemId(int position) {
		return levels.indexOf(getItem(position));
	}

	
}
