package com.example.x46yan.fotagx46yan;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RatingBar;

public class ImageAdapter extends BaseAdapter {

    private Model model;
    private Context context;
    private LayoutInflater inflater;
    private PopupWindow popupWindow;

    private int height;
    private int width;



    public ImageAdapter(Context c, Model m) {
        this.model = m;
        this.context = c;
        this.inflater = (LayoutInflater) c.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.popupWindow = new PopupWindow();

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        this.height = displayMetrics.heightPixels;
        this.width = displayMetrics.widthPixels;

    }


    public int getCount() {
        return this.model.getNumOfDisplayedImages();

    }

    public Object getItem(int pos) {
        return null;
    }


    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null) {
          // Log.d("????","getView is called and view " + i + "is null??????????");
            view = inflater.inflate(R.layout.image_view, null);//set layout for displaying items

        } else {

        }

        if(!model.getCache().containsKey(model.getRes(i))) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeResource(context.getResources(), model.getRes(i), options);
            options.inSampleSize = 1;
            options.inJustDecodeBounds = false;
            model.getCache().put(model.getRes(i), BitmapFactory.decodeResource(context.getResources(), model.getRes(i), options));
        }

        ImageView icon = (ImageView) view.findViewById(R.id.icon);//get id for image view
        icon.setImageBitmap(model.getCache().get(model.getRes(i)));

        final int k = i;

        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView image = new ImageView(context);
                image.setImageBitmap(model.getCache().get(model.getRes(k)));
                image.setScaleType(ImageView.ScaleType.FIT_XY);
                image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                popupWindow.setContentView(image);
                popupWindow.showAtLocation(view, Gravity.CENTER,0,0);
                if (model.getGridView() == true) {
                    popupWindow.update(0,0,height,width);
                } else {
                    popupWindow.update(0,0,width,height);
                }

            }
        });

        RatingBar ratingBar = view.findViewById(R.id.rating_bar);
        ratingBar.setRating(model.getRatingAt(i));
        ratingBar.setOnRatingBarChangeListener(null);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                if (b == true) {
                    //Log.d("??????","rating bar is changed?????");
                    model.setImageRating(k,(int) v);
                }

            }
        });

        Button reset = view.findViewById(R.id.clearBtn);
        reset.setOnClickListener(null);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.clearRatingAt(k);
            }
        });

       return view;
    }

}
