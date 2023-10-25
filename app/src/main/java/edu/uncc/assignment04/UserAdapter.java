package edu.uncc.assignment04;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class UserAdapter extends ArrayAdapter<User> {
    public UserAdapter(@NonNull Context context, int resource, @NonNull List<User> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_user, parent, false);
        }

        User user = getItem(position);
        TextView textViewName = convertView.findViewById(R.id.textViewUserName);
        TextView textViewAge = convertView.findViewById(R.id.textViewUserAgeGroup);
        ImageView imageViewMood = convertView.findViewById(R.id.imageViewUserMood);

        textViewName.setText(user.name);
        textViewAge.setText(user.age_value);
        setMoodImage(user.mood_image_resource, imageViewMood);


        return convertView;
    }

    public static void setMoodImage(int mood_value, ImageView imageView){
        if(mood_value == 4){
            imageView.setImageResource(R.drawable.not_well);
        } else if(mood_value == 3){
            imageView.setImageResource(R.drawable.sad);
        } else if(mood_value == 2){
            imageView.setImageResource(R.drawable.ok);
        } else if(mood_value == 1){
            imageView.setImageResource(R.drawable.good);
        } else if(mood_value == 0){
            imageView.setImageResource(R.drawable.very_good);
        }
    }
}
