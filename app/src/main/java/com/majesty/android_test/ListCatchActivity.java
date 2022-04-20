package com.majesty.android_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.majesty.android_test.databinding.ActivityListCatchBinding;
import com.majesty.android_test.databinding.ActivityMainBinding;
import com.majesty.android_test.fragment.ListCatchFragment;
import com.majesty.android_test.fragment.MainFragment;

public class ListCatchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityListCatchBinding activityListCatchBinding = ActivityListCatchBinding.inflate(getLayoutInflater());
        setContentView(activityListCatchBinding.getRoot());
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        if (savedInstanceState == null) {
            setUpListCatchFragment();
        }
    }

    private void setUpListCatchFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container_view, new ListCatchFragment())
                .setReorderingAllowed(true)
                .commit();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY); //The IMMERSIVE_STICKY use to hide Navigation Bar after short time don't touch on it
        }
    }
}