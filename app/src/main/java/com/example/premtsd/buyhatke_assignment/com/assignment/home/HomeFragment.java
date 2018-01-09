package com.example.premtsd.buyhatke_assignment.com.assignment.home;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.premtsd.buyhatke_assignment.R;
import com.google.android.gms.plus.PlusOneButton;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;

/**
 * A fragment with a Google +1 button.
 * Activities that contain this fragment must implement the
 * {@link OnClickInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static ViewPager mPager;
    private static final Integer[] IMAGES = {R.drawable.flipkart_upcomig_offer, R.drawable.flipkart_diwali_sale, R.drawable.flipkart_big_shopping_days_image};
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();
    private OnClickInteractionListener mListener;
    private RelativeLayout viewPagerLayout;
    private TextView txtAmazon;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment HomeFragment.
     */
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_plus_one, container, false);
        init(view);
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnClickInteractionListener) {
            mListener = (OnClickInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnClickInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.amazon) {
            mListener.onItemCLickInteraction(getResources().getString(R.string.amazon));
        }else if(view.getId() == R.id.myntra){
            mListener.onItemCLickInteraction(getResources().getString(R.string.myntra));
        }else{
            Toast.makeText(getActivity(),R.string.click_amazon_or_myntra,Toast.LENGTH_LONG).show();
        }
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnClickInteractionListener {
        void onItemCLickInteraction(String status);
    }

    private void init(View view) {
        viewPagerLayout = (RelativeLayout) view.findViewById(R.id.viewPagerLayout);
        txtAmazon = (TextView) view.findViewById(R.id.amazon);
        txtAmazon.setOnClickListener(this);
        viewPagerLayout.setVisibility(View.VISIBLE);
        for (int i = 0; i < IMAGES.length; i++)
            ImagesArray.add(IMAGES[i]);

        mPager = (ViewPager) view.findViewById(R.id.pager);
        mPager.setAdapter(new SlidingImageAdapter(getActivity(), ImagesArray));

        CircleIndicator indicator = (CircleIndicator) view.findViewById(R.id.indicator);
        indicator.setViewPager(mPager);
    }
}
