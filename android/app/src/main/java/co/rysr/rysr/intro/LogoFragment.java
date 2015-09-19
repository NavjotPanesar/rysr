package co.rysr.rysr.intro;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.rysr.rysr.R;


public class LogoFragment extends Fragment {
    @Bind(R.id.imageViewIcon) ImageView imageViewIcon;
    @Bind(R.id.imageViewLogoTypography) ImageView imageViewLogoTypography;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment LogoFragment.
     */
    public static LogoFragment newInstance() {
        return new LogoFragment();
    }

    public LogoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_logo, container, false);
        ButterKnife.bind(this, view);

        // Setup animations
        Animation slideUp = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_up);
        Animation slideDown = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_down);
        slideDown.setStartOffset(300);

        imageViewIcon.startAnimation(slideUp);
        imageViewLogoTypography.startAnimation(slideDown);

        return view;
    }
}
