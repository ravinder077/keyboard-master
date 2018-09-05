package fontconverter.com.cpkamboj.pupboard;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Feedback extends AppCompatActivity {
    private Button fed;
    @Override
    protected void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.feedback);

        fed = findViewById(R.id.fed_btn);
        fed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Feedback.this, "Thanks For your Feedback.", Toast.LENGTH_SHORT).show();
                Handler handler = new Handler();
                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    }
                };
                handler.postDelayed(r,1000);
            }
        });

    }
}
