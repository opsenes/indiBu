package com.internship.mts.internproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.internship.mts.internproject.base.BaseFragment;


public class PlaceHolderFragment extends BaseFragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    ImageView img;

    int[] bgs = new int[]{R.drawable.indirimleri_paylas, R.drawable.firsatlari_kacirma, R.drawable.kuponlari_sat};

    public PlaceHolderFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlaceHolderFragment newInstance(int sectionNumber) {
        PlaceHolderFragment fragment = new PlaceHolderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_onboarding_fragment, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.section_label);

        switch (getArguments().getInt(ARG_SECTION_NUMBER)) {
            case 1:
                textView.setText(R.string.onboarding_share);
                break;
            case 2:
                textView.setText(R.string.onboarding_follow);
                break;
            case 3:
                textView.setText(R.string.onboarding_sell);
                break;
        }

        img = (ImageView) rootView.findViewById(R.id.section_img);
        img.setBackgroundResource(bgs[getArguments().getInt(ARG_SECTION_NUMBER) - 1]);

        return rootView;
    }
}
