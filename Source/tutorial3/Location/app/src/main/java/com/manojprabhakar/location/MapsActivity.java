package com.manojprabhakar.location;

import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    boolean isGPSEnabled = false;
    boolean isNetworkEnabled = false;
    protected LocationManager locationManager;

    Location location; // location
    double latitude; // latitude
    double longitude; // longitude
    private GoogleMap mMap;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try{
            setContentView(R.layout.activity_maps);
            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
            Bundle b = getIntent().getExtras();
             bitmap = (Bitmap) b.get("photo");

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    StringBuilder userAddress;
    Geocoder geocoder;
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        geocoder = new Geocoder(this);
        try{
            mapactivity mp = new mapactivity(this);
            Location location = mp.getLocation();
            latitude = location.getLatitude();
            longitude = location.getLongitude();

            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            Address address = addresses.get(0);
            userAddress =  new StringBuilder();
            for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                userAddress.append(address.getAddressLine(i)).append("\t");
            }
            userAddress.append(address.getCountryName()).append("\t");

            // Add a marker in Sydney and move the camera
            LatLng sydney = new LatLng(latitude, longitude);
            BitmapDescriptor bmp = BitmapDescriptorFactory.fromBitmap(bitmap);
            mMap.addMarker(new MarkerOptions().position(sydney).title(String.valueOf(userAddress)).icon(bmp));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


}
