package example.seniordesign_project;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HelpActivity extends AppCompatActivity {

    TextView inform_text;
    Typeface tf1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        inform_text = (TextView) findViewById(R.id.information_text);

        tf1 = Typeface.createFromAsset(getAssets(), "fonts/BalooBhai-Regular.ttf");
        inform_text.setTextSize(18);
        inform_text.setTypeface(tf1);
    }

    @Override
    public void onBackPressed()
    {
        Intent intent=new Intent(HelpActivity.this,MainActivity.class);
        startActivity(intent);
        finish();

    }
}
