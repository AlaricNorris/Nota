package teambition.myapplication;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;


public class MainActivity extends Activity {
    ImageView red, green, black;
    Button button;
    EditText editText;
    boolean isExpand;
    Random random;
    int num;
    final static int BLACK=0;
    final static int RED =1;
    final static int GREEN =-1;
    int defaultLevel = BLACK; //默认为0，黑色
    String nota;
    NotificationManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        setContentView(R.layout.activity_main);
        InitViews();
        SetListeners();
    }

    private void SetListeners() {
        black.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isExpand) {
                    green.setVisibility(View.VISIBLE);
                    red.setVisibility(View.VISIBLE);
                    isExpand = true;
                } else {
                    green.setVisibility(View.INVISIBLE);
                    red.setVisibility(View.INVISIBLE);
                    isExpand = false;
                }
            }
        });

        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                green.setVisibility(View.INVISIBLE);
                red.setVisibility(View.INVISIBLE);
                isExpand = false;
                black.setImageResource(R.mipmap.red);
                defaultLevel=RED;
            }
        });

        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                green.setVisibility(View.INVISIBLE);
                red.setVisibility(View.INVISIBLE);
                isExpand = false;
                black.setImageResource(R.mipmap.green);
                defaultLevel=GREEN;
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nota=editText.getText().toString();
                if (!nota.equals("")) {
                    Intent i = new Intent(MainActivity.this, SecondActivity.class);
                    i.putExtra("nota", nota);
                    i.putExtra("num",num);
                    PendingIntent pendingIntent2 = PendingIntent.getActivity(MainActivity.this, num,
                            i, PendingIntent.FLAG_CANCEL_CURRENT);
                    Notification notify2 = new Notification.Builder(MainActivity.this).setLargeIcon(getBitmaps()).setSmallIcon(getSmallIcon()).setTicker("Remember to "+nota).setContentTitle(nota).setContentIntent(pendingIntent2).getNotification();
                    notify2.flags = Notification.FLAG_ONGOING_EVENT;
                    finish();
                    manager.notify(num, notify2);
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a message!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private int getSmallIcon() {
        int drawable =0;
        switch (defaultLevel){
            case GREEN:drawable=R.drawable.green;break;
            case RED:drawable=R.drawable.red;break;
            case BLACK:drawable=R.drawable.black;break;
        }
        return drawable;
    }

    private Bitmap getBitmaps() {
        Bitmap bitmap=null;
        switch (defaultLevel){
            case GREEN:bitmap=BitmapFactory.decodeResource(getResources(),R.mipmap.green);break;
            case RED:bitmap=BitmapFactory.decodeResource(getResources(),R.mipmap.red);break;
            case BLACK:bitmap=BitmapFactory.decodeResource(getResources(),R.mipmap.black);break;
        }
        return bitmap;
    }

    private void InitViews() {
        button = (Button) findViewById(R.id.Button);
        red = (ImageView) findViewById(R.id.TopImage);
        black = (ImageView) findViewById(R.id.MidImage);
        green = (ImageView) findViewById(R.id.DownImage);
        editText = (EditText) findViewById(R.id.editText);
        random = new Random();
        num= random.nextInt(1123);
    }

}
