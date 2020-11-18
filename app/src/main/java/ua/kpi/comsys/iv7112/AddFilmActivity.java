package ua.kpi.comsys.iv7112;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddFilmActivity extends AppCompatActivity {

    public void onClickAdd(View view){
        Intent intent = new Intent(this, MainActivity.class);

        EditText inputTitle = (EditText)findViewById(R.id.inputTitle);
        EditText inputYear = (EditText)findViewById(R.id.inputYear);
        EditText inputType = (EditText)findViewById(R.id.inputType);

        if (inputYear.getText().toString().matches("[0-9]")){
            intent.putExtra("title", inputTitle.getText().toString());
            intent.putExtra("year", inputYear.getText().toString());
            intent.putExtra("type", inputType.getText().toString());
            startActivity(intent);
            finish();
        }else {
            CharSequence message = "Wrong data";
            Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_film);
    }
}