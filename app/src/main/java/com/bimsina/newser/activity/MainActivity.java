package com.bimsina.newser.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.bimsina.newser.R;
import com.bimsina.newser.fragment.TrendingFragment;

public class MainActivity extends AppCompatActivity {


    Toolbar mainToolbar;
   // BottomNavigationView mainBottomNav;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainToolbar = findViewById(R.id.toolbar);
        //mainBottomNav = findViewById(R.id.mainBottomNav);
        setSupportActionBar(mainToolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setTitle("");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_search);
        }
        final TrendingFragment trendingFragment = new TrendingFragment();
        replaceFragment(trendingFragment);

//        mainBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                switch (menuItem.getItemId()){
//                    case R.id.nav_trending:
//                        replaceFragment(trendingFragment);
//                        break;
//                    case R.id.nav_saved:
//                        break;
//
//                }
//                return false;
//            }
//        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainContainer, fragment, "frag");
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id){
            case R.id.person_menu:
                Toast.makeText(getApplicationContext(),"Clicked",Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                Toast.makeText(getApplicationContext(),"Search",Toast.LENGTH_SHORT).show();
                break;
        }

        return true;
    }

}
