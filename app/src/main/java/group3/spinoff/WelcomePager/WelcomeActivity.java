package group3.spinoff.WelcomePager;

import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import group3.spinoff.R;

public class WelcomeActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private int[] viewPagerLayouts = {R.layout.welcomepager_welcomescreen, R.layout.welcomepager_meetingscreen, R.layout.welcomepage_anonymousscreen, R.layout.welcomepage_feedbackscreen};

    private ViewPagerAdapter viewPagerAdapter;

    private LinearLayout layoutDots;
    private ImageView[] imageViewdots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        viewPager = findViewById(R.id.viewPagerWelcome);

        viewPagerAdapter = new ViewPagerAdapter(viewPagerLayouts, this);
        viewPager.setAdapter(viewPagerAdapter);

        layoutDots = findViewById(R.id.dotsLayout);
        createDots(0);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                createDots(position);
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


}

