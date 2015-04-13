package idv.leo.hk5_1;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    private final static int NOTIFICATION_ID = 100;
    private EditText editText;
    private TextView tvResult;
    private Button btn1, btn2;
    private NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);


        findViews();
    }




    private void findViews() {

        editText = (EditText) findViewById(R.id.editText);
        tvResult = (TextView) findViewById(R.id.tvResult);
        btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CountDownTimer(3000,1000){

                    @Override
                    public void onFinish() {
                        tvResult.setText(editText.getText().toString());
                    }

                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                }.start();



                String title = getString(R.string.title);
                String content = getString(R.string.content);
                Intent intent = new Intent(MainActivity.this, NotiActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("title", title);
                bundle.putString("content", content);
                intent.putExtras(bundle);

                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this
                        , 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

                Notification notification = new Notification.Builder(MainActivity.this)

                        .setTicker(getString(R.string.ticker))

                        .setContentTitle(title)

                        .setContentText(content)

                        .setSmallIcon(R.drawable.ic_secret_notification)

                        .setAutoCancel(true)

                        .setContentIntent(pendingIntent)

                        .setSound(soundUri)

                        .addAction(R.drawable.ic_secret_notification, "View", pendingIntent)
                        .addAction(0, "Remind", pendingIntent)
                        .build();

                notificationManager.notify(NOTIFICATION_ID, notification);


            }
        });

        btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editText.setText("");
                tvResult.setText(editText.getText().toString());
                notificationManager.cancel(NOTIFICATION_ID);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
