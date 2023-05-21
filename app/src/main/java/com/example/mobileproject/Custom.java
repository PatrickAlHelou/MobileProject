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

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;

class Custom extends BaseAdapter {

   private ArrayList<MusicModel> modelArrayList;
   private Context context;
   private int layout;

   private MediaPlayer mediaPlayer;
   private ViewHolder currentlyPlayingViewHolder;

   public final static String SHARED_PREFS = "userPrefs";
   public final static String USER_NAME = "userName";

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

      SharedPreferences sh = context.getSharedPreferences(LoginActivity.SHARED_PREFS,Context.MODE_PRIVATE);
      SharedPreferences sharedpreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
      String name = sharedpreferences.getString("name", "");


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

                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference musicRef = database.getReference("music");

                        Query query = musicRef.orderByChild("name").equalTo(name);

                        query.addListenerForSingleValueEvent(new ValueEventListener() {
                           @Override
                           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                              boolean isMusicFound = false;
                              for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                                 String musicTitle = childSnapshot.child("title").getValue(String.class);
                                 if (musicTitle.equals(model.getTitle())) {
                                    // Increment the time count
                                    int time = childSnapshot.child("time").getValue(Integer.class);
                                    time++;
                                    childSnapshot.getRef().child("time").setValue(time);
                                    isMusicFound = true;
                                    break;
                                 }
                              }
                              if (!isMusicFound) {
                                 DatabaseReference newMusicRef = musicRef.push();
                                 newMusicRef.child("name").setValue(name);
                                 newMusicRef.child("title").setValue(model.getTitle());
                                 newMusicRef.child("time").setValue(1);
                              }
                           }

                           @Override
                           public void onCancelled(@NonNull DatabaseError error) {

                           }
                        });


                        mediaPlayer.start();
                        viewHolder.isPlaying = true;


                        currentlyPlayingViewHolder = viewHolder;
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


//      viewHolder.button.setOnClickListener(new View.OnClickListener() {
//         public void onClick(View view) {
//            if (!viewHolder.isPlaying) {
//
//               if (currentlyPlayingViewHolder != null && currentlyPlayingViewHolder != viewHolder) {
//                  // Stop the MediaPlayer of the previously playing ViewHolder
//                  stopMediaPlayer();
//                  currentlyPlayingViewHolder.isPlaying = false;
//               }
//
//               String audioUrl = model.getLink();
//               try {
//                  mediaPlayer.reset();
//                  mediaPlayer.setDataSource(audioUrl);
//                  mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                     @Override
//                     public void onPrepared(MediaPlayer mp) {
//
//                        FirebaseDatabase database = FirebaseDatabase.getInstance();
//                        DatabaseReference musicRef = database.getReference("music");
//
//                        Query query = musicRef.orderByChild("name").equalTo(name);
//
//                        query.addListenerForSingleValueEvent(new ValueEventListener() {
//                           @Override
//                           public void onDataChange(DataSnapshot dataSnapshot) {
//                              if (dataSnapshot.exists()) {
//                                 // If a matching child node is found, increment the "time" attribute by 1
//                                 for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
//                                    int currentTime = childSnapshot.child("time").getValue(Integer.class);
//                                    childSnapshot.getRef().child("time").setValue(currentTime + 1);
//                                    break; // Exit the loop after the first match
//                                 }
//                              } else {
//                                 // If no matching child node is found, create a new child node with the provided "name", "title", and "time" attributes set to the given values
//                                 DatabaseReference newMusicRef = musicRef.push();
//                                 newMusicRef.child("name").setValue(name);
//                                 newMusicRef.child("title").setValue(model.getTitle());
//                                 newMusicRef.child("time").setValue(1);
//                              }
//                           }
//
//                           @Override
//                           public void onCancelled(DatabaseError databaseError) {
//                              // Handle the error
//                           }
//                        });
//                        mediaPlayer.start();
//                        viewHolder.isPlaying = true;
//
//
//                        currentlyPlayingViewHolder = viewHolder;
//                     }
//                  });
//                  mediaPlayer.prepareAsync();
//               } catch (IOException e) {
//                  e.printStackTrace();
//               }
//            } else {
//               mediaPlayer.pause();
//               viewHolder.isPlaying = false;
//            }
//         }
//      });

      return view;
   }
}