package com.example.android.miwok;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    public WordAdapter(Context context, ArrayList<Word> words) {
        // resource: The resource ID for a layout file containing a TextView to use when instantiating views.
        // It is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews, 'resource' will not be used therefore any int value is allowed.
        super(context, 0, words);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Word currentWord = getItem(position);

        TextView miwokText = (TextView) listItem.findViewById(R.id.miwok_text);
        miwokText.setText(currentWord.getMiwok());

        TextView defaultText = (TextView) listItem.findViewById(R.id.default_text);
        defaultText.setText(currentWord.getDefault());

        ImageView image = (ImageView) listItem.findViewById(R.id.image);
        if (currentWord.hasImage()) {
            image.setImageResource(currentWord.getImageResourceId());
            image.setVisibility(View.VISIBLE);
        } else {
            image.setVisibility(View.GONE);
        }


        return listItem;
    }
}
