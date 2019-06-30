package com.example.fitnesstimer.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;

import com.example.fitnesstimer.R;
import com.example.fitnesstimer.activity.DetailTimerActivity;
import com.example.fitnesstimer.activity.MainActivity;
import com.example.fitnesstimer.common.Common;
import com.example.fitnesstimer.model.DetailTimer;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class ServiceTimer extends Service {
    private static final String ACTION_PLAY = "actionplay";
    private static final String ACTION_PAUSE = "actionpause";
    private Random random = new Random();
    public Timer myTimer = new Timer();
    public Context context;
    private NotificationCompat.Builder builder;
    public Notification n;
    public PendingIntent contentIntent;
    private int i = 0;
    public Intent notificationIntent;
    private DetailTimer detailTimer;
    private String sTime, sPreparation, sJob, sRest, sRestSet, sRestAll;
    private RemoteViews remoteViews;
    private NotificationManager nm;
    private ArrayList<String> nameStep = new ArrayList<>();
    private ArrayList<Integer> namberStep = new ArrayList<>();
    private ArrayList<Integer> newStep = new ArrayList<>();
    private Timer timer;
    private TimerTask tTask;
    private String actionPP;


    public ServiceTimer() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d("ServiceTimer", "onCreate");

        RemoteViews notificationLayout = new RemoteViews(getPackageName(), R.layout.notification);

        Intent notificationIntent = new Intent(this, MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(android.R.drawable.stat_notify_chat)
                .setShowWhen(false)
                .setContentTitle("FITNESS TIMER")
                .setContentText("who has a birthday?...")
                .setContentIntent(pendingIntent).build();
        startForeground(1037, notification);
        init();
        actionPP = ACTION_PAUSE;

        if (timer != null) timer.cancel();
        timer = new Timer();

        context = getApplicationContext();
        notificationIntent = new Intent(context, MainActivity.class);
        contentIntent = PendingIntent.getActivity(context,
                0, notificationIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);
        builder = new NotificationCompat.Builder(context);

        remoteViews = new RemoteViews(getPackageName(), R.layout.notification);
        remoteViews.setTextViewText(R.id.tvName, "Custom notification text");

        remoteViews.setOnClickPendingIntent(R.id.root, contentIntent);

        //remoteViews.setViewVisibility(R.id.tvNamPreparation,View.GONE);



    }

    private void schedule(final int id, final Intent intent) {
        if (tTask != null) tTask.cancel();

        tTask = new TimerTask() {
            public void run() {
                //Log.d("ServiceTimer", "id " + id);
                runTimer(intent);
            }
        };
        timer.schedule(tTask, 0, 1000);

    }

    private void runTimer(Intent intent) {
        i++;

       // if (newStep!=null) {
            for (int j = 0; j <= newStep.size() - 1; j++) {
                 //Log.d("ServiceTimer", "newStep.get(j) " + newStep.get(j) +" i "+i+" newStep.get(j) - namberStep.get(j) "+(newStep.get(j) - namberStep.get(j)));
                if ((newStep.get(j) >= i) & ((newStep.get(j) - namberStep.get(j)) < i)) {
                    // Log.d("ServiceTimer", "nameStep.get(j) " + nameStep.get(j));
                    switch (nameStep.get(j)) {
                        case "preparation":
                            sPreparation = timeToString(i, newStep.get(j));
                            remoteViews.setTextViewText(R.id.tvNamPreparation, sPreparation);
                            break;
                        case "job":
                            sJob = timeToString(i, newStep.get(j));
                            remoteViews.setTextViewText(R.id.tvNamJob, sJob);
                            break;
                        case "rest":
                            sRest = timeToString(i, newStep.get(j));
                            remoteViews.setTextViewText(R.id.tvNamRest, sRest);
                            break;
                        case "restset":
                            sRestSet = timeToString(i, newStep.get(j));
                            remoteViews.setTextViewText(R.id.tvNamRestSet, sRestSet);
                            break;
                        case "restall":
                            sRestAll = timeToString(i, newStep.get(j));
                            remoteViews.setTextViewText(R.id.tvNamRestAll, sRestAll);
                            if (i == newStep.get(j)) {
                                i = 0;
                            }
                            break;
                        default:

                            break;
                    }
                }
            }
       // }
        remoteViews.setTextViewText(R.id.tvName, detailTimer.getName());

        Intent playIntent = new Intent(this, ServiceTimer.class);
        playIntent.setAction(actionPP);
        PendingIntent pplayIntent = PendingIntent.getService(this, 0,
                playIntent, 0);
        remoteViews.setOnClickPendingIntent(R.id.btnPlayPause, pplayIntent);

        if (intent.getAction() == actionPP) {
            Log.d("ServiceTimer", "test ");
            i=0;
            Log.d("ServiceTimer", "actionPP.equals(ACTION_PAUSE) "+actionPP.equals(ACTION_PAUSE));
            if(actionPP.equals(ACTION_PAUSE)){
                actionPP=ACTION_PLAY;
                remoteViews.setImageViewResource(R.id.imBtn,R.drawable.ic_play_circle_filled_black_24dp);
            }else{
                actionPP=ACTION_PAUSE;
                remoteViews.setImageViewResource(R.id.imBtn,R.drawable.ic_pause_circle_outline_black_24dp);
            }
            Log.d("ServiceTimer", "actionPP "+actionPP);
        }

        nm = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);

        builder.setContentIntent(contentIntent)
                .setSmallIcon(R.drawable.ic_add_white_24dp)
                .setCustomContentView(remoteViews)
                .setStyle(new NotificationCompat.DecoratedCustomViewStyle())
                .setAutoCancel(true)
        .setOngoing(true);


        n = builder.build();

        n.defaults = Notification.DEFAULT_SOUND |
                Notification.DEFAULT_VIBRATE;

        nm.notify(1, n);

    }

    private void init() {
        detailTimer = new DetailTimer();
        detailTimer.setName("Бег");
        detailTimer.setPreparation("10");
        detailTimer.setJob("20");
        detailTimer.setRest("5");
        detailTimer.setRepeat("1");
        detailTimer.setSet("1");
        detailTimer.setRestSet("1");
        detailTimer.setRestAll("2");
        detailTimer.setTime("37");


    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("ServiceTimer", "onBind");
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");

    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("ServiceTimer", "onStartCommand flags " + flags + " startId " + startId);
        boolean start = true;
        boolean stop = false;

/*
        context = getApplicationContext();
        notificationIntent = new Intent(context, MainActivity.class);
        contentIntent = PendingIntent.getActivity(context,
                0, notificationIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);
        builder = new NotificationCompat.Builder(context);

        remoteViews = new RemoteViews(getPackageName(), R.layout.notification);
        remoteViews.setTextViewText(R.id.tvName, "Custom notification text");

        remoteViews.setOnClickPendingIntent(R.id.root, contentIntent);

        //remoteViews.setViewVisibility(R.id.tvNamPreparation,View.GONE);

        Intent playIntent = new Intent(this, ServiceTimer.class);
        playIntent.setAction(ACTION_PLAY_PAUSE);
        PendingIntent pplayIntent = PendingIntent.getService(this, 0,
                playIntent, 0);
        remoteViews.setOnClickPendingIntent(R.id.btnPlayPause, pplayIntent);
*/
        getIntent(intent);




        schedule(startId,intent);
        return Service.START_STICKY_COMPATIBILITY;
    }

    private void getIntent(Intent intent) {
        String s=intent.getStringExtra(Common.KEY);
        //Log.d("ServiceTimer", "intent.getStringExtra(Common.KEY).isEmpty() " + TextUtils.isEmpty(s));
        if ((intent != null)&(!TextUtils.isEmpty(s))) {
            if (intent.getStringExtra(Common.KEY).equals(Common.KEY)) {
                nameStep = new ArrayList<>();
                namberStep = new ArrayList<>();
                newStep = new ArrayList<>();
                nameStep = intent.getStringArrayListExtra(Common.ARRAY_NAME_STEP);
                namberStep = intent.getIntegerArrayListExtra(Common.ARRAY_NAMBER_STEP);
                newStep = intent.getIntegerArrayListExtra(Common.ARRAY_NEW_STEP);
                //Log.d("ServiceTimer", "nameStep " + nameStep);
                // Log.d("ServiceTimer", "namberStep " + namberStep);
                //Log.d("ServiceTimer", "newStep " + newStep);
                // Log.d("ServiceTimer", "test " + intent.getIntExtra("test",0));}
            }
        }
    }

    private String timeToString(int ms, int getMs) {
        int min = 0;
        int sec = 0;
        //if (ms <= getMs) {
        min = Math.abs(getMs - ms) / 60;
        sec = Math.abs(getMs - ms) - (min * 60);
        //}
        return String.format("%02d:%02d", min, sec);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (myTimer != null) myTimer.cancel();
        Log.d("ServiceTimer", "onDestroy");
    }
}
