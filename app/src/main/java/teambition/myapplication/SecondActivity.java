package teambition.myapplication;

import android.app.Activity;
import android.app.NotificationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class SecondActivity extends Activity {
    TextView textView;
    Button button1,button2;
    String nota;
    int num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        final NotificationManager notifiMgr = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        textView= (TextView) findViewById(R.id.textview);
        button1= (Button) findViewById(R.id.Button_Yes);
        button2= (Button) findViewById(R.id.Button_No);
        nota=getIntent().getStringExtra("nota");
        num =getIntent().getIntExtra("num",-1);
        textView.setText("Did you "+nota+ " yet?");
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SecondActivity.this,"awesome",Toast.LENGTH_LONG).show();
                notifiMgr.cancel(num);
                finish();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SecondActivity.this,"Make sure you "+nota+" soon!",Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }



}
