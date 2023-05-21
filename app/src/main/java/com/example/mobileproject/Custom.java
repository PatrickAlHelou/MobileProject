package com.example.mobileproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

class Custom extends BaseAdapter {

   private ArrayList<MusicModel> modelArrayList;
   private Context context;
   private int layout;

   private MediaPlayer mediaPlayer; // Declare mediaPlayer as a class-level variable
   private ViewHolder currentlyPlayingViewHolder; // Declare currentlyPlayingViewHolder as a class-level variable

   public Custom(ArrayList<MusicModel> modelArrayList, Context context, int layout) {
      this.modelArrayList = modelArrayList;
      this.context = context;
      this.layout = layout;

      mediaPlayer = new MediaPlayer();
      mediaPlayer.setAudioAttributes(
              new AudioAttributes.Builder()
                      .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                      .setUsage(AudioAttributes.USAGE_MEDIA)
                      .build()
      );
   }

   @Override
   public int getCount() {
      return modelArrayList.size();
   }

   @Override
   public Object getItem(int position) {
      return modelArrayList.get(position);
   }

   @Override
   public long getItemId(int position) {
      return position;
   }

   private class ViewHolder {
      TextView txt, txt1;
      Button button;
      boolean isPlaying = false;
   }

   private void stopMediaPlayer() {
      if (mediaPlayer != null && mediaPlayer.isPlaying()) {
         mediaPlayer.pause();
         mediaPlayer.seekTo(0);
      }
   }

   @Override
   public View getView(int i, View view, ViewGroup viewGroup) {
      ViewHolder viewHolder;
      LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      if (view == null) {
         view = layoutInflater.inflate(layout, null);
         viewHolder = new ViewHolder();
         viewHolder.txt = view.findViewById(R.id.textView6);
         viewHolder.txt1 = view.findViewById(R.id.textView7);
         viewHolder.button = view.findViewById(R.id.button2);
         view.setTag(viewHolder);
      } else {
         viewHolder = (ViewHolder) view.getTag();
      }

      MusicModel model = modelArrayList.get(i);
      viewHolder.txt.setText(model.getTitle());
      viewHolder.txt1.setText("TOP: " + model.getRank());

      if (viewHolder.isPlaying && viewHolder == currentlyPlayingViewHolder) {
         viewHolder.isPlaying = true;
      } else {
         viewHolder.isPlaying = false;
      }
      SharedPreferences sh = this.context.getSharedPreferences(LoginActivity.SHARED_PREFS,Context.MODE_PRIVATE);
      viewHolder.button.setOnClickListener(new View.OnClickListener() {
         public void onClick(View view) {
            if (!viewHolder.isPlaying) {

               if (currentlyPlayingViewHolder != null && currentlyPlayingViewHolder != viewHolder) {
                  // Stop the MediaPlayer of the previously playing ViewHolder
                  stopMediaPlayer();
                  currentlyPlayingViewHolder.isPlaying = false;
               }

               String audioUrl = model.getLink();
               try {
                  mediaPlayer.reset();
                  mediaPlayer.setDataSource(audioUrl);
                  mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                     @Override
                     public void onPrepared(MediaPlayer mp) {
                        try (Database db = new Database(view.getContext(), "fitness", null, 1)) {
                           String userName = sh.getString(LoginActivity.USER_NAME,"");
                           //db.addHistory(model.getTitle(), userName);
                        }
                        mediaPlayer.start();
                        viewHolder.isPlaying = true;
                        currentlyPlayingViewHolder = viewHolder; // Update the currently playing ViewHolder
                     }
                  });
                  mediaPlayer.prepareAsync();
               } catch (IOException e) {
                  e.printStackTrace();
               }
            } else {
               mediaPlayer.pause();
               viewHolder.isPlaying = false;
            }
         }
      });

      return view;
   }


}
