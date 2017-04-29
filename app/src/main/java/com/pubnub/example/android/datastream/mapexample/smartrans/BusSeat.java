package com.pubnub.example.android.datastream.mapexample.smartrans;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.google.common.base.Throwables;
import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import com.pubnub.api.callbacks.SubscribeCallback;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.pubsub.PNMessageResult;
import com.pubnub.api.models.consumer.pubsub.PNPresenceEventResult;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

public class BusSeat extends AppCompatActivity {

    View s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17,s18,s19,s20,s21,s22,s23,s24,s25,s26,s27,s28,s29,s30,s31,s32,s33,s34,s35,s36,s37,s38,s39,s40,s41;
    String hex="1FFFFFFFFFF";    // 11 digit
    String binary;
    private PubNub mPubNub;
    String channelName; // Todo - get channel name from intent
    int i;
    private ArrayList<View> viewArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_bus_seat);

        channelName = getIntent().getStringExtra("channelname");
        Log.d("channel",channelName);
        s1 = (View) findViewById(R.id.seat_1);
        s2 = (View) findViewById(R.id.seat_2);
        s3 = (View) findViewById(R.id.seat_3);
        s4 = (View) findViewById(R.id.seat_4);
        s5 = (View) findViewById(R.id.seat_5);
        s6 = (View) findViewById(R.id.seat_6);
        s7 = (View) findViewById(R.id.seat_7);
        s8 = (View) findViewById(R.id.seat_8);
        s9 = (View) findViewById(R.id.seat_9);
        s10 = (View) findViewById(R.id.seat_10);

        s11 = (View) findViewById(R.id.seat_11);
        s12 = (View) findViewById(R.id.seat_12);
        s13 = (View) findViewById(R.id.seat_13);
        s14 = (View) findViewById(R.id.seat_14);
        s15 = (View) findViewById(R.id.seat_15);
        s16 = (View) findViewById(R.id.seat_16);
        s17 = (View) findViewById(R.id.seat_17);
        s18 = (View) findViewById(R.id.seat_18);
        s19 = (View) findViewById(R.id.seat_19);
        s20 = (View) findViewById(R.id.seat_20);

        s21 = (View) findViewById(R.id.seat_21);
        s22 = (View) findViewById(R.id.seat_22);
        s23 = (View) findViewById(R.id.seat_23);
        s24 = (View) findViewById(R.id.seat_24);
        s25 = (View) findViewById(R.id.seat_25);
        s26 = (View) findViewById(R.id.seat_26);
        s27 = (View) findViewById(R.id.seat_27);
        s28 = (View) findViewById(R.id.seat_28);
        s29 = (View) findViewById(R.id.seat_29);
        s30 = (View) findViewById(R.id.seat_30);


        s31 = (View) findViewById(R.id.seat_31);
        s32 = (View) findViewById(R.id.seat_32);
        s33 = (View) findViewById(R.id.seat_33);
        s34 = (View) findViewById(R.id.seat_34);
        s35 = (View) findViewById(R.id.seat_35);
        s36 = (View) findViewById(R.id.seat_36);
        s37 = (View) findViewById(R.id.seat_37);
        s38 = (View) findViewById(R.id.seat_38);
        s39 = (View) findViewById(R.id.seat_39);
        s40 = (View) findViewById(R.id.seat_40);
        s41 = (View) findViewById(R.id.seat_41);

        viewArrayList.addAll(Arrays.asList(s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17,s18,s19,s20,s21,s22,s23,s24,s25,s26,s27,s28,s29,s30,s31,s32,s33,s34,s35,s36,s37,s38,s39,s40,s41));
        initPubNub();
    }

    String HexToBinary(String Hex) {
        String bin =  new BigInteger(hex, 16).toString(2);
        //long inb = Long.parseLong(bin);
        //bin = String.format("%016d", inb);
        if(bin.length() < 41);{
            do{
                bin = "0"+bin;
            }while(bin.length() != 41);
        }
        return bin;
    }

    private final void initPubNub() {
        PNConfiguration config = new PNConfiguration();

        config.setPublishKey(MainActivity.PUBLISH_KEY);
        config.setSubscribeKey(MainActivity.SUBSCRIBE_KEY);
        config.setSecure(true);

        this.mPubNub = new PubNub(config);

        this.mPubNub.addListener(new SubscribeCallback() {
            @Override
            public void status(PubNub pubnub, PNStatus status) {
                // no status handler for simplicity
            }

            @Override
            public void message(PubNub pubnub, PNMessageResult message) {
                try {
                    hex = message.getMessage().toString().substring(message.getMessage().toString().lastIndexOf(' ') + 1, message.getMessage().toString().lastIndexOf('"'));
                    Log.d("hex",String.valueOf(hex));
                    String bin = HexToBinary(hex);
                    Log.d("bin",bin +" " + String.valueOf(bin.length()));
                    updateView(bin);
                } catch (Exception e) {
                    throw Throwables.propagate(e);
                }
            }

            @Override
            public void presence(PubNub pubnub, PNPresenceEventResult presence) {
                // no presence handler for simplicity
            }
        });

        this.mPubNub.subscribe().channels(Arrays.asList(channelName)).execute();
    }

    private void updateView(final String bin){

        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                i=0;
                for (char ch : bin.toCharArray()){
                    //Log.v("ola1 i",String.valueOf(i));
                    //Log.v("ola1 ch",String.valueOf(ch));
                    View seat = viewArrayList.get(i);
                    //Log.v("ola1 seatId",String.valueOf(seat.getId()));
                    if(ch == '1'){
                        seat.setBackgroundResource(R.drawable.bus_seat_on);
                    } else {
                        seat.setBackgroundResource(R.drawable.bus_seat_off);
                    }
                    i++;
                }
            }
        });
    }

//    @Override
//    protected void onPause() {
//        killService();
//        super.onPause();
//    }
//    private void killService() {
//        ActivityManager manager = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
//
//        List<ActivityManager.RunningAppProcessInfo> services = manager.getRunningAppProcesses();
//        for (ActivityManager.RunningAppProcessInfo service : services) {
//            if (service.processName.equals("com.pubnub.example.android.datastream.mapexample.pubnubandroidmap")) {
//                int pid = service.pid;
//                android.os.Process.killProcess(pid);
//            }
//        }
//    }
}
