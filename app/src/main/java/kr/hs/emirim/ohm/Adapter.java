package kr.hs.emirim.ohm;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class Adapter extends PagerAdapter {

        private List<Model> models;
        private LayoutInflater layoutInflater;
        private Context context;


    public Adapter(List<Model> models, Context context) {
            this.models = models;
            this.context = context;
        }

        @Override
        public int getCount() {
            return models.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view.equals(object);
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, final int position) {
            layoutInflater = LayoutInflater.from(context);
            View view = layoutInflater.inflate(R.layout.activity_item, container, false);

            ImageView imageView;
            TextView title, desc, day;

            imageView = view.findViewById(R.id.card_image);
            title = view.findViewById(R.id.card_title);
            desc = view.findViewById(R.id.card_desc);
            day = view.findViewById(R.id.card_day);

            imageView.setImageResource(models.get(position).getImage());
            title.setText(models.get(position).getTitle());
            desc.setText(models.get(position).getDesc());
            day.setText(models.get(position).getDay());


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(position==0) {
                        Intent intent = new Intent(context, MainPage.class);
                        //intent.putExtra("param", models.get(position).getTitle());
                        context.startActivity(intent);

                    }
                    else if(position==1){
                        Intent intent = new Intent(context,  MainPage.class);
                        //intent.putExtra("param", models.get(position).getTitle());
                        context.startActivity(intent);
                    }
                    else if(position==2){
                        Intent intent = new Intent(context,  MainPage.class);
                        //intent.putExtra("param", models.get(position).getTitle());
                        context.startActivity(intent);
                    }
                    else if(position==3){
                        Intent intent = new Intent(context,  MainPage.class);
                        //intent.putExtra("param", models.get(position).getTitle());
                        context.startActivity(intent);
                    }
                }

            });

            container.addView(view, 0);
            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View)object);
        }
    }
