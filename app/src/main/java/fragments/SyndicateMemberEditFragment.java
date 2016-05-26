package fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.paulcurle.syndicatemanager.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

//import adapters.SyndicateMembersAdapter;
//import models.AllMembers;

import adapters.SyndicateMembersAdapter;
import database.DbHelper;
import database.SyndicateMemberDAO;
import models.SyndicateMember;
import utilities.EuroDrawNumberChecker;
//import utilities.FileHandlers;

/**
 * A simple {@link Fragment} subclass.
 */
public class SyndicateMemberEditFragment extends Fragment {

    private static final String TAG = "EditSyndicateMemberFragment";
    private static final int DATASET_ROW_COUNT = 2;
    private static final int DATASET_COL_COUNT = 7;
    private RecyclerView mRecyclerView;
    //Screen input fields fields
    private ViewGroup group;
    private EditText memberName;
    private EditText memberEmail;
    private EditText ball1;
    private EditText ball2;
    private EditText ball3;
    private EditText ball4;
    private EditText ball5;
    private EditText star1;
    private EditText star2;
    //
    private String mName;
    private String mEmail;
    private int[] drawArray = new int[7];
    private int[] ballArray = new int[5];
    private int[] starArray = {0,0};

    //Buttons
    private Button btnAddEdit;
    //Variables
    private String errorMessage = "";

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }
    protected LayoutManagerType mCurrentLayoutManagerType;
    protected SyndicateMembersAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<SyndicateMember> syndicateMemberList;
    private ArrayList<SyndicateMember> mDrawList;

    private Context mContext;

    private DbHelper dbh;



    public SyndicateMemberEditFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //TODO Replace with call to dbhelper getallmembers db selection if there are any

