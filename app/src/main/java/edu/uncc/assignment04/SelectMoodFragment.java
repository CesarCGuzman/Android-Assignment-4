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
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import edu.uncc.assignment04.databinding.FragmentSelectMoodBinding;

public class SelectMoodFragment extends Fragment {

    List<String> moods = Arrays.asList("Very Good", "Good", "Ok", "Sad", "Not Well");
    ListView listView;
    SelectMoodFragmentAdapter adapter;

    public SelectMoodFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    FragmentSelectMoodBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSelectMoodBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Select Mood");

        listView = binding.listView;
        adapter = new SelectMoodFragmentAdapter(getActivity(), R.layout.list_item_mood, moods);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view1, position, id) -> {
            mListener.sendSelectedMood(position);
        });

        binding.buttonCancel.setOnClickListener(v -> {
            mListener.cancelMoodSelection();
        });
    }

    MoodFragmentListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (MoodFragmentListener) context;
    }

    interface MoodFragmentListener {
        void sendSelectedMood(int moodValue);
        void cancelMoodSelection();
    }
}