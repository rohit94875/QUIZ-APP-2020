package adaptor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.coviam.quizapp.R;

import java.util.List;

import pojo.Category;

public class CategoryAdaptor extends RecyclerView.Adapter<CategoryAdaptor.ViewHolder>{

    List<Category> myCategory;
    CategoryDetails categoryDetails;

    public CategoryAdaptor(List<Category> category,CategoryDetails categoryDetails1)
    {
        this.myCategory=category;
        this.categoryDetails=categoryDetails1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.activity_category, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.textView.setText(myCategory.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryDetails.onClick(myCategory.get(position).getId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return myCategory.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public ConstraintLayout constraintLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            this.textView = itemView.findViewById(R.id.category);
            constraintLayout = itemView.findViewById(R.id.constraint);
        }
    }

    public interface CategoryDetails
    {
        void onClick(String categoryId);
    }
}
