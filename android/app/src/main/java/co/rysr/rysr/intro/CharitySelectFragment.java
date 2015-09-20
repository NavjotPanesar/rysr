package co.rysr.rysr.intro;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.rysr.rysr.R;
import fr.castorflex.android.verticalviewpager.VerticalViewPager;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CharitySelectFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CharitySelectFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CharitySelectFragment extends Fragment {

    @Bind(R.id.view_pager) VerticalViewPager viewPager;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment CharitySelectFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CharitySelectFragment newInstance() {
        return new CharitySelectFragment();
    }

    public CharitySelectFragment() {
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
        View view = inflater.inflate(R.layout.fragment_charity_select, container, false);

        ButterKnife.bind(this, view);

        ImagePagerAdapter adapter = new ImagePagerAdapter();
        viewPager.setAdapter(adapter);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        try {
//            mListener = (OnFragmentInteractionListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     *
     * https://github.com/chiuki/android-swipe-image-viewer/blob/master/src/com/sqisland/android/swipe_image_viewer/MainActivity.java
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private class ImagePagerAdapter extends PagerAdapter {
        private int[] mImages = new int[] {
                R.drawable.charity_doctors_without_borders,
                R.drawable.charity_nature_conservancy,
                R.drawable.charity_unicef,
                R.drawable.charity_wwf
        };

        @Override
        public int getCount() {
            return mImages.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((ImageView) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Context context = getActivity();
            ImageView imageView = new ImageView(context);
            int padding = context.getResources().getDimensionPixelSize(
                    R.dimen.padding_medium);
            imageView.setPadding(padding, 0, padding, padding);
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            imageView.setImageResource(mImages[position]);
            container.addView(imageView, 0);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((ImageView) object);
        }
    }

}
