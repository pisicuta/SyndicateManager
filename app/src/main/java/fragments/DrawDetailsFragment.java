package fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import adapters.SyndicateMembersAdapter;
//import models.AllMembers;
//import database.DatabaseHelper;
//import database.SyndicateMemberDataSource;
import database.DbHelper;
import database.EuroDrawDAO;
import database.SyndicateMemberDAO;
import models.EuroDraw;
//import models.EuroDrawHistory;
import models.SyndicateMember;
import utilities.EuroDrawNumberChecker;
//import utilities.FileHandlers;

import static utilities.EuroDrawNumberChecker.clearForm;

/**
 * A simple {@link Fragment} subclass.
 * The code for this and the follow on editing fragment is done already.
 * Need to load the members into a class and list; see ValOkafor App 1 part 3?
 * Need to write the class out to a file if changed
 */
public class DrawDetailsFragment extends Fragment {

    private static final String TAG = "SyndicateMembersFragment";
    private static final int DATASET_ROW_COUNT = 2;
    private static final int DATASET_COL_COUNT = 7;
    private RecyclerView mRecyclerView;
    //
    //Screen input fields fields
    private ViewGroup group;
    private EditText mDateEntryField;
    private EditText mball1;
    private EditText mball2;
    private EditText mball3;
    private EditText mball4;
    private EditText mball5;
    private EditText mstar1;
    private EditText mstar2;
    private int[] drawArray = new int[7];
    private int[] ballArray = new int[5];
    private int[] starArray = {0,0};

    //Buttons
    private Button btnCheckSave;
    //Variables
    private String errorMessage = "";

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    protected LayoutManagerType mCurrentLayoutManagerType;
    protected SyndicateMembersAdapter mAdapter;

    private RecyclerView.LayoutManager mLayoutManager;
    private boolean blnSuccess;
    private ArrayList<SyndicateMember> syndicateMemberList;
    private ArrayList<SyndicateMember> mDrawList;
    private ArrayList<EuroDraw> historyList;

    private Context mContext;
    private DbHelper db;

    public DrawDetailsFragment() {
        // Required empty public constructor
    }



    /**
     * Returns a new instance of this fragment for the given section number.
     */

