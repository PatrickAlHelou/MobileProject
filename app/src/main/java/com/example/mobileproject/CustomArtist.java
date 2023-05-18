package com.example.mobileproject;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

class CustomArtist extends BaseAdapter {

    private ArrayList<ArtistModel> modelArrayList;
    private Context context;
    private int layout;

    public CustomArtist(ArrayList<ArtistModel> modelArrayList, Context context, int layout) {
        this.modelArrayList = modelArrayList;
        this.context = context;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return modelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return modelArrayList.get( position );
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        TextView txt,txt1;

        Button button;

        MediaPlayer mediaPlayer;

        boolean isPlaying = false;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = new ViewHolder();
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(layout,null);
        viewHolder.txt = view.findViewById(R.id.textView6);

        ArtistModel model = modelArrayList.get(i);
        viewHolder.txt.setText(model.getArtist());
        viewHolder.txt1 =view.findViewById(R.id.textView7);
        viewHolder.txt1.setText("TOP: "+model.getRank());

        return view;
    }
}