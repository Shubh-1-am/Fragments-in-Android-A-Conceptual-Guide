package com.example.navigationdrawertutorial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    MaterialToolbar mMaterialToolbar;
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMaterialToolbar = findViewById(R.id.topAppBar);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mNavigationView = findViewById(R.id.nav_view);

        mMaterialToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                mDrawerLayout.closeDrawer(GravityCompat.START);

                switch (item.getItemId()){
                    case R.id.home:
                        replaceFragment(new HomeFragment()); break;
                    case R.id.message:
                        replaceFragment(new MessageFragment()); break;
                    case R.id.settings:
                        replaceFragment(new SettingFragment()); break;
                    case R.id.login:
                        removeFragment();
                        Toast.makeText(MainActivity.this, "Login", Toast.LENGTH_SHORT).show();; break;
                    case R.id.sync:
                        removeFragment();
                        Toast.makeText(MainActivity.this, "Sync", Toast.LENGTH_SHORT).show();; break;
                    case R.id.trash:
                        removeFragment();
                        Toast.makeText(MainActivity.this, "Trash", Toast.LENGTH_SHORT).show();; break;
                    case R.id.share:
                        Toast.makeText(MainActivity.this, "Share", Toast.LENGTH_SHORT).show();; break;
                    case R.id.rate:
                        Toast.makeText(MainActivity.this, "Rate", Toast.LENGTH_SHORT).show();; break;
                    default: return true;
                }
                return  true;
            }
        });


    }

    private void replaceFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,fragment).commit();

    }
    private void removeFragment(){
        for (Fragment fragment : getSupportFragmentManager().getFragments()){
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
        }
    }
}