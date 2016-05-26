package fragments;


//import android.app.Activity;
//import android.content.Context;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.paulcurle.syndicatemanager.R;

import java.util.ArrayList;

import adapters.SyndicateMembersAdapter;
import database.DbHelper;
import database.SyndicateMemberDAO;
import models.SyndicateMember;

/**
 * A simple {@link Fragment} subclass.
 * The code for this and the follow on editing fragment is done already.
 * Need to load the members into a class and list; see ValOkafor App 1 part 3?
 * Need to write the class out to a file if changed
 */
public class SyndicateMembersFragment extends Fragment {

    private static final String TAG = "SyndicateMembersFragment";
    private static final int DATASET_ROW_COUNT = 2;
    private static final int DATASET_COL_COUNT = 7;
    private RecyclerView mRecyclerView;
    //private RecyclerView.Adapter mAdapter;

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    protected LayoutManagerType mCurrentLayoutManagerType;
    protected SyndicateMembersAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<SyndicateMember> syndicateMemberList;

    private Context mContext;
    private DbHelper db;
    private boolean blnSuccess;



    public SyndicateMembersFragment() {
        // Required empty public constructor
    }


    /**

     * Returns a new instance of this fragment for the given section number.

     */

    //static removed from newInstance
    public SyndicateMembersFragment newInstance() {

        SyndicateMembersFragment fragment = new SyndicateMembersFragment();

        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mContext = getContext();
        //TODO Replace with call to dbhelper getallmembers db selection
        Log.i("Reading: ", "Reading all members..");
//        db = new DatabaseHelper(null);
//        db = DbHelper.getInstance(mContext);
        SyndicateMemberDAO smDAO = new SyndicateMemberDAO(mContext);

        syndicateMemberList = smDAO.getAllSyndicateMembers();



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        SyndicateMemberDAO smDAO = new SyndicateMemberDAO(mContext);
/*
mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
mRecyclerView.setHasFixedSize(true);
mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
mRecyclerView.setItemAnimator(new DefaultItemAnimator());
mRecyclerView.setAdapter(new DataBeanAdapter(dbAdapter.getAllData(), R.layout.item));
 */


        View rootView = inflater.inflate(R.layout.fragment_syndicate_members, container, false);
        rootView.setTag(TAG);

        // BEGIN_INCLUDE(initializeRecyclerView)
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.draw_history_recycler_view);

        // LinearLayoutManager is used here, this will layout the elements in a similar fashion
        // to the way ListView would layout elements. The RecyclerView.LayoutManager defines how
        // elements are laid out.
        mLayoutManager = new LinearLayoutManager(getActivity());

        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        setRecyclerViewLayoutManager(mCurrentLayoutManagerType);

        syndicateMemberList = smDAO.getAllSyndicateMembers();
        mAdapter = new SyndicateMembersAdapter(syndicateMemberList, getActivity());   //(euroDrawList, getActivity());
        // Set CustomAdapter as the adapter for RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        // END_INCLUDE(initializeRecyclerView)

        return rootView;
    }

    /**
     * Set RecyclerView's LayoutManager to the one given.
     *
     * @param layoutManagerType Type of layout manager to switch to.
     */
    public void setRecyclerViewLayoutManager(LayoutManagerType layoutManagerType) {
        int scrollPosition = 0;

        // If a layout manager has already been set, get current scroll position.
        if (mRecyclerView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.scrollToPosition(scrollPosition);
    }
    /**
     * Generates Strings for RecyclerView's adapter. This data would usually come
     * from a local content provider or remote server.
     */


}
