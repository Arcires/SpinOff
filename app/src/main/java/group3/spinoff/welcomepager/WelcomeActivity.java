package group3.spinoff.welcomepager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import group3.spinoff.R;
import group3.spinoff.RoleSelectionActivity;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager viewPager;
    private int[] viewPagerLayouts = {R.layout.welcomepager_welcomescreen, R.layout.welcomepager_meetingscreen, R.layout.welcomepage_anonymousscreen, R.layout.welcomepage_feedbackscreen};

    private ViewPagerAdapter viewPagerAdapter;

    private LinearLayout layoutDots;
    private ImageView[] imageViewdots;

    private Button buttonFinish, buttonSkip;

    SharedPreferences sharedPrefs;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        Boolean skipIntro = sharedPrefs.getBoolean("skipWelcomePager", false);
        if (skipIntro) {
            loadHome();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomeviewpager);

        viewPager = findViewById(R.id.viewPagerWelcome);

        viewPagerAdapter = new ViewPagerAdapter(viewPagerLayouts, this);
        viewPager.setAdapter(viewPagerAdapter);

        buttonFinish = findViewById(R.id.buttonWelcomePagerFinish);
        buttonSkip = findViewById(R.id.buttonWelcomePagerSkip);

        buttonFinish.setOnClickListener(this);
        buttonSkip.setOnClickListener(this);

        layoutDots = findViewById(R.id.graphDotsLayout);
        createDots(0);

        editor = sharedPrefs.edit();

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                createDots(position);
                if (position == viewPagerLayouts.length - 1) {
                    buttonSkip.setVisibility(View.INVISIBLE);
                    buttonFinish.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }


    private void createDots(int currentPosition) {
        if (layoutDots != null) {
            layoutDots.removeAllViews();
            imageViewdots = new ImageView[viewPagerLayouts.length];

            for (int i = 0; i < viewPagerLayouts.length; i++) {
                imageViewdots[i] = new ImageView(this);
                if (i == currentPosition) {
                    imageViewdots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.active_dots));
                } else {
                    imageViewdots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.inactive_dots));
                }

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.setMargins(4, 0, 4, 0);

                layoutDots.addView(imageViewdots[i], params);
            }

        }
    }


    @Override
    public void onClick(View view) {
        if (view == buttonSkip || view == buttonFinish) {
            loadHome();
            editor.putBoolean("skipWelcomePager", true).apply();
        }
    }

    private void loadNextSlide() {
        int nextSlide = viewPager.getCurrentItem() + 1;
        if (nextSlide < viewPagerLayouts.length) {
            viewPager.setCurrentItem(nextSlide);
        } else {
            loadHome();
            editor.putBoolean("skipWelcomePager", true).apply();
        }
    }

    private void loadHome() {
        Intent i = new Intent(this, RoleSelectionActivity.class);
        startActivity(i);
        finish();
    }


}

