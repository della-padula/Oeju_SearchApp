package com.elliott.searchactivity.result;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.elliott.searchactivity.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ResultActivity extends AppCompatActivity {

    // Bottom Navigation View로 탭으로 화면을 3개 분할하여 구성
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private NaverFragment   naverFragment; // Naver
    private DaumFragment    daumFragment; // Daum
    private GoogleFragment  googleFragment; // Google
    private ImageButton backImageButton; // Back Button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        naverFragment   = new NaverFragment();
        daumFragment = new DaumFragment();
        googleFragment = new GoogleFragment();
        backImageButton = findViewById(R.id.ib_exit_add_story);

        backImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, naverFragment).commitAllowingStateLoss();

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());
    }

    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            // 하단 버튼을 누를 경우 화면을 전환하기 위한 코드
            switch(menuItem.getItemId()) {
                case R.id.menu_naver:
                    transaction.replace(R.id.frameLayout, naverFragment).commitAllowingStateLoss();
                    break;
                case R.id.menu_daum:
                    transaction.replace(R.id.frameLayout, daumFragment).commitAllowingStateLoss();
                    break;
                case R.id.menu_google:
                    transaction.replace(R.id.frameLayout, googleFragment).commitAllowingStateLoss();
                    break;
            }
            return true;
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
