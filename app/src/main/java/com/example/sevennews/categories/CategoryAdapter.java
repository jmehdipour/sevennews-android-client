package com.example.sevennews.categories;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sevennews.R;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private Category[] categories = new Category[6] ;
    private Context context;
    public CategoryAdapter(Context context) {
        this.context = context;
        Category iran = new Category();
        iran.setId(1);
        iran.setTitle("Iran");
        iran.setIcon(R.drawable.category1);
        categories[0] = iran;

        Category business = new Category();
        business.setId(2);
        business.setTitle("Business");
        business.setIcon(R.drawable.category2);
        categories[1] = business;

        Category health = new Category();
        health.setId(3);
        health.setTitle("Health");
        health.setIcon(R.drawable.category3);
        categories[2] = health;

        Category technology = new Category();
        technology.setId(4);
        technology.setTitle("Technology");
        technology.setIcon(R.drawable.category4);
        categories[3] = technology;

        Category world = new Category();
        world.setId(5);
        world.setTitle("World");
        world.setIcon(R.drawable.category5);
        categories[4] = world;

        Category sport = new Category();
        sport.setId(6);
        sport.setTitle("Sport");
        sport.setIcon(R.drawable.category6);
        categories[5] = sport;






    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new CategoryViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_category,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder categoryViewHolder, int i) {
        categoryViewHolder.bindCategory(categories[i]);
    }

    @Override
    public int getItemCount() {
        return categories.length;
    }


    class CategoryViewHolder extends RecyclerView.ViewHolder{
        private TextView titleTextView;
        private ImageView iconImageView;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.tv_category_title);
            iconImageView = itemView.findViewById(R.id.iv_category_icon);
        }

        public void bindCategory(Category category){
            titleTextView.setText(category.getTitle());
            iconImageView.setImageResource(category.getIcon());
        }
    }

}