    public static DrawDetailsFragment newInstance() {

        DrawDetailsFragment fragment = new DrawDetailsFragment();

        return fragment;

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mContext = getContext();
        //TODO Replace with call to dbhelper getallmembers db selection
        Log.i("Reading: ", "Reading all members..");
//        db = new DatabaseHelper(null);
        db = DbHelper.getInstance(mContext);
        SyndicateMemberDAO smDAO = new SyndicateMemberDAO(mContext);
        EuroDrawDAO edDAO = new EuroDrawDAO(mContext);

        syndicateMemberList = smDAO.getAllSyndicateMembers();

        historyList = edDAO.getAllEuroDraws();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        final ViewGroup mContainer = container;
        View rootView = inflater.inflate(R.layout.fragment_draw_details, container, false);
        //
        //Screen entry fields for any draw
        mDateEntryField = (EditText) rootView.findViewById(R.id.drawDate);
        //Date field validation
        mDateEntryField.addTextChangedListener(mDateEntryWatcher);
        //
        mball1 = (EditText) rootView.findViewById(R.id.drawBall1);
        mball2 = (EditText) rootView.findViewById(R.id.drawBall2);
        mball3 = (EditText) rootView.findViewById(R.id.drawBall3);
        mball4 = (EditText) rootView.findViewById(R.id.drawBall4);
        mball5 = (EditText) rootView.findViewById(R.id.drawBall5);
        mstar1 = (EditText) rootView.findViewById(R.id.drawLuckyStar1);
        mstar2 = (EditText) rootView.findViewById(R.id.drawLuckyStar2);
        //
        //Drawn ball entry validation
        mball1.addTextChangedListener(new TextValidator(mball1) {
            @Override
            public void validate(TextView textView, String text) {
           /* Validation code here */
                errorMessage = EuroDrawNumberChecker.checkTextInput(text, "mball");
                if (errorMessage != "") {
                    mball1.setError(errorMessage);
                    ballArray[0] = 0;
                }
                else {
                    errorMessage = EuroDrawNumberChecker.checkForDuplicates(ballArray, Integer.parseInt(text), 0);
//                    errorMessage = checkForDuplicates(ballArray, Integer.parseInt(text), 0);
                    if (errorMessage != "") {
                        mball1.setError(errorMessage);
                        ballArray[0] = 0;
                    } else {
                        ballArray[0] = Integer.parseInt(text);
                    }
                }
            }
        });

        mball2.addTextChangedListener(new TextValidator(mball2) {
            @Override
            public void validate(TextView textView, String text) {
           /* Validation code here */
                errorMessage = EuroDrawNumberChecker.checkTextInput(text, "mball");
                if (errorMessage != "") {
                    mball2.setError(errorMessage);
                    ballArray[1] = 0;
                } else {
                    errorMessage = EuroDrawNumberChecker.checkForDuplicates(ballArray, Integer.parseInt(text), 1);
                    if (errorMessage != "") {
                        mball2.setError(errorMessage);
                        ballArray[1] = 0;
                    } else {
                        ballArray[1] = Integer.parseInt(text);
                    }
                }
            }
        });

        mball3.addTextChangedListener(new TextValidator(mball3) {
            @Override
            public void validate(TextView textView, String text) {
           /* Validation code here */
                errorMessage = EuroDrawNumberChecker.checkTextInput(text, "mball");
                if (errorMessage != "") {
                    mball3.setError(errorMessage);
                    ballArray[2] = 0;
                } else {
                    errorMessage = EuroDrawNumberChecker.checkForDuplicates(ballArray, Integer.parseInt(text), 2);
                    if (errorMessage != "") {
                        mball3.setError(errorMessage);
                        ballArray[2] = 0;
                    } else {
                        ballArray[2] = Integer.parseInt(text);
                    }
                }
            }
        });

        mball4.addTextChangedListener(new TextValidator(mball4) {
            @Override
            public void validate(TextView textView, String text) {
           /* Validation code here */
                errorMessage = EuroDrawNumberChecker.checkTextInput(text, "mball");
                if (errorMessage != "") {
                    mball4.setError(errorMessage);
                    ballArray[3] = 0;
                } else {
                    errorMessage = EuroDrawNumberChecker.checkForDuplicates(ballArray, Integer.parseInt(text), 3);
                    if (errorMessage != "") {
                        mball4.setError(errorMessage);
                        ballArray[3] = 0;
                    } else {
                        ballArray[3] = Integer.parseInt(text);
                    }
                }
            }
        });

        mball5.addTextChangedListener(new TextValidator(mball5) {
            @Override
            public void validate(TextView textView, String text) {
           /* Validation code here */
                errorMessage = EuroDrawNumberChecker.checkTextInput(text, "mball");
                if (errorMessage != "") {
                    mball5.setError(errorMessage);
                    ballArray[4] = 0;
                } else {
                    errorMessage = EuroDrawNumberChecker.checkForDuplicates(ballArray, Integer.parseInt(text), 4);
                    if (errorMessage != "") {
                        mball5.setError(errorMessage);
                        ballArray[4] = 0;
                    } else {
                        ballArray[4] = Integer.parseInt(text);
                    }
                }
            }
        });

        mstar1.addTextChangedListener(new TextValidator(mstar1) {
            @Override
            public void validate(TextView textView, String text) {
           /* Validation code here */
                errorMessage = EuroDrawNumberChecker.checkTextInput(text, "Star");
                if (errorMessage != "") {
                    mstar1.setError(errorMessage);
                    starArray[0] = 0;
                } else {
                    if (starArray[1] != 0) {
                        if (starArray[0] == starArray[1]) {
                            errorMessage = "You have entered a duplicate with Lucky Star 2";
                            mstar1.setError(errorMessage);
                            starArray[0] = 0;
                        }
                    } else {
                        starArray[0] = Integer.parseInt(text);
                    }

                }
            }
        });

        mstar2.addTextChangedListener(new TextValidator(mstar2) {
            @Override
            public void validate(TextView textView, String text) {
           /* Validation code here */
                errorMessage = EuroDrawNumberChecker.checkTextInput(text, "Star");
                if (errorMessage != "") {
                    mstar2.setError(errorMessage);
                    starArray[1] = 0;
                } else {
                    if (starArray[0] != 0) {
                        if (starArray[0] == Integer.parseInt(text)) {
                            errorMessage = "You have entered a duplicate with Lucky Star 1";
                            mstar2.setError(errorMessage);
                            starArray[1] = 0;
                        } else {
                            starArray[1] = Integer.parseInt(text);
                        }
                    }
                }
            }
        });

        //
        btnCheckSave = (Button) rootView.findViewById(R.id.button_add_draw_details);
        View.OnClickListener btnCheckListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!ValidateFinalEntry(mDateEntryField.getText().toString())) {
                    errorMessage = "Enter a valid date: DD/MM/YYYY. Must be a Tuesday or Friday in current year";
                    mDateEntryField.setError(errorMessage);
                } else if (checkmballs()) {
                    int winningRows = checkMemberRows();
                    if (mball1.isEnabled()) {
                        String inDate = mDateEntryField.getText().toString();
                        EuroDraw ed = new EuroDraw();
                        ed.setDrawDate(inDate);
                        ed.setNumberOfWinningRows(winningRows);
                        ed.setBall1(String.valueOf(ballArray[0]));
                        ed.setBall2(String.valueOf(ballArray[1]));
                        ed.setBall3(String.valueOf(ballArray[2]));
                        ed.setBall4(String.valueOf(ballArray[3]));
                        ed.setBall5(String.valueOf(ballArray[4]));
                        ed.setLuckyStar1(String.valueOf(starArray[0]));
                        ed.setLuckyStar2(String.valueOf(starArray[1]));
                        historyList.add(ed);
                        //
                        //enableTextFields(false);
                        //
                        //TODO Replace with insert into database call
//                        if (!FileHandlers.writeDrawFile(mContext, historyList)) {
//                            Toast.makeText(mContext, "Error saving draw data." , Toast.LENGTH_LONG).show();
//                        }

                    }
                    //Wait some time
                    // Execute some code after 5 seconds have passed
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            clearForm(mContainer);
                        }
                    }, 5000);
                }
            }
        };

        btnCheckSave.setOnClickListener(btnCheckListener);
