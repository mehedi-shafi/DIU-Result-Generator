package shafi.mehedi.diuresultgenerator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class AdvancedActivity extends AppCompatActivity {

    LinearLayout specificSemester;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced);

        specificSemester = (LinearLayout) findViewById(R.id.specificSemester_result);

        specificSemester.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdvancedActivity.this, SpeicificSemester.class));
            }
        });


    }
}
