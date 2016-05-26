package fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.paulcurle.syndicatemanager.R;

import activities.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 * Returns a new instance of this fragment for the given section number.
 * Fragment displays text about the app
 */
public class AboutSyndicateManagerFragment extends Fragment {


    public AboutSyndicateManagerFragment() {
        // Required empty public constructor
    }

    public static AboutSyndicateManagerFragment newInstance() {

        AboutSyndicateManagerFragment fragment = new AboutSyndicateManagerFragment();

        return fragment;

    }
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,

                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_about_syndicate_manager, container,

                false);

        return rootView;

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity a;

        if (context instanceof Activity){
            a=(Activity) context;
        }

    }
}