package com.mymusic.cocomusic.adapter;

import java.util.ArrayList;

import quickscroll.Scrollable;

import com.mymusic.cocomusic.R;
import com.mymusic.cocomusic.util.MediaUtils;
import com.mymusic.cocomusic.vo.Mp3Info;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyMusicListAdapter extends BaseAdapter implements Scrollable{

	private Context context;
	private ArrayList<Mp3Info> mp3infos;
	public MyMusicListAdapter(Context context,ArrayList<Mp3Info> mp3infos){
		this.context = context;
		this.mp3infos = mp3infos;		
	}
	public void setMp3infos(ArrayList<Mp3Info> mp3infos) {
		this.mp3infos = mp3infos;
	}

	@Override
	public int getCount() {
		return mp3infos.size();
	}

	@Override
	public Object getItem(int position) {
		return mp3infos.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View conventView, ViewGroup arg2) {
		ViewHolder vh;
		if(conventView==null){
			conventView = LayoutInflater.from(context).inflate(R.layout.item_music_list, null);
			vh = new ViewHolder();
			vh.item_tv_songName = (TextView) conventView.findViewById(R.id.item_tv_songName);
			vh.item_tv_songHand = (TextView) conventView.findViewById(R.id.item_tv_songHand);
			vh.tv_time = (TextView) conventView.findViewById(R.id.tv_time);
			conventView.setTag(vh);
		}
		vh = (ViewHolder) conventView.getTag();
		Mp3Info mp3info = mp3infos.get(position);
		vh.item_tv_songName.setText(mp3info.getTitle());
		vh.item_tv_songHand.setText(mp3info.getArtist());
		vh.tv_time.setText(MediaUtils.formatTime(mp3info.getDuration()));
		
		return conventView;
	}
	static class ViewHolder{
		TextView item_tv_songName;
		TextView item_tv_songHand;
		TextView tv_time;
	}
	@Override
	public String getIndicatorForPosition(int childposition, int groupposition) {
		return Character.toString((mp3infos.get(childposition)).getTitle().charAt(0));
	}
	@Override
	public int getScrollPosition(int childposition, int groupposition) {
		return childposition;
	}

}
