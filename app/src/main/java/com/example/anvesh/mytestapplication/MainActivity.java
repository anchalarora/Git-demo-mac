package com.example.anvesh.mytestapplication;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.transition.TransitionInflater;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    Context context;
    FragmentManager frgManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = MainActivity.this;
        getSupportActionBar().setTitle("Upcoming Movies");
        frgManager = getSupportFragmentManager();
        Log.d(Const.TAG, " Main Activity ");
        addInitialFragment();
        //Fragment fragment = new MovieListFragment();
        //replaceFragment(fragment, MovieListFragment.class.getSimpleName());

    }

    public void addInitialFragment() {
        Fragment fragment = new MovieListFragment();
        FragmentTransaction fragmentTransaction = frgManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_content_frame, fragment).commit();
        frgManager.executePendingTransactions();
    }

    public void addInitialFragment2(Movies str) {
        Fragment fragment = MovieDetailFragment.newInstance(str);
        FragmentTransaction fragmentTransaction = frgManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_content_frame, fragment).commit();
        frgManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        frgManager.executePendingTransactions();
    }


    public void replaceFragment(Fragment newFragment, String transactionTag) {
        if (newFragment != null) {
            FragmentTransaction fragmentTransaction = frgManager.beginTransaction();
            fragmentTransaction.addToBackStack(transactionTag);
            fragmentTransaction.replace(R.id.main_content_frame, newFragment).addToBackStack(null).commit();
            frgManager.executePendingTransactions();
        } else {
            Log.d(Const.TAG, " check the fragment what u passed ");
        }
    }

    public void onMovieDetailSelected(Movies str) {
        Fragment fragment = MovieDetailFragment.newInstance(str);
        if (android.os.Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            fragment.setReturnTransition(TransitionInflater.from(this).inflateTransition(android.R.transition.no_transition));
        }
        replaceFragment(fragment, MovieDetailFragment.class.getSimpleName());
    }


}
