package edu.uncc.assignment04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

import edu.uncc.assignment04.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements UsersFragment.UserListener,
                                                               ProfileFragment.ProfileFragmentListener,
                                                               AddUserFragment.AddUserListener,
                                                               SelectAgeGroupFragment.AgeGroupListener,
                                                               SelectMoodFragment.MoodFragmentListener {

    ArrayList<User> mUsers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.rootView, UsersFragment.newInstance(mUsers), "home-fragment")
                .commit();
    }

    @Override
    public void clearAllUsers() {
        mUsers.clear();
        AddUserFragment.resetValues();
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void goToUserDetails(User user) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, ProfileFragment.newInstance(user))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void goToNewUser() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, AddUserFragment.newInstance(mUsers), "add-user-fragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void goToUsers() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void goToSelectAge() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new SelectAgeGroupFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void goToSelectMood() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new SelectMoodFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void sendUsers(ArrayList<User> users) {
        mUsers = users;
        AddUserFragment.resetValues();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, UsersFragment.newInstance(mUsers))
                .commit();
    }

    @Override
    public void sendSelectedAgeGroup(String ageGroup) {
        AddUserFragment addUserFragment = (AddUserFragment) getSupportFragmentManager().findFragmentByTag("add-user-fragment");
        if (addUserFragment != null) {
            addUserFragment.updateAgeGroup(ageGroup);
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public void cancelAgeGroupSelection() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void sendSelectedMood(int moodValue) {
        AddUserFragment addUserFragment = (AddUserFragment) getSupportFragmentManager().findFragmentByTag("add-user-fragment");
        if (addUserFragment != null) {
            addUserFragment.updateMood(moodValue);
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public void cancelMoodSelection() {
        getSupportFragmentManager().popBackStack();
    }
}