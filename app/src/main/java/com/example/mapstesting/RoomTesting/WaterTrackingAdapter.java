package com.example.mapstesting.RoomTesting;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mapstesting.R;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class WaterTrackingAdapter extends RecyclerView.Adapter<WaterTrackingAdapter.ViewHolder> {

    private final List<WaterTracking> waterTrackingList;

    // Constructor to initialize the list
    public WaterTrackingAdapter(List<WaterTracking> waterTrackingList) {
        this.waterTrackingList = waterTrackingList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WaterTracking item = waterTrackingList.get(position);

        // Format the LocalDate for display
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        holder.textViewDate.setText(item.date.format(formatter));
        holder.textViewWaterIntake.setText("Water Intake: " + item.waterIntake + " ml");
        holder.textViewCategory.setText("Category: " + item.category);
        holder.textViewDescription.setText("Description: " + item.description);

        if (item.category.equals("Sweetened")) {
            holder.imageView.setImageResource(R.drawable.soft_drink);
        } else if (item.category.equals("Non-Sweetened")) {
            holder.imageView.setImageResource(R.drawable.food_no_sugar);
        } else if(item.category.equals("Plain Water")) {
            holder.imageView.setImageResource(R.drawable.bottle_of_water);
        } else {
            holder.imageView.setImageResource(R.drawable.ic_launcher_foreground);
        }



    }

    @Override
    public int getItemCount() {
        return waterTrackingList.size();
    }

    // ViewHolder class to hold views for each item
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewDate, textViewWaterIntake, textViewCategory, textViewDescription;
        ImageView imageView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewDate = itemView.findViewById(R.id.textViewDate);
            textViewWaterIntake = itemView.findViewById(R.id.textViewWaterIntake);
            textViewCategory = itemView.findViewById(R.id.textViewCategory);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
