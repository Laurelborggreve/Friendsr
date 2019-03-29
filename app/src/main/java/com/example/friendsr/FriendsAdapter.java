package com.example.friendsr;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FriendsAdapter extends ArrayAdapter<Friend> {
    private ArrayList<Friend> friends;

    public FriendsAdapter(Context context, int resource, ArrayList<Friend> objects) {
        super(context, resource, objects);
        friends = objects;
    }

    // Method to load the layout for each grid item
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.grid_item, parent, false);
        }

        // Get access to layout's views (Image and Name)
        ImageView picture = convertView.findViewById(R.id.picture);
        picture.setImageResource(friends.get(position).getId_drawable());
        TextView name = convertView.findViewById(R.id.name);
        name.setText(friends.get(position).getName());

        return convertView;
    }

}