//        if (db.getMemberCount() > 0) {
//            ArrayList<SyndicateMember> members = db.getMembers(1);
//        }

        //syndicateMemberList = AllMembers.get(getActivity()).getallMembers();

        mContext = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final ViewGroup mContainer = container;
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_syndicate_member_edit, container, false);
        View rootView = inflater.inflate(R.layout.fragment_syndicate_member_edit, container, false);

        rootView.setTag(TAG);

        memberName = (EditText) rootView.findViewById(R.id.memberName);
        memberEmail = (EditText) rootView.findViewById(R.id.memberEmail);
        ball1 = (EditText) rootView.findViewById(R.id.drawBall1);
        ball2 = (EditText) rootView.findViewById(R.id.drawBall2);
        ball3 = (EditText) rootView.findViewById(R.id.drawBall3);
        ball4 = (EditText) rootView.findViewById(R.id.drawBall4);
        ball5 = (EditText) rootView.findViewById(R.id.drawBall5);
        star1 = (EditText) rootView.findViewById(R.id.drawLuckyStar1);
        star2 = (EditText) rootView.findViewById(R.id.drawLuckyStar2);
        //
        memberName.addTextChangedListener(new TextValidator(memberName) {
            @Override
            public void validate(TextView textView, String text) {
           /* Validation code here */
                if(!EuroDrawNumberChecker.isNameValid(memberName)) {
                    errorMessage = "Invalid name details, only alpha characters accepted";
                    memberName.setError(errorMessage);
                }
                mName = memberName.getText().toString();

            }
        });

        memberEmail.addTextChangedListener(new TextValidator(memberEmail) {
            @Override
            public void validate(TextView textView, String text) {
           /* Validation code here */
                if(!EuroDrawNumberChecker.isEmailValid(memberEmail.getText().toString())) {
                    errorMessage = "Invalid email address";
                    memberEmail.setError(errorMessage);
                }
                mEmail = memberEmail.getText().toString();
            }
        });

        //Drawn ball entry validation
        ball1.addTextChangedListener(new TextValidator(ball1) {
            @Override
            public void validate(TextView textView, String text) {
           /* Validation code here */
                errorMessage = EuroDrawNumberChecker.checkTextInput(text, "Ball");
                if (errorMessage != "") {
                    ball1.setError(errorMessage);
                    ballArray[0] = 0;
                }
                else {
                    errorMessage = EuroDrawNumberChecker.checkForDuplicates(ballArray, Integer.parseInt(text), 0);
//                    errorMessage = checkForDuplicates(ballArray, Integer.parseInt(text), 0);
                    if (errorMessage != "") {
                        ball1.setError(errorMessage);
                        ballArray[0] = 0;
                    } else {
                        ballArray[0] = Integer.parseInt(text);
                    }
                }
            }
        });

        ball2.addTextChangedListener(new TextValidator(ball2) {
            @Override
            public void validate(TextView textView, String text) {
           /* Validation code here */
                errorMessage = EuroDrawNumberChecker.checkTextInput(text, "Ball");
                if (errorMessage != "") {
                    ball2.setError(errorMessage);
                    ballArray[1] = 0;
                } else {
                    errorMessage = EuroDrawNumberChecker.checkForDuplicates(ballArray, Integer.parseInt(text), 1);
                    if (errorMessage != "") {
                        ball2.setError(errorMessage);
                        ballArray[1] = 0;
                    } else {
                        ballArray[1] = Integer.parseInt(text);
                    }
                }
            }
        });

        ball3.addTextChangedListener(new TextValidator(ball3) {
            @Override
            public void validate(TextView textView, String text) {
           /* Validation code here */
                errorMessage = EuroDrawNumberChecker.checkTextInput(text, "Ball");
                if (errorMessage != "") {
                    ball3.setError(errorMessage);
                    ballArray[2] = 0;
                } else {
                    errorMessage = EuroDrawNumberChecker.checkForDuplicates(ballArray, Integer.parseInt(text), 2);
                    if (errorMessage != "") {
                        ball3.setError(errorMessage);
                        ballArray[2] = 0;
                    } else {
                        ballArray[2] = Integer.parseInt(text);
                    }
                }
            }
        });

        ball4.addTextChangedListener(new TextValidator(ball4) {
            @Override
            public void validate(TextView textView, String text) {
           /* Validation code here */
                errorMessage = EuroDrawNumberChecker.checkTextInput(text, "Ball");
                if (errorMessage != "") {
                    ball4.setError(errorMessage);
                    ballArray[3] = 0;
                } else {
                    errorMessage = EuroDrawNumberChecker.checkForDuplicates(ballArray, Integer.parseInt(text), 3);
                    if (errorMessage != "") {
                        ball4.setError(errorMessage);
                        ballArray[3] = 0;
                    } else {
                        ballArray[3] = Integer.parseInt(text);
                    }
                }
            }
        });

        ball5.addTextChangedListener(new TextValidator(ball5) {
            @Override
            public void validate(TextView textView, String text) {
           /* Validation code here */
                errorMessage = EuroDrawNumberChecker.checkTextInput(text, "Ball");
                if (errorMessage != "") {
                    ball5.setError(errorMessage);
                    ballArray[4] = 0;
                } else {
                    errorMessage = EuroDrawNumberChecker.checkForDuplicates(ballArray, Integer.parseInt(text), 4);
                    if (errorMessage != "") {
                        ball5.setError(errorMessage);
                        ballArray[4] = 0;
                    } else {
                        ballArray[4] = Integer.parseInt(text);
                    }
                }
            }
        });

        star1.addTextChangedListener(new TextValidator(star1) {
            @Override
            public void validate(TextView textView, String text) {
           /* Validation code here */
                errorMessage = EuroDrawNumberChecker.checkTextInput(text, "Star");
                if (errorMessage != "") {
                    star1.setError(errorMessage);
                    starArray[0] = 0;
                } else {
                    if (starArray[1] != 0) {
                        if (starArray[0] == starArray[1]) {
                            errorMessage = "You have entered a duplicate with Lucky Star 2";
                            star1.setError(errorMessage);
                            starArray[0] = 0;
                        }
                    } else {
                        starArray[0] = Integer.parseInt(text);
                    }

                }
            }
        });

        star2.addTextChangedListener(new TextValidator(star2) {
            @Override
            public void validate(TextView textView, String text) {
           /* Validation code here */
                errorMessage = EuroDrawNumberChecker.checkTextInput(text, "Star");
                if (errorMessage != "") {
                    star2.setError(errorMessage);
                    starArray[1] = 0;
                } else {
                    if (starArray[0] != 0) {
                        if (starArray[0] == Integer.parseInt(text)) {
                            errorMessage = "You have entered a duplicate with Lucky Star 1";
                            star2.setError(errorMessage);
                            starArray[1] = 0;
                        } else {
                            starArray[1] = Integer.parseInt(text);
                        }
                    }
                }
            }
        });

        btnAddEdit = (Button) rootView.findViewById(R.id.button_add_member_details);
        View.OnClickListener btnEditListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean blnFalse = false;
                if (checkBalls()) {
                    Toast.makeText(mContext, "Trying to save member data.", Toast.LENGTH_LONG).show();
                    String dateString = formattedDate();
                    SyndicateMember sm = new SyndicateMember();

                    sm.setMEMBER_NAME(mName);
                    sm.setMEMBER_CONTACT_INFO(mEmail);
                    sm.setLOTTERY_CHOICE_KEY(0);
                    sm.setBall1(String.valueOf(ballArray[0]));
                    sm.setBall2(String.valueOf(ballArray[1]));
                    sm.setBall3(String.valueOf(ballArray[2]));
                    sm.setBall4(String.valueOf(ballArray[3]));
                    sm.setBall5(String.valueOf(ballArray[4]));
                    sm.setLuckyStar1(String.valueOf(starArray[0]));
                    sm.setLuckyStar2(String.valueOf(starArray[1]));
                    sm.setEndDate("2016-12-31");
                    sm.setCHANGED_ymdhms(dateString);
                    //syndicateMemberList.add(sm);

                    //TODO Replace with insert into database call
                    SyndicateMemberDAO smDAO = new SyndicateMemberDAO(mContext);
                    long createOK = smDAO.createSyndicateMember(sm);
                    if (createOK != -1) {
                        syndicateMemberList = smDAO.getAllSyndicateMembers();
                    }
//                    db.createMember(sm);
//                    if (!FileHandlers.writeMembersFile(mContext, syndicateMemberList)) {

//                    }
                }
            }
        };
        btnAddEdit.setOnClickListener(btnEditListener);

        SyndicateMemberDAO smDAO = new SyndicateMemberDAO(mContext);

        syndicateMemberList = smDAO.getAllSyndicateMembers();

        mDrawList = syndicateMemberList;

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.draw_history_recycler_view);

        // LinearLayoutManager is used here, this will layout the elements in a similar fashion
        // to the way ListView would layout elements. The RecyclerView.LayoutManager defines how
        // elements are laid out.
        mLayoutManager = new LinearLayoutManager(getActivity());

        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        setRecyclerViewLayoutManager(mCurrentLayoutManagerType);

        mAdapter = new SyndicateMembersAdapter(syndicateMemberList, getActivity());   //(euroDrawList, getActivity());
        // Set CustomAdapter as the adapter for RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        // END_INCLUDE(initializeRecyclerView)

        return rootView;
    }

    public abstract class TextValidator implements TextWatcher {
        private final TextView textView;

        public TextValidator(TextView textView) {
            this.textView = textView;
        }

        public abstract void validate(TextView textView, String text);

        @Override
        final public void afterTextChanged(Editable s) {
            String text = textView.getText().toString();
            validate(textView, text);
        }

        @Override
        final public void beforeTextChanged(CharSequence s, int start, int count, int after) { /* Don't care */ }

        @Override
        final public void onTextChanged(CharSequence s, int start, int before, int count) { /* Don't care */ }
    }
    //

    public boolean checkBalls() {

        boolean isValid = true;

        for (int i = 0; i < (ballArray.length - 1); i++) {
            if (ballArray[i] != 0) {
                drawArray[i] = ballArray[i];
            } else {
                isValid = false;
                errorMessage = "Please enter a value between 1 and 50 inclusive";
                switch (i) {
                    case 0:
                        ball1.setError(errorMessage);
                        break;
                    case 1:
                        ball2.setError(errorMessage);
                        break;
                    case 2:
                        ball3.setError(errorMessage);
                        break;
                    case 3:
                        ball4.setError(errorMessage);
                        break;
                    case 4:
                        ball5.setError(errorMessage);
                        break;
                }
                i = ballArray.length;
            }
        }
        if (isValid) {
            if (starArray[0] != 0) {
                drawArray[5] = starArray[0];
            } else {
                errorMessage = "Please enter a value between 1 and 11 inclusive";
                isValid = false;
                star1.setError(errorMessage);
            }
        }

        if (isValid) {
            if (starArray[1] != 0) {
                drawArray[6] = starArray[1];
            } else {
                errorMessage = "Please enter a value between 1 and 11 inclusive";
                isValid = false;
                star1.setError(errorMessage);
            }
        }

        return isValid;
    }

    /**
     * Generates Strings for RecyclerView's adapter. This data would usually come
     * from a local content provider or remote server.
     */
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

    //public static String formattedDateFromString(String inputFormat, String outputFormat, String inputDate){
    public static String formattedDate(){

        Date dateNow = new Date();

        String outputFormat = "yyyy-MM-dd hh:mm:ss";

        Date parsed = null;
        String outputDate = "";

        SimpleDateFormat df_output = new SimpleDateFormat(outputFormat, java.util.Locale.getDefault());

        // You can set a different Locale, This example set a locale of Country Mexico.
        //SimpleDateFormat df_input = new SimpleDateFormat(inputFormat, new Locale("es", "MX"));
        //SimpleDateFormat df_output = new SimpleDateFormat(outputFormat, new Locale("es", "MX"));

        try {
            outputDate = df_output.format(dateNow);
        } catch (Exception e) {
            Log.e("formattedDateFromString", "Exception in formateDateFromstring(): " + e.getMessage());
        }
        return outputDate;

    }
}
