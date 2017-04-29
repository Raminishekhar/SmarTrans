package com.pubnub.example.android.datastream.mapexample.smartrans;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.common.base.Throwables;
import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import com.pubnub.api.callbacks.SubscribeCallback;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.pubsub.PNMessageResult;
import com.pubnub.api.models.consumer.pubsub.PNPresenceEventResult;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener,DownloadResultReceiver.Receiver {

    private Marker busBodakdev;
    private Marker busVastrapur;
    private Marker busThaltej;

    private int notif;
    private String notifbusName;
    private float[] distance = new float[2];
    private Vibrator v;

    private ArrayList<Marker> markersBodakdev;
    private ArrayList<Marker> markersThaltej;
    private ArrayList<Marker> markersVastrapur;

    public static final String TAG = MainActivity.class.getName();

    public static final String DATASTREAM_PREFS = "com.pubnub.example.android.datastream.mapexample.DATASTREAM_PREFS";
    public static final String DATASTREAM_UUID = "com.pubnub.example.android.datastream.mapexample.DATASTREAM_UUID";

    public static final String PUBLISH_KEY = "pub-c-c9ea35cf-958a-425e-a72d-e37c16eb1a0a";
    public static final String SUBSCRIBE_KEY = "sub-c-a0bc40b4-0a1f-11e7-b95c-0619f8945a4f";
    private static final int MY_LOCATION_REQUEST_CODE = 1;

    private GoogleMap mMap;
    private Circle circle;

    private PubNub mPubNub;
    //private List<LatLng> mPoints = new ArrayList<>();
    String busChannelName;
    Boolean change = false;

    String bin;
    TextView no_seats;
    TextView busTitleView;
    String busTitle;

    final Handler handler = new Handler();
    DownloadResultReceiver mReceiver;
    View s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17,s18,s19,s20,s21,s22,s23,s24,s25,s26,s27,s28,s29,s30,s31,s32,s33,s34,s35,s36,s37,s38,s39,s40,s41;
    private ArrayList<View> viewArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mReceiver = new DownloadResultReceiver(new Handler());
        mReceiver.setReceiver(this);
        no_seats = (TextView) findViewById(R.id.no_of_seat);
        busTitleView = (TextView) findViewById(R.id.bus_title);

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

//      getSupportActionBar().hide();
    }

    public void resetClick(View v) {
        // Do something in response to button click
        notif = 0;

        for (Marker mkr : markersBodakdev) {
            mkr.setVisible(false);
        }
        for (Marker mkr : markersThaltej) {
            mkr.setVisible(false);
        }
        for (Marker marker : markersVastrapur) {
            marker.setVisible(false);
        }

        if (busThaltej != null) {
            busThaltej.setVisible(true);
        }
        if (busVastrapur != null) {
            busVastrapur.setVisible(true);
        }
        if (busBodakdev != null) {
            busBodakdev.setVisible(true);
        }

        if (circle != null) circle.setVisible(false);
        busChannelName = null;
        busTitleView.setText("Select a Bus");
        no_seats.setText("00");
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.style_json));

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_LOCATION_REQUEST_CODE);
            return;
        }
        mMap.setPadding(0, 70, 10, 0);
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(false);
        mMap.setOnInfoWindowClickListener(this);

        markersBodakdev = new ArrayList<Marker>();
        markersBodakdev.add(mMap.addMarker(new MarkerOptions().position(new LatLng(23.113996, 72.536630)).title("Bus Stop").anchor(0.5f, 0.5f).icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_stop_20))));
        markersBodakdev.add(mMap.addMarker(new MarkerOptions().position(new LatLng(23.102628, 72.532682)).title("Bus Stop").anchor(0.5f, 0.5f).icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_stop_20))));
        markersBodakdev.add(mMap.addMarker(new MarkerOptions().position(new LatLng(23.089680, 72.529249)).title("Bus Stop").anchor(0.5f, 0.5f).icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_stop_20))));
        markersBodakdev.add(mMap.addMarker(new MarkerOptions().position(new LatLng(23.072625, 72.524271)).title("Bus Stop").anchor(0.5f, 0.5f).icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_stop_20))));
        markersBodakdev.add(mMap.addMarker(new MarkerOptions().position(new LatLng(23.049565, 72.517404)).title("Bus Stop").anchor(0.5f, 0.5f).icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_stop_20))));

        markersVastrapur = new ArrayList<Marker>();
        markersVastrapur.add(mMap.addMarker(new MarkerOptions().position(new LatLng(23.049565, 72.517404)).title("Bus Stop").anchor(0.5f, 0.5f).icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_stop_20))));
        markersVastrapur.add(mMap.addMarker(new MarkerOptions().position(new LatLng(23.045932, 72.529935)).title("Bus Stop").anchor(0.5f, 0.5f).icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_stop_20))));
        markersVastrapur.add(mMap.addMarker(new MarkerOptions().position(new LatLng(23.037718, 72.527704)).title("Bus Stop").anchor(0.5f, 0.5f).icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_stop_20))));
        markersVastrapur.add(mMap.addMarker(new MarkerOptions().position(new LatLng(23.034005, 72.535707)).title("Bus Stop").anchor(0.5f, 0.5f).icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_stop_20))));
        markersVastrapur.add(mMap.addMarker(new MarkerOptions().position(new LatLng(23.028239, 72.531588)).title("Bus Stop").anchor(0.5f, 0.5f).icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_stop_20))));

        markersThaltej = new ArrayList<Marker>();
        markersThaltej.add(mMap.addMarker(new MarkerOptions().position(new LatLng(23.027291, 72.513070)).title("Bus Stop").anchor(0.5f, 0.5f).icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_stop_20))));
        markersThaltej.add(mMap.addMarker(new MarkerOptions().position(new LatLng(23.027607, 72.520194)).title("Bus Stop").anchor(0.5f, 0.5f).icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_stop_20))));
        markersThaltej.add(mMap.addMarker(new MarkerOptions().position(new LatLng(23.024131, 72.530236)).title("Bus Stop").anchor(0.5f, 0.5f).icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_stop_20))));
        markersThaltej.add(mMap.addMarker(new MarkerOptions().position(new LatLng(23.029266, 72.532296)).title("Bus Stop").anchor(0.5f, 0.5f).icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_stop_20))));
        markersThaltej.add(mMap.addMarker(new MarkerOptions().position(new LatLng(23.033768, 72.535557)).title("Bus Stop").anchor(0.5f, 0.5f).icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_stop_20))));


        for (Marker mkr : markersBodakdev) {
            mkr.setVisible(false);
        }
        for (Marker mkr : markersThaltej) {
            mkr.setVisible(false);
        }
        for (Marker marker : markersVastrapur) {
            marker.setVisible(false);
        }
    }

    private final void initPubNub() {
        PNConfiguration config = new PNConfiguration();

        config.setPublishKey(PUBLISH_KEY);
        config.setSubscribeKey(SUBSCRIBE_KEY);
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
                    // "23.48950 72.4895 1FFFFAB3"
                    //Log.v("ola", JsonUtil.asJson(message));
                    String lat = message.getMessage().toString().substring(message.getMessage().toString().indexOf('"') + 1, message.getMessage().toString().indexOf(" "));
                    //Log.v("ola", lat);
                    String lng = message.getMessage().toString().substring(message.getMessage().toString().indexOf(' ') + 1,
                            message.getMessage().toString().indexOf(' ', message.getMessage().toString().indexOf(' ') + 1));
                    //Log.v("ola", lng);

                    bin = message.getMessage().toString().substring(message.getMessage().toString().lastIndexOf(' ') + 1, message.getMessage().toString().lastIndexOf('"'));
                    //Log.d("ola bin",bin);

                    final String chan = message.getChannel().toString();
                    //Log.v(TAG, chan);
//                    Log.v("ola", JsonUtil.asJson(message));
//                    String lat = message.getMessage().toString().substring(message.getMessage().toString().indexOf('"') + 1, message.getMessage().toString().indexOf(" "));
//                    Log.v("ola", lat);
//                    String lng = message.getMessage().toString().substring(message.getMessage().toString().lastIndexOf(' ') + 1, message.getMessage().toString().lastIndexOf('"'));
//                    Log.v("ola", lng);
//                    String chan = message.getChannel().toString();
//                    Log.v(TAG, chan);
                    //Map<String, String> map = JsonUtil.convert(message.getMessage(), LinkedHashMap.class);
                    //String lat = map.get("lat");
                    //String lng = map.get("lng");
                    if (change && String.valueOf(busChannelName).equals(chan)) {
//                        new backgroundTask().execute(bin);
                        Log.d("ola bin",bin);
                        Intent intent = new Intent(Intent.ACTION_SYNC, null, getApplicationContext(), BackgroundService.class);
                        intent.putExtra("receiver",mReceiver);
                        intent.putExtra("binary_value",bin);
                        startService(intent);
                        change = false;
                    }
                    updateLocation(new LatLng(Double.parseDouble(lat), Double.parseDouble(lng)), chan);
                } catch (Exception e) {
                    throw Throwables.propagate(e);
                }
            }

            @Override
            public void presence(PubNub pubnub, PNPresenceEventResult presence) {
                // no presence handler for simplicity
            }
        });

        this.mPubNub.subscribe().channels(Arrays.asList("busBodakdev", "busThaltej", "busVastrapur")).execute();
    }

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {
        switch(resultCode){
            case BackgroundService.STATUS_FINISHED:
                int count = resultData.getInt("seatcount");
                int i=0;
                String bin = resultData.getString("bin");
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
                Log.d("ola value",String.valueOf(count));
                no_seats.setText(String.valueOf(count));
                busTitleView.setText(busTitle);
                break;
        }
    }

    private void updateLocation(final LatLng location, final String channel) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //mPoints.add(location)
                switch (channel) {
                    case "busThaltej":
                        if (busThaltej != null) {
                            busThaltej.setPosition(location);
                        } else {
                            busThaltej = mMap.addMarker(new MarkerOptions().position(location).title("Thaltej").anchor(0.5f, 1f).icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_30)));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 12));
                        }
                        break;
                    case "busVastrapur":
                        if (busVastrapur != null) {
                            busVastrapur.setPosition(location);
                        } else {
                            busVastrapur = mMap.addMarker(new MarkerOptions().position(location).title("Vastrapur").anchor(0.5f, 1f).icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_30)));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 12));
                        }
                        break;
                    case "busBodakdev":
                        if (busBodakdev != null) {
                            busBodakdev.setPosition(location);
                        } else {
                            busBodakdev = mMap.addMarker(new MarkerOptions().position(location).title("Bodakdev").anchor(0.5f, 1f).icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_30)));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 12));
                        }
                        break;
                    default:
                        break;
                }


                //Log.i("Data: ",notif + " " + channel + " " + notifbusName);
                if (notif == 1 && channel.equals(notifbusName)) {
                    Location.distanceBetween(location.latitude, location.longitude, circle.getCenter().latitude, circle.getCenter().longitude, distance);
                    if (distance[0] < circle.getRadius()) {
                        Toast.makeText(getBaseContext(), "Your bus will arrive in Five minutes", Toast.LENGTH_LONG).show();
                        v.vibrate(1000);
                        notif = 0;
                    }
                }
