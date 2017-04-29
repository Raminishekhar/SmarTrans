package com.pubnub.example.android.datastream.mapexample.smartrans;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.os.ResultReceiver;
import android.util.Log;

/**
 * Created by max on 4/28/2017.
 */

public class BackgroundService extends IntentService {

    public static final int STATUS_FINISHED = 1;

    public BackgroundService(){
        super(BackgroundService.class.getName());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d("ola service","service started");
        final ResultReceiver receiver = intent.getParcelableExtra("receiver");
        StringBuilder bin = new StringBuilder(intent.getStringExtra("binary_value"));

        Bundle bundle = new Bundle();

        int i =0 ;
//        if (bin.length() < 41) ;
//        {
//            do {
//                bin.insert(0,"0");
//            } while (bin.length() != 41);
//        }
        String binar = bin.toString();
        Log.d("ola service binar",binar);
//            Log.d("bin", bin + " " + String.valueOf(bin.length()));
//            Log.d("ola","Inside View Updater");
        for (char ch : binar.toCharArray()) {
            //Log.v("ola1 i",String.valueOf(i));
            //Log.v("ola1 ch",String.valueOf(ch));
            //Log.v("ola1 seatId",String.valueOf(seat.getId()));
            if(ch == '1'){
                i++;
            }
        }
        Log.d("ola service i",String.valueOf(i));
        bundle.putString("bin",binar);
        bundle.putInt("seatcount",i);
        receiver.send(STATUS_FINISHED,bundle);
        this.stopSelf();
    }
}
