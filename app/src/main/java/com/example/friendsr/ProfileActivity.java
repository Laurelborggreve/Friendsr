package com.example.friendsr;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ProfileActivity extends AppCompatActivity {

    // Declare variables
    private SharedPreferences.Editor editor;
    private SharedPreferences.Editor editorLikes;
    private String nameIndividual;
    public float friendLiked = 0;
    public boolean friendLikedButtonClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Extract value from the intent
        Intent intent = getIntent();
        Friend retrievedFriend = (Friend) intent.getSerializableExtra("clicked_friend");

        // Store the ratings so that information persists even when closing the app
        SharedPreferences prefs = getSharedPreferences("settings", MODE_PRIVATE);
        editor = getSharedPreferences("settings", MODE_PRIVATE).edit();
        RatingBar ratingbar = findViewById(R.id.ratingbar_individual);
        ratingbar.setOnRatingBarChangeListener(new RatingBarChangeListener());
        nameIndividual = retrievedFriend.getName();
        float ratingStored = prefs.getFloat(nameIndividual, 0);

        // Check if there is a rating that needs to be restored or if user has not been rated yet
        if (ratingStored != 0) {
            retrievedFriend.setRating(ratingStored);
        }
        else {
            retrievedFriend.setRating((float) 0);
        }

        // Store the likes so that information persists even when closing the app (= EXTRA IDEA)
        SharedPreferences prefsLikes = getSharedPreferences("settingsLikes", MODE_PRIVATE);
        editorLikes = getSharedPreferences("settingsLikes", MODE_PRIVATE).edit();
        Button likeButton = findViewById(R.id.like_button);
        likeButton.setOnClickListener(new LikeButtonListener());
        nameIndividual = retrievedFriend.getName();
        float likesStored = prefsLikes.getFloat(nameIndividual, 0);

        // Check if there is an amount of likes that needs to be restored or if user has not been liked yet
        TextView amountOfLikes = findViewById(R.id.amount_of_likes);

        if (likesStored != 0) {
            retrievedFriend.setProfileLiked(likesStored);
            amountOfLikes.setText(nameIndividual + " is liked: \n" + likesStored + " times");
            friendLikedButtonClicked = true;
        }
        else {
            retrievedFriend.setProfileLiked((float) 0);
            amountOfLikes.setText(nameIndividual + " is liked: \n 0.0 times");
        }

        // Set name, bio, rating and picture of the user
        ImageView pictureIndividual = findViewById(R.id.picture_individual);
        pictureIndividual.setImageResource(retrievedFriend.getId_drawable());

        ratingbar.setRating(retrievedFriend.getRating());

        TextView nameIndividual = findViewById(R.id.name_individual);
        nameIndividual.setText(retrievedFriend.getName());

        TextView bioIndividual = findViewById(R.id.bio_individual);
        bioIndividual.setText(retrievedFriend.getBio());
    }

    // EXTRA IDEA FOR IMPROVING PROJECT
    // Method to recognize when like button is touched
    private class LikeButtonListener implements Button.OnClickListener {

        @Override
        public void onClick(View v) {
            TextView amountOfLikes = findViewById(R.id.amount_of_likes);
            Button likeButton = findViewById(R.id.like_button);

            if (friendLiked == 0 && friendLikedButtonClicked == false) {
                // Keep track of likes
                friendLiked++;

                // Show amount of likes
                amountOfLikes.setText(nameIndividual + " is liked: \n" + friendLiked + " times");

                // Show the user that he liked the profile
                Toast.makeText(getApplicationContext(), " You liked " + nameIndividual, Toast.LENGTH_SHORT).show();

                // Disable like button
                likeButton.setClickable(false);

                // Save amount of likes
                SharedPreferences prefsLikes = getSharedPreferences("settingsLikes", MODE_PRIVATE);
                SharedPreferences.Editor editorLikes = prefsLikes.edit();
                editorLikes.putFloat(nameIndividual, friendLiked);
                editorLikes.apply();
            }

            else {
                // Extract value from intent
                Intent intent = getIntent();
                Friend retrievedFriend = (Friend) intent.getSerializableExtra("clicked_friend");
                nameIndividual = retrievedFriend.getName();

                // Keep track of likes
                friendLiked++;
                float likesStored = retrievedFriend.getProfileLiked();
                float totalLikes = likesStored + friendLiked;

                // Show amount of likes
                amountOfLikes.setText(nameIndividual + " is liked: \n" + totalLikes + " times");

                // Show the user that he liked the profile
                Toast.makeText(getApplicationContext(), " You liked " + nameIndividual, Toast.LENGTH_SHORT).show();

                // Disable like button
                likeButton.setClickable(false);

                // Save amount of likes
                SharedPreferences prefsLikes = getSharedPreferences("settingsLikes", MODE_PRIVATE);
                SharedPreferences.Editor editorLikes = prefsLikes.edit();
                editorLikes.putFloat(nameIndividual, totalLikes);
                editorLikes.apply();
            }
        }
    }

    // Method to recognize when ratingbar is touched
    private class RatingBarChangeListener implements RatingBar.OnRatingBarChangeListener {

        @Override
        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
            SharedPreferences prefs = getSharedPreferences("settings", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putFloat(nameIndividual, rating);
            editor.apply();
        }
    }
}
