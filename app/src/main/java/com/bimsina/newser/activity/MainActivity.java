package com.bimsina.newser.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.bimsina.newser.R;
import com.bimsina.newser.fragment.HomeFragment;

public class MainActivity extends AppCompatActivity {


    Toolbar mainToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mainToolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setTitle("");
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        }
        HomeFragment homeFragment = new HomeFragment();
        replaceFragment(homeFragment);

    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainContainer, fragment, "frag");
        fragmentTransaction.commit();
    }
}
