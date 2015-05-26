package com.example.lenovo.clothesorganizer;



import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

public class MyWardrobeAdapter extends CursorAdapter {
    public MyWardrobeAdapter (Context context, Cursor cursor) {
        super(context, cursor);
    }
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView name = (TextView) view.findViewById(R.id.nameInListView);
        name.setText(cursor.getString(cursor.getColumnIndex("name")));

        TextView comment = (TextView) view.findViewById(R.id.commentInListView);
        comment.setText(cursor.getString(cursor.getColumnIndex("comment")));

        ImageView photo = (ImageView) view.findViewById(R.id.imageInListView);
        String photoPath =  cursor.getString(cursor.getColumnIndex("pathToImage"));
        File file = new File(photoPath);
        photo.setImageURI(Uri.fromFile(file));
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.list_row, parent, false);
            bindView(view, context, cursor);
            return view;

    }

}
