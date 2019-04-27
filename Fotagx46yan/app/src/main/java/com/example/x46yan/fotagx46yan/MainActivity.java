package com.example.x46yan.fotagx46yan;

import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.RatingBar;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private Model model;
    private GridView gridView;
    private ImageAdapter imageadapter;
    private Toolbar toolbar;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        this.model = new Model();


        this.gridView = (GridView) findViewById(R.id.gridView);
        this.imageadapter = new ImageAdapter( this, model);
        this.gridView.setAdapter(this.imageadapter);

        this.model.addAdapter(this.imageadapter);

        this.searchView = findViewById(R.id.search_view);


        ImageButton remove = findViewById(R.id.removeBtn);
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.removeAll();
                imageadapter.notifyDataSetChanged();
            }
        });


        ImageButton load = findViewById(R.id.loadBtn);
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.load();
            }
        });

        final RatingBar ratingbar = findViewById(R.id.filter);
        ratingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                model.setFilter((int) v);
            }
        });


        ImageButton clear = findViewById(R.id.clear);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.setFilter(0);
                ratingbar.setRating(0);
                imageadapter.notifyDataSetChanged();
            }
        } );


        this.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                new MyTask(s).execute();
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        // Checks the orientation of the screen
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            model.setGridView(true);
            toolbar.setTitle("Fotag(x46yan)");
            gridView.setNumColumns(2);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            model.setGridView(false);
            toolbar.setTitle("");
            gridView.setNumColumns(1);
        }
    }

    private class MyTask extends AsyncTask<Void, Void, Bitmap> {
        String input;


        public MyTask(String s) {
            this.input = s;
        }

        @Override
        protected Bitmap doInBackground(Void... params) {
            Bitmap bitmap = null;
            try {
                URL url = new URL(input);
                bitmap = BitmapFactory.decodeStream((InputStream) url.getContent());
            } catch (IOException e) {
                Log.e("loadfailed!!!!!!!!!!!!!!!!!!!", e.getMessage());
            }
            return bitmap;
        }
        @Override
        protected void onPostExecute(Bitmap bitmap) {
           model.AddImage(bitmap);
           imageadapter.notifyDataSetChanged();
        }
    }
}
