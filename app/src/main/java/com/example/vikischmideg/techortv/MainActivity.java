package com.example.vikischmideg.techortv;


        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //make toast message and navigate to the quiz
    public void goToQuiz (View view){
        Toast.makeText(this, R.string.toast1, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        startActivity(intent);
    }

    public void goToBB (View view){
        Toast.makeText(this, R.string.toast1, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(MainActivity.this, MainActivitybb.class);
        startActivity(intent);
    }

    public void onBackPressed(){
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory(Intent.CATEGORY_HOME);
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
    }
}



