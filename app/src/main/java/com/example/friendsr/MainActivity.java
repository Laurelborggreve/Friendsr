package com.example.friendsr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Make new list with friends
    ArrayList<Friend> friends = new ArrayList<>();

    // Method to create a new ArrayList if the bundle is empty or restore the ArrayList if the bundle is not empty
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            friends = new ArrayList<>();
            addFriends();
        }
        else {
            friends = (ArrayList<Friend>) savedInstanceState.getSerializable("friends");
        }
        setAdapter();
    }

    // Method to save the friends list
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("friends", friends);
    }

    // Method to add all items of friends to the list
    public void addFriends(){
        friends.add(new Friend("Arya", "Arya is a powerful woman.", getResources().getIdentifier("arya", "drawable", getPackageName())));
        friends.add(new Friend("Cersei", "Cersei is a woman with beautiful, long, blond hair.", getResources().getIdentifier("cersei", "drawable", getPackageName())));
        friends.add(new Friend("Daenerys", "Daenerys can be a bitch!", getResources().getIdentifier("daenerys", "drawable", getPackageName())));
        friends.add(new Friend("Jaime", "Jaime is hot and a real flirt.", getResources().getIdentifier("jaime", "drawable", getPackageName())));
        friends.add(new Friend("Jon", "Jon is a fighter and will always win the battle.", getResources().getIdentifier("jon", "drawable", getPackageName())));
        friends.add(new Friend("Jorah", "Jorah is an old, but very wise man.", getResources().getIdentifier("jorah", "drawable", getPackageName())));
        friends.add(new Friend("Margaery", "Margaery is Jorah's daughter and unpredictable.", getResources().getIdentifier("margaery", "drawable", getPackageName())));
        friends.add(new Friend("Melisandre", "Melisandre is a strong independent divorced woman.", getResources().getIdentifier("melisandre", "drawable", getPackageName())));
        friends.add(new Friend("Sansa", "Sansa is blind and deaf, but still dangerous.", getResources().getIdentifier("sansa", "drawable", getPackageName())));
        friends.add(new Friend("Tyrion", "Tyrion is a little, grumpy man.", getResources().getIdentifier("tyrion", "drawable", getPackageName())));
    }

    // Method to instantiate the adapter
    public void setAdapter() {
        FriendsAdapter adapter = new FriendsAdapter(this, R.layout.grid_item, friends);
        GridView grid = findViewById(R.id.grid);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new GridItemClickListener());
    }

    // Method to recognize clicks on separate subitems
    private class GridItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Log.d("friendsr","onItemClick: ");

            Friend clickedFriend = (Friend) parent.getItemAtPosition(position);
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            intent.putExtra("clicked_friend", clickedFriend);
            startActivity(intent);
        }
    }
}
