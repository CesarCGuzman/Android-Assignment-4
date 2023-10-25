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

import java.util.ArrayList;

import edu.uncc.assignment04.databinding.FragmentUsersBinding;
public class UsersFragment extends Fragment {
    private static final String ARG_PARAM_USERS = "users";


    private ArrayList<User> mUsers;
    ListView listView;
    UserAdapter adapter;
    public UsersFragment() {
        // Required empty public constructor
    }

    public static UsersFragment newInstance(ArrayList<User> users) {
        UsersFragment fragment = new UsersFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_USERS, users);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mUsers = (ArrayList<User>)getArguments().getSerializable(ARG_PARAM_USERS);
        }
    }

    FragmentUsersBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUsersBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Users");
        listView = binding.listView;
        adapter = new UserAdapter(getContext(), R.layout.list_item_user, mUsers);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view1, position, id) -> {
            User user = mUsers.get(position);
            mListener.goToUserDetails(user);
        });

        binding.buttonAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.goToNewUser();
            }
        });

        binding.buttonClearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUsers.clear();
                adapter.notifyDataSetChanged();
            }
        });
    }

    UserListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (UserListener) context;
    }

    public interface UserListener{
        void clearAllUsers();
        void goToUserDetails(User user);
        void goToNewUser();
    }

}