//
        rootView.setTag(TAG);

        mDrawList = syndicateMemberList;

        // BEGIN_INCLUDE(initializeRecyclerView)
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.allmembers_recycler_view);

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

    private TextWatcher mDateEntryWatcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String working = s.toString();
            String errorMessage = "Enter a valid date: DD/MM/YYYY. Must be a Tuesday or Friday in current year";

            boolean isValid = true;
            boolean leapYear = false;

            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
            if (((currentYear % 4) == 0 ) || ((currentYear % 400) == 0 )) {
                leapYear = true;
            }

            if (working.length()==2 && before ==0) {
                if (Integer.parseInt(working) < 1 || Integer.parseInt(working)>31) {
                    isValid = false;
                } else {
                    working+="/";
                    mDateEntryField.setText(working);
                    mDateEntryField.setSelection(working.length());
                }
            }
            else if (working.length()==5 && before ==0) {
                String enteredMonth = working.substring(3);
                String day = working.substring(0,2);
                int inputMonth = Integer.parseInt(enteredMonth);
                if (inputMonth > (currentMonth + 1)) {
                    errorMessage = "Draw date cannot be in the future";
                    isValid = false;
                }
                if ((inputMonth < 1) || (inputMonth > 12)) {
                    isValid = false;
                } else if (inputMonth == 2) {
                    if ((!leapYear) && (Integer.parseInt(day) > 28)) {
                        errorMessage = "Too many days for February. Only 28 days in this month";
                        isValid = false;
                    } else if ((leapYear) && (Integer.parseInt(day) > 29)) {
                        errorMessage = "Too many days for February. Only 29 days in this month";
                        isValid = false;
                    }
                } else if ((inputMonth == 4) || (inputMonth == 6) || (inputMonth == 9) || (inputMonth == 11)) {
                    if (Integer.parseInt(day) > 30) {
                        errorMessage = "Too many days. Only 30 days in this month";
                        isValid = false;
                    }
                }

                if (isValid) {
                    working += "/" + currentYear;
                    mDateEntryField.setText(working);
                    mDateEntryField.setSelection(working.length());

//                    isValid = ValidateFinalEntry(working);

                }
            }

            if (!isValid) {
                mDateEntryField.setError(errorMessage);
            } else {
                mDateEntryField.setError(null);
                //
                //Check if date is in the file/array already
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                Date dateIn = new Date(System.currentTimeMillis());
                try {
                    dateIn =  formatter.parse(mDateEntryField.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                //TODO Replace with call to dbHelper getall draws
//                ArrayList<EuroDraw> euroDrawList = db.getAllDraws(1);
                //ArrayList<EuroDraw> euroDrawList = EuroDrawHistory.get(getActivity()).geteuroDrawHistory();

//                for (int x = 0; x < euroDrawList.size(); x++) {
//                    Date dateObj = new Date(System.currentTimeMillis());
//
//                    try {
//                        dateObj = formatter.parse(euroDrawList.get(x).getEdDrawDate().toString());
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                    }
//                    int result = dateIn.compareTo(dateObj);
//                    if (result == 0) {
//                        //output the previously drawn numbers
//                        mball1.setText(euroDrawList.get(x).getBall1());
//                        mball2.setText(euroDrawList.get(x).getBall2());
//                        mball3.setText(euroDrawList.get(x).getBall3());
//                        mball4.setText(euroDrawList.get(x).getBall4());
//                        mball5.setText(euroDrawList.get(x).getBall5());
//                        mstar1.setText(euroDrawList.get(x).getLuckyStar1());
//                        mstar2.setText(euroDrawList.get(x).getLuckyStar2());
//                        //
//                        enableTextFields(false);
//                        //Stop the loop
//                        x = euroDrawList.size();
//                    }
//                }
            }

        }

        @Override
        public void afterTextChanged(Editable s) {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    };

    public void enableTextFields(boolean _state) {
        mball1.setEnabled(_state);
        mball2.setEnabled(_state);
        mball3.setEnabled(_state);
        mball4.setEnabled(_state);
        mball5.setEnabled(_state);
        mstar1.setEnabled(_state);
        mstar2.setEnabled(_state);
    }

    public boolean ValidateFinalEntry(String inputDate) {

        boolean blnValid = true;

        if (inputDate.equals("")) {
            blnValid = false;
        } else {
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            int enteredYear = Integer.parseInt(inputDate.substring(6));
            int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
            int enteredMonth = Integer.parseInt(inputDate.substring(3, 4));

            if (enteredMonth > (currentMonth + 1)) {
                blnValid = false;
            }
            if (currentYear != enteredYear) {
                blnValid = false;
            }
        }

        if (blnValid) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

            try {
                Date dateObj = formatter.parse(mDateEntryField.getText().toString());
                Calendar c = Calendar.getInstance();
                c.setTime(dateObj);
                int dow = c.get(Calendar.DAY_OF_WEEK);
                if ((dow != 3) && dow != 6) {
                    blnValid = false;
                }

            } catch (ParseException e) {
                e.printStackTrace();
                blnValid = false;
            }
        }

        return blnValid;
    }

    public boolean checkmballs() {

        boolean isValid = true;

        for (int i = 0; i < (ballArray.length - 1); i++) {
            if (ballArray[i] != 0) {
                drawArray[i] = ballArray[i];
            } else {
                isValid = false;
                errorMessage = "Please enter a value between 1 and 50 inclusive";
                switch (i) {
                    case 0:
                        mball1.setError(errorMessage);
                        break;
                    case 1:
                        mball2.setError(errorMessage);
                        break;
                    case 2:
                        mball3.setError(errorMessage);
                        break;
                    case 3:
                        mball4.setError(errorMessage);
                        break;
                    case 4:
                        mball5.setError(errorMessage);
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
                mstar1.setError(errorMessage);
            }
        }

        if (isValid) {
            if (starArray[1] != 0) {
                drawArray[6] = starArray[1];
            } else {
                errorMessage = "Please enter a value between 1 and 11 inclusive";
                isValid = false;
                mstar2.setError(errorMessage);
            }
        }

        return isValid;
    }

    public int checkMemberRows() {

        int winningballs = 0, winningStars = 0, winners = 0;
        StringBuilder sb = new StringBuilder();
        SyndicateMember mSyndicateMember;

        //Loop through the drawn numbers array - drawArray and compare each number with the neumbers in the syndicate memeers array copied to mDrawList

        for (int x = 0; x < mDrawList.size(); x++)
        {
            winningballs = 0;
            winningStars = 0;
            mSyndicateMember = mDrawList.get(x);
            for (int y = 0; y < ballArray.length; y++)
            {
                if (Integer.parseInt(mSyndicateMember.getBall1().toString()) == ballArray[y]) {
                    winningballs++;
                } else if (Integer.parseInt(mSyndicateMember.getBall2().toString()) == ballArray[y]) {
                    winningballs++;
                } else if (Integer.parseInt(mSyndicateMember.getBall3().toString()) == ballArray[y]) {
                    winningballs++;
                } else if (Integer.parseInt(mSyndicateMember.getBall4().toString()) == ballArray[y]) {
                    winningballs++;
                } else if (Integer.parseInt(mSyndicateMember.getBall5().toString()) == ballArray[y]) {
                    winningballs++;
                }
            }
            for (int z = 0; z <starArray.length; z++) {
                if (Integer.parseInt(mSyndicateMember.getLuckyStar1().toString()) == starArray[z]) {
                    winningStars++;
                } else if (Integer.parseInt(mSyndicateMember.getLuckyStar2().toString()) ==  starArray[z]) {
                    winningStars++;
                }
            }
            //
            if ((winningballs >= 2) || (winningStars == 2) && (winningballs >= 1)) {
                sb.append("\n" + mSyndicateMember.getMEMBER_NAME());
                sb.append(" with " + String.valueOf(winningballs));
                sb.append(" mballs and ");
                sb.append(" with " + String.valueOf(winningStars));
                sb.append(" lucky stars.");
                winners++;
            }
        }
//
        StringBuilder sb1 = new StringBuilder();

        switch (winners) {
            case 0:
                sb1.append("\n There are no winners for this draw");
                break;
            case 1:
                sb1.append("There is 1 winner in this draw");
                sb1.append("\n");
                sb1.append(sb.toString());
                break;
            default:
                sb1.append("There are "  );
                sb1.append(String.valueOf(winners) + " winners in this draw");
                for (int i = 1; i <= winners; i++) {
                    sb1.append("\n");
                    sb1.append(sb.toString());
                }
                break;
        }


        Toast.makeText(mContext, sb1.toString() , Toast.LENGTH_LONG).show();

        return winners;
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
}
