package com.example.bn4n5.lab6;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.location.LocationListener;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import android.Manifest;
import android.location.Address;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.graphics.Bitmap;
import android.widget.ImageView;
import com.google.android.gms.maps.model.LatLng;

public class signin extends AppCompatActivity {
    double lat;
    double lon;
    ImageView userImage ;
Bitmap photo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        LocationManager userCurrentLocation = (LocationManager) this
                .getSystemService(Context.LOCATION_SERVICE);
        LocationListener userCurrentLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        LatLng userCurrentLocationCorodinates = null;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat
                .checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            return;
        }

        userCurrentLocation.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                0, 0, userCurrentLocationListener);
        lat = userCurrentLocation
                .getLastKnownLocation(LocationManager.GPS_PROVIDER)
                .getLatitude();
        lon = userCurrentLocation
                .getLastKnownLocation(LocationManager.GPS_PROVIDER)
                .getLongitude();



        Button signup = (Button) findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(signin.this, MapsActivity.class);
                Intent send=new Intent(signin.this,MapsActivity.class);
                Bundle bundle=new Bundle();
                bundle.putDouble("lat",lat);
                bundle.putDouble("lon",lon);
                send.putExtra("bitmap",photo);
                send.putExtras(bundle);
                startActivity(i);
                startActivity(send);

            }
        });
        Geocoder geocoder = new Geocoder(this, Locale.ENGLISH);
        TextView myAddress = (TextView)findViewById(R.id.add);
        try {
            List<Address> addresses = geocoder.getFromLocation(lat, lon, 1);

            if(addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");
                for(int i=0; i<returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append(" ");
                }
                myAddress.setText(strReturnedAddress.toString());
            }
            else{

            }
        } catch (IOException e) {

            e.printStackTrace();

        }




        userImage = (ImageView) findViewById(R.id.img);

        userImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };
                AlertDialog.Builder builder = new AlertDialog.Builder(signin.this);
                builder.setTitle("Add Photo");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                if (options[item].equals("Take Photo")) {
                                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                    startActivityForResult(cameraIntent, 0);
                                }
                                if (options[item].equals("Choose from Gallery")) {
                                    Intent pickPhoto = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                    startActivityForResult(pickPhoto , 1);
                                }
                                if (options[item].equals("Choose from Gallery")) {

                                    dialog.dismiss();
                                }
                            }
                        });
                        builder.show();



            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == RESULT_OK) {
            photo = (Bitmap) data.getExtras().get("data");
            userImage.setImageBitmap(photo);
            Log.d("CameraDemo", "Pic saved");
        }
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Uri img = data.getData();
            try {
               // photo= MediaStore.Images.Media.getBitmap(this.getContentResolver(), img);
                photo = BitmapFactory.decodeStream(getContentResolver().openInputStream(img));
                Log.d("CameraDemo", "Pic saved");

            } catch (IOException e) {
                e.printStackTrace();
            }
            userImage.setImageBitmap(photo);
        }
    }
    }