//                float[] distance = new float[2];
//
//                Location.distanceBetween( location.latitude, location.longitude,
//                        circle.getCenter().latitude, circle.getCenter().longitude, distance);
//
//                if( distance[0] > circle.getRadius()  ){
//                    Toast.makeText(getBaseContext(), "Outside", Toast.LENGTH_LONG).show();
//                } else {
//                    Toast.makeText(getBaseContext(), "Inside", Toast.LENGTH_LONG).show();
//                }
//                if (MainActivity.this.mPolyline != null) {
//                    MainActivity.this.mPolyline.setPoints(mPoints);
//                } else {
//                    MainActivity.this.mPolyline = mMap.addPolyline(new PolylineOptions().color(Color.BLUE).addAll(mPoints));
//                }

            }
        });
    }

    @Override
    public void onInfoWindowClick(Marker marker) {

        busTitle = marker.getTitle().toString();
        switch (busTitle) {
            case "Bodakdev":
                for (Marker marker1 : markersBodakdev) {
                    marker1.setVisible(true);
                }
                if (busVastrapur != null) {
                    busVastrapur.setVisible(false);
                }
                if (busThaltej != null) {
                    busThaltej.setVisible(false);
                }
                notifbusName = new String("busBodakdev");
                busChannelName = "busBodakdev";
                change = true;
                break;

            case "Vastrapur":
                for (Marker marker1 : markersVastrapur) {
                    marker1.setVisible(true);
                }
                if (busBodakdev != null) {
                    busBodakdev.setVisible(false);
                }
                if (busThaltej != null) {
                    busThaltej.setVisible(false);
                }
                notifbusName = new String("busVastrapur");
                busChannelName = "busVastrapur";
                change = true;
                break;

            case "Thaltej":
                for (Marker marker1 : markersThaltej) {
                    marker1.setVisible(true);
                }
                if (busVastrapur != null) {
                    busVastrapur.setVisible(false);
                }
                if (busBodakdev != null) {
                    busBodakdev.setVisible(false);
                }
                notifbusName = new String("busThaltej");
                busChannelName = "busThaltej";
                change = true;
                break;
            case "Bus Stop":
                if (circle == null) {
                    circle = mMap.addCircle(new CircleOptions()
                            .center(new LatLng(marker.getPosition().latitude, marker.getPosition().longitude))
                            .radius(1000)
                            .strokeWidth(0)
                            .fillColor(Color.argb(128, 76, 175, 80)));
                } else {
                    circle.setCenter(new LatLng(marker.getPosition().latitude, marker.getPosition().longitude));
                }
                circle.setVisible(true);
                notif = 1;
                break;
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_LOCATION_REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    Log.v(TAG, "Failed to obtain Location Permission");
                }
                return;
            }
        }
    }

}