package du.com.myapplication;

import android.Manifest;
import android.app.Activity;
import android.arch.lifecycle.MutableLiveData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private static final int PICK_IMAGE = 1;
    final Activity activity = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                                                new HomeFragment()).commit();
        checkPermission();
    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;
                    switch(menuItem.getItemId()){
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.nav_favorites:
                            selectedFragment = new FavouriteFragment();
                            break;
                        case R.id.nav_search:
                            selectedFragment = new SearchFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();
                    return true;
            }
    };

    private void checkPermission(){
        int permission_camera = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        int permission_write  = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permission_read  = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if(permission_camera != PackageManager.PERMISSION_GRANTED
           || permission_read != PackageManager.PERMISSION_GRANTED
           || permission_write != PackageManager.PERMISSION_GRANTED ){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA,
                                                                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                                                        Manifest.permission.READ_EXTERNAL_STORAGE},
                                                                        1);
        }

    }


}

