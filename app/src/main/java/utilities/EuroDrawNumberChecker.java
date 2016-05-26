package utilities;

import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.paulcurle.syndicatemanager.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Paul on 21.02/2016.
 */
public class EuroDrawNumberChecker {

    public EuroDrawNumberChecker() {}

    static public String checkForDuplicates( int[] testArray, int _value, int _index){
        String _errorMessage = "";

        for (int i = 0; i < (testArray.length - 1); i++) {
            if ((i != _index) && (testArray[i] != 0)) {
                if (testArray[i] == _value) {
                    _errorMessage = "You have entered a duplicate with Ball " + (i + 1);
                    break;
                }
            }
        }
        return _errorMessage;
    }

    static public String checkTextInput(String _inputText, String _inputName) {

        String _errorMessage = "";
        int number = 0;

        //The catch should never happen as it is declared as a number input field

        if (_inputText.isEmpty()) {
            _errorMessage = "Please enter a value";
            return _errorMessage;
        }
        switch (_inputName) {
            case "Name":
                break;
            case "Email":
                if (!isEmailValid(_inputText))
                    _errorMessage = "Please enter a valid email address";
                break;
            default:
                if (_inputText.startsWith("B",1)) {
                    return _errorMessage;
                }
                try {
                    number = Integer.parseInt(_inputText);
                    //textBox value is a number
                    if (number == 0) {
                        _errorMessage = "Please insert a number between 1 and 50 for the main balls or 1 and 11 for the lucky stars.";
                    } else if (number > 11) {
                        if (_inputName == "Star") {
                            _errorMessage = "Please insert a number in the range 1 to 11.";
                        } else if (number > 50) {
                            _errorMessage = "Please insert a number in the range 1 to 50.";
                        }
                    }
                } catch (NumberFormatException nfe) {
                    //Should never happen it's set as a numeric EditText
                    _errorMessage = "Could not parse " + nfe;
                }

                break;
        }



        return _errorMessage;
    }

    static public boolean isEmailValid(CharSequence mInputText) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(mInputText).matches();
    }

    static public boolean isNameValid(EditText mName)
    {

        Pattern ps = Pattern.compile("^[\\p{L} .'-]+$");
        Matcher ms = ps.matcher(mName.getText().toString());

        return ms.matches();
    }

    static public void clearForm(ViewGroup group)
    {
        for (int i = 0, count = group.getChildCount(); i < count; ++i) {
            View view = group.getChildAt(i);
            if  (view instanceof EditText) {
                if (view == ((EditText)view.findViewById(R.id.drawDate))) {
                    ((EditText) view).setText("");
                }
                if (view == ((EditText)view).findViewById(R.id.drawBall1)) {

                    //((EditText)view).setEnabled(true);
                    ((EditText)view).getText().clear();
//                    ((EditText)view).setText("");
//                    ((EditText)view).setHint("B1");
//                } else if (view == ((EditText)view).findViewById(R.id.drawBall2)) {
//                    ((EditText)view).setText("B2");
//                } else if (view == ((EditText)view).findViewById(R.id.drawBall3)) {
//                    ((EditText)view).setText("B3");
//                } else if (view == ((EditText)view).findViewById(R.id.drawBall4)) {
//                    ((EditText)view).setText("B4");
//                } else if (view == ((EditText)view).findViewById(R.id.drawBall5)) {
//                    ((EditText)view).setText("B5");
//                } else if (view == ((EditText)view).findViewById(R.id.drawLuckyStar1)) {
//                    ((EditText)view).setText("L1");
//                } else if (view == ((EditText)view).findViewById(R.id.drawLuckyStar2)) {
//                    ((EditText)view).setText("L2");
                }
            }

            if(view instanceof ViewGroup && (((ViewGroup)view).getChildCount() > 0))
                clearForm((ViewGroup)view);
        }
    }
}