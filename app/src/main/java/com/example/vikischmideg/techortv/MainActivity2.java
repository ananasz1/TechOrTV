package com.example.vikischmideg.techortv;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.vikischmideg.techortv.R.layout.popup;


public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {

    private CollapsingToolbarLayout collapsingToolbarLayout = null;
    private PopupWindow popupWindow;
    private LayoutInflater layoutInflater;
    private Button tryAgain;
    private Button tryAgain2;
    private Button close;
    private Boolean isFabOpen = false;
    private FloatingActionButton fab,fab1,fab2;
    private Animation fab_open,fab_close;

    Button submit;
    Button showResults;
    LinearLayout newButtons;
    CoordinatorLayout coordinatorLayout;
    RadioButton q1a1, q1a2, q1a3, q1a4, q2a1, q2a2, q2a3, q2a4, q3a1, q3a2, q3a3, q3a4;
    CheckBox q4a1, q4a2, q4a3, q4a4, q5a1, q5a2, q5a3, q5a4, q6a1, q6a2, q6a3, q6a4;
    EditText q7a, q8a;
    String wrong = "#FF0000";
    String right = "#4CAF50";
    String htc = "htc";
    String market = "android market";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        submit = (Button) findViewById(R.id.openpopup);
        newButtons = (LinearLayout) findViewById(R.id.newButtons);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.activity_main2);
        q1a1 = (RadioButton) findViewById(R.id.q1a1);
        q1a2 = (RadioButton) findViewById(R.id.q1a2);
        q1a3 = (RadioButton) findViewById(R.id.q1a3);
        q1a4 = (RadioButton) findViewById(R.id.q1a4);
        q2a1 = (RadioButton) findViewById(R.id.q2a1);
        q2a2 = (RadioButton) findViewById(R.id.q2a2);
        q2a3 = (RadioButton) findViewById(R.id.q2a3);
        q2a4 = (RadioButton) findViewById(R.id.q2a4);
        q3a1 = (RadioButton) findViewById(R.id.q3a1);
        q3a2 = (RadioButton) findViewById(R.id.q3a2);
        q3a3 = (RadioButton) findViewById(R.id.q3a3);
        q3a4 = (RadioButton) findViewById(R.id.q3a4);
        q4a1 = (CheckBox) findViewById(R.id.q4a1);
        q4a2 = (CheckBox) findViewById(R.id.q4a2);
        q4a3 = (CheckBox) findViewById(R.id.q4a3);
        q4a4 = (CheckBox) findViewById(R.id.q4a4);
        q5a1 = (CheckBox) findViewById(R.id.q5a1);
        q5a2 = (CheckBox) findViewById(R.id.q5a2);
        q5a3 = (CheckBox) findViewById(R.id.q5a3);
        q5a4 = (CheckBox) findViewById(R.id.q5a4);
        q6a1 = (CheckBox) findViewById(R.id.q6a1);
        q6a2 = (CheckBox) findViewById(R.id.q6a2);
        q6a3 = (CheckBox) findViewById(R.id.q6a3);
        q6a4 = (CheckBox) findViewById(R.id.q6a4);
        q7a = (EditText) findViewById(R.id.q7a);
        q8a = (EditText) findViewById(R.id.q8a);

        /**
         * values and settings of collapsed toolbar
         */
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(getResources().getString(R.string.quizTitle));

        dynamicToolbarColor();
        toolbarTextAppernce();

        fab = (FloatingActionButton)findViewById(R.id.fab);
        fab1 = (FloatingActionButton)findViewById(R.id.fab1);
        fab2 = (FloatingActionButton)findViewById(R.id.fab2);
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        fab.setOnClickListener(this);
        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);

        /**
         * setting the visibility of buttons
         */
        submit.setVisibility(View.VISIBLE);
        newButtons.setVisibility(View.GONE);

    }

    /**
     * called when click submit
     */
    @SuppressLint("StringFormatMatches")
    public void submit(View v) {
        //set all the correct answers, and give points
        int points = 0;

        {
            //RadioButton correct answers
            if (q1a2.isChecked()) points += 1;
            if (q2a4.isChecked()) points += 1;
            if (q3a3.isChecked()) points += 1;

            //CheckBox correct answers
            if (q4a1.isChecked() && q4a2.isChecked() && !q4a3.isChecked() && q4a4.isChecked())
                points += 1;
            if (q5a1.isChecked() && q5a2.isChecked() && q5a3.isChecked() && !q5a4.isChecked())
                points += 1;
            if (!q6a1.isChecked() && q6a2.isChecked() && q6a3.isChecked() && !q6a4.isChecked())
                points += 1;

            //EditText correct answers
            String answer7 = q7a.getText().toString().trim();
            if (answer7.equalsIgnoreCase(htc)) points += 1;
            String answer8 = q8a.getText().toString().trim();
            if (answer8.equalsIgnoreCase(market)) points += 1;
        }
        /**
         * popup window positioning and content settings
         */
        layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        ViewGroup pw = (ViewGroup) layoutInflater.inflate(popup, null);
        popupWindow = new PopupWindow(pw, CoordinatorLayout.LayoutParams.MATCH_PARENT, CoordinatorLayout.LayoutParams.MATCH_PARENT);
        popupWindow.showAtLocation(coordinatorLayout, Gravity.CENTER, 0, 0);

        //set the messages (result and different summary messages)which appears in the popup window
        TextView txtPoints = (TextView) pw.findViewById(R.id.popupText);
        txtPoints.setText(getString(R.string.summaryPoints, points));

        TextView txtSummary = (TextView) pw.findViewById(R.id.summary);
        if (points <= 2) {
            txtSummary.setText(R.string.summary1);
        } else if (points <= 4) {
            txtSummary.setText(R.string.summary2);
        } else if (points <= 6) {
            txtSummary.setText(R.string.summary3);
        } else {
            txtSummary.setText(R.string.summary4);
        }
        //start again the quiz
        tryAgain = (Button) pw.findViewById(R.id.tryAgain);
        tryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);

            }
        });
        //close popup window, show results with different colors, and change the submit button to other buttons
        showResults = (Button) pw.findViewById(R.id.showResults);
        showResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popupWindow.dismiss();

                //when show results, it go back to the top of the page
                NestedScrollView scroll_view = (NestedScrollView) findViewById(R.id.scrollView);
                scroll_view.fullScroll(ScrollView.FOCUS_UP);

                //every right answer change to green
                q1a2.setTextColor(getResources().getColor(R.color.rightAnswer));
                q2a4.setTextColor(getResources().getColor(R.color.rightAnswer));
                q3a3.setTextColor(getResources().getColor(R.color.rightAnswer));
                q4a1.setTextColor(getResources().getColor(R.color.rightAnswer));
                q4a2.setTextColor(getResources().getColor(R.color.rightAnswer));
                q4a4.setTextColor(getResources().getColor(R.color.rightAnswer));
                q5a1.setTextColor(getResources().getColor(R.color.rightAnswer));
                q5a2.setTextColor(getResources().getColor(R.color.rightAnswer));
                q5a3.setTextColor(getResources().getColor(R.color.rightAnswer));
                q6a2.setTextColor(getResources().getColor(R.color.rightAnswer));
                q6a3.setTextColor(getResources().getColor(R.color.rightAnswer));

                //set the wrong answer and non checked answer colors
                //RadioGroup1
                if (q1a1.isChecked()) {
                    q1a1.setTextColor(getResources().getColor(R.color.wrongAnswer));
                } else {
                    q1a1.setTextColor(getResources().getColor(R.color.answerNoCheck));
                }
                if (q1a3.isChecked()) {
                    q1a3.setTextColor(getResources().getColor(R.color.wrongAnswer));
                } else {
                    q1a3.setTextColor(getResources().getColor(R.color.answerNoCheck));
                }
                if (q1a4.isChecked()) {
                    q1a4.setTextColor(getResources().getColor(R.color.wrongAnswer));
                } else {
                    q1a4.setTextColor(getResources().getColor(R.color.answerNoCheck));
                }
                //RadioGroup2
                if (q2a1.isChecked()) {
                    q2a1.setTextColor(getResources().getColor(R.color.wrongAnswer));
                } else {
                    q2a1.setTextColor(getResources().getColor(R.color.answerNoCheck));
                }
                if (q2a2.isChecked()) {
                    q2a2.setTextColor(getResources().getColor(R.color.wrongAnswer));
                } else {
                    q2a2.setTextColor(getResources().getColor(R.color.answerNoCheck));
                }
                if (q2a3.isChecked()) {
                    q2a3.setTextColor(getResources().getColor(R.color.wrongAnswer));
                } else {
                    q2a3.setTextColor(getResources().getColor(R.color.answerNoCheck));
                }
                //RadioGroup3
                if (q3a1.isChecked()) {
                    q3a1.setTextColor(getResources().getColor(R.color.wrongAnswer));
                } else {
                    q3a1.setTextColor(getResources().getColor(R.color.answerNoCheck));
                }
                if (q3a2.isChecked()) {
                    q3a2.setTextColor(getResources().getColor(R.color.wrongAnswer));
                } else {
                    q3a2.setTextColor(getResources().getColor(R.color.answerNoCheck));
                }
                if (q3a4.isChecked()) {
                    q3a4.setTextColor(getResources().getColor(R.color.wrongAnswer));
                } else {
                    q3a4.setTextColor(getResources().getColor(R.color.answerNoCheck));
                }

                //CheckBox1
                if (q4a3.isChecked()) {
                    q4a3.setTextColor(getResources().getColor(R.color.wrongAnswer));
                } else {
                    q4a3.setTextColor(getResources().getColor(R.color.answerNoCheck));
                }
                //CheckBox2
                if (q5a4.isChecked()) {
                    q5a4.setTextColor(getResources().getColor(R.color.wrongAnswer));
                } else {
                    q5a4.setTextColor(getResources().getColor(R.color.answerNoCheck));
                }
                //CheckBox3
                if (q6a1.isChecked()) {
                    q6a1.setTextColor(getResources().getColor(R.color.wrongAnswer));
                } else {
                    q6a1.setTextColor(getResources().getColor(R.color.answerNoCheck));
                }
                if (q6a4.isChecked()) {
                    q6a4.setTextColor(getResources().getColor(R.color.wrongAnswer));
                } else {
                    q6a4.setTextColor(getResources().getColor(R.color.answerNoCheck));
                }
                //EditText1
                EditText answer7 = q7a;
                String correct7 = answer7.getText().toString().trim();
                if (correct7.equalsIgnoreCase(htc)) {
                    q7a.setTextColor(getResources().getColor(R.color.rightAnswer));
                } else {
                    EditText wrong7 = q7a;
                    String wrong7a = wrong7.getText().toString().trim();
                    wrong7a = getColorToText(wrong7a, wrong);
                    String right7 = getColorToText(htc, right);
                    wrong7.setText(Html.fromHtml(wrong7a + " -> " + right7));
                }
                //EditText2
                EditText answer8 = q8a;
                String correct8 = answer8.getText().toString().trim();
                if (correct8.equalsIgnoreCase(market)) {
                    q8a.setTextColor(getResources().getColor(R.color.rightAnswer));
                } else {
                    EditText wrong8 = q8a;
                    String wrong8a = wrong8.getText().toString().trim();
                    wrong8a = getColorToText(wrong8a, wrong);
                    String right8 = getColorToText(market, right);
                    wrong8.setText(Html.fromHtml(wrong8a + " -> " + right8));
                }

                //disable to click on answers when show results
                q1a1.setEnabled(false);
                q1a2.setEnabled(false);
                q1a3.setEnabled(false);
                q1a4.setEnabled(false);
                q2a1.setEnabled(false);
                q2a2.setEnabled(false);
                q2a3.setEnabled(false);
                q2a4.setEnabled(false);
                q3a1.setEnabled(false);
                q3a2.setEnabled(false);
                q3a3.setEnabled(false);
                q3a4.setEnabled(false);
                q4a1.setEnabled(false);
                q4a2.setEnabled(false);
                q4a3.setEnabled(false);
                q4a4.setEnabled(false);
                q5a1.setEnabled(false);
                q5a2.setEnabled(false);
                q5a3.setEnabled(false);
                q5a4.setEnabled(false);
                q6a1.setEnabled(false);
                q6a2.setEnabled(false);
                q6a3.setEnabled(false);
                q6a4.setEnabled(false);
                q7a.setEnabled(false);
                q8a.setEnabled(false);

                //**change buttons visibility
                submit.setVisibility(View.GONE);
                newButtons.setVisibility(View.VISIBLE);

                //start again the test, good luck toast message
                tryAgain2 = (Button) findViewById(R.id.tryAgain2);
                tryAgain2.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity2.this, R.string.toast2, Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
                //exit the app, good bye toast message
                close = (Button) findViewById(R.id.close);
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity2.this, R.string.toast3, Toast.LENGTH_SHORT).show();

                        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                        homeIntent.addCategory(Intent.CATEGORY_HOME);
                        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(homeIntent);
                    }
                });

            }

        });
    }

    //disable back button when popup window is showing (can't go back to change the answer)
    @Override
    public void onBackPressed() {
        if (popupWindow != null && popupWindow.isShowing()) {
        } else {
            super.onBackPressed();
        }
    }

    //need different colors for the EditText results if the answer is wrong
    private String getColorToText(String text, String color) {
        String input = "<font color=" + color + ">" + text + "</font>";
        return input;
    }

    //toolbar appearance
    private void dynamicToolbarColor() {

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.androidtoolbar3);
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.darka));
                collapsingToolbarLayout.setStatusBarScrimColor(getResources().getColor(R.color.darka));
            }
        });
    }

    private void toolbarTextAppernce() {
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.collapsedAppbar);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.expandedAppbar);
    }

    //when touch outside of EditText, this method hides the keyboard
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(ev);
    }
    //floating action buttons settings
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.fab:
                animateFAB();
                break;

            case R.id.fab1:
                //open BB wiki in browser
                String url = getResources().getString(R.string.webbreakingbad);
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                break;

            case R.id.fab2:
                //open android wiki in browser
                url = getResources().getString(R.string.webandroid);
                i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                break;
        }
    }
    //FAB animation settings
    public void animateFAB(){

        if(isFabOpen){

            fab1.startAnimation(fab_close);
            fab2.startAnimation(fab_close);
            fab1.setClickable(false);
            fab2.setClickable(false);
            isFabOpen = false;

        } else {

            fab1.startAnimation(fab_open);
            fab2.startAnimation(fab_open);
            fab1.setClickable(true);
            fab2.setClickable(true);
            isFabOpen = true;
        }
    }
}