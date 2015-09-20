package co.rysr.rysr;

import android.graphics.Color;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

import co.rysr.rysr.intro.CharitySelectFragment;
import co.rysr.rysr.intro.LogoFragment;
import co.rysr.rysr.intro.PaymentSetupFragment;

public class SplashActivity extends AppIntro {

    @Override
    public void init(Bundle savedInstanceState) {

        // Add your slide's fragments here
        // AppIntro will automatically generate the dots indicator and buttons.
        addSlide(LogoFragment.newInstance());
        addSlide(CharitySelectFragment.newInstance());
        addSlide(PaymentSetupFragment.newInstance());

        // Instead of fragments, you can also use our default slide
        // Just set a title, description, background and image. AppIntro will do the rest
        addSlide(AppIntroFragment.newInstance("test", "description", R.drawable.ic_arrow_forward_white_24px, Color.RED));

        // Override bar/separator color
        setBarColor(getResources().getColor(R.color.primary_dark));
        setSeparatorColor(Color.parseColor("#2196F3"));

        // Hide Skip/Done button
        showSkipButton(false);
        showDoneButton(false);

        // Turn vibration on and set intensity
        setVibrate(true);
        setVibrateIntensity(30);
    }

    @Override
    public void onSkipPressed() {
        // Do something when users tap on Skip button.
    }

    @Override
    public void onDonePressed() {
        // Do something when users tap on Done button.
    }
}
