package edu.uncc.assignment04;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.uncc.assignment04.databinding.FragmentAddUserBinding;

public class AddUserFragment extends Fragment {
    private static final String ARG_PARAM_USERS = "users";
    ArrayList<User> mUsers;
    static String selectedAgeGroup = null;
    static int selectedMood = -1;

    public static void resetValues() {
        selectedAgeGroup = null;
        selectedMood = -1;
    }

    public void updateAgeGroup (String selectedAgeGroup) {
        this.selectedAgeGroup = selectedAgeGroup;
    }

    public void updateMood (int selectedMood) {
        this.selectedMood = selectedMood;
    }

    public AddUserFragment() {
        // Required empty public constructor
    }

    public static AddUserFragment newInstance(ArrayList<User> users) {
        AddUserFragment fragment = new AddUserFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_USERS, users);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mUsers = (ArrayList<User>) getArguments().getSerializable(ARG_PARAM_USERS);
        }
    }

    FragmentAddUserBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddUserBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Add User");
        ImageView imageView = binding.imageViewMood;

        if (selectedAgeGroup != null) {
            binding.textViewAgeGroup.setText(selectedAgeGroup);
        }

        if (selectedMood != -1) {
            setArgParamUsers(selectedMood, imageView, binding.textViewMood);
        }

        binding.buttonSelectAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.goToSelectAge();
            }
        });

        binding.buttonSelectMood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.goToSelectMood();
            }
        });

        binding.buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.editTextText.getText().toString();
                if (name.isEmpty()) {
                    Toast.makeText(getActivity(), "Enter a valid name", Toast.LENGTH_SHORT).show();
                } else if (selectedAgeGroup == null) {
                    Toast.makeText(getActivity(), "Select Age Group", Toast.LENGTH_SHORT).show();
                } else if (selectedMood == -1) {
                    Toast.makeText(getActivity(), "Select Mood", Toast.LENGTH_SHORT).show();
                } else {
                    User user = new User(name, selectedAgeGroup, selectedMood);
                    mUsers.add(user);
                    mListener.sendUsers(mUsers);
                }
            }
        });
    }

    AddUserListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (AddUserListener) context;
    }

    interface AddUserListener{
        void goToSelectAge();
        void goToSelectMood();
        void sendUsers(ArrayList<User> users);
    }

    public static void setArgParamUsers(int mood_value, ImageView imageView, TextView textView){
        imageView.setVisibility(View.VISIBLE);
        if(mood_value == 4){
            imageView.setImageResource(R.drawable.not_well);
            textView.setText("Not Well");
        } else if(mood_value == 3){
            imageView.setImageResource(R.drawable.sad);
            textView.setText("Sad");
        } else if(mood_value == 2){
            imageView.setImageResource(R.drawable.ok);
            textView.setText("Ok");
        } else if(mood_value == 1){
            imageView.setImageResource(R.drawable.good);
            textView.setText("Good");
        } else if(mood_value == 0){
            imageView.setImageResource(R.drawable.very_good);
            textView.setText("Very Good");
        } else {
            imageView.setImageResource(R.drawable.not_well);
            textView.setText("Something's wrong");
        }
    }
}