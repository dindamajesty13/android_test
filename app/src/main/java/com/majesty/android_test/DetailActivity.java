package com.majesty.android_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.majesty.android_test.databinding.ActivityDetailBinding;
import com.majesty.android_test.fragment.DetailFragment;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDetailBinding activityDetailBinding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(activityDetailBinding.getRoot());
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        if (savedInstanceState == null) {
            setUpDetailFragment();
        }
    }

    private void setUpDetailFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container_view, new DetailFragment())
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