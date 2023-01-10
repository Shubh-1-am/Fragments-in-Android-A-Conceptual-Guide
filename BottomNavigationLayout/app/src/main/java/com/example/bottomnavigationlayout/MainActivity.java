package com.example.bottomnavigationlayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView mBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         mBottomNavigationView = findViewById(R.id.bottom_nav);
         getSupportFragmentManager().beginTransaction().replace(R.id.parent_conatiner, new HomeFragment()).commit();

         mBottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
             @Override
             public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                 Fragment fragment = null;
                 switch (item.getItemId()) {
                    case  R.id.home:
                       fragment = new HomeFragment(); break;

                     case  R.id.search:
                      fragment = new SearchFragment(); break;

                     case  R.id.fav:
                         fragment = new FavFragment(); break;

                     case  R.id.shop:
                         fragment = new ShopFragment(); break;

                 }

                 getSupportFragmentManager().beginTransaction().replace(R.id.parent_conatiner,fragment).commit();

                 return true;
             }
         });

         /*
         This same can also be implemented in a viewpager2
          */

    }
}