package application.greyhats.calculat_o_meter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText maxEditTextNumber;
    String max;
    public void PlayGame (View view){
        max = maxEditTextNumber.getText().toString();
        if ( max.length() > 0) {
            Intent myIntent = new Intent(this, Game.class);
            myIntent.putExtra("maximum", max);
            startActivity(myIntent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        maxEditTextNumber = findViewById(R.id.maxEditTextNumber);
    }
}