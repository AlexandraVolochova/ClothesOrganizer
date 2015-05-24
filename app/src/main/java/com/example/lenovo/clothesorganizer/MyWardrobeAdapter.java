package com.example.lenovo.clothesorganizer;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

import java.io.File;

public class MyWardrobeAdapter extends ResourceCursorAdapter {
    public MyWardrobeAdapter (Context context, int layout, Cursor cursor) {
        super(context, layout, cursor);
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

}
