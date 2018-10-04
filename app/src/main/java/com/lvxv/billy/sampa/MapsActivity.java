package com.lvxv.billy.sampa;

import android.content.Intent;
import android.location.Location;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends BaseActivity implements OnMapReadyCallback , NavigationView.OnNavigationItemSelectedListener {

    private GoogleMap mMap;
    private BottomSheetBehavior mBottomSheetBehavior;
    View bottomSheet;
    TextView BankName,Address, OpenHour;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        bottomSheet = findViewById( R.id.bottom_sheet);

        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        mBottomSheetBehavior.setPeekHeight(100);
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

        BankName = (TextView) findViewById(R.id.NamaBank);
        Address = (TextView) findViewById(R.id.Alamat);
        OpenHour = (TextView) findViewById(R.id.JamBuka);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-7.251504, 112.791749);
        mMap.addMarker(new MarkerOptions().position(sydney)
                .title("SPBU Pertamina")
                .snippet("Indomaret, Jl. Kenjeran No.661, Dukuh Sutorejo, Mulyorejo, Kota SBY, Jawa Timur 60134")
        );
        // Add a marker in Sydney and move the camera
        LatLng zland = new LatLng(-7.251360, 112.792557);
        mMap.addMarker(new MarkerOptions().position(zland)
                .title("Cafe Mentari")
                .snippet("Jl. Wiratno No.1, Kenjeran, Bulak, Kota SBY, Jawa Timur 60134")
        );


        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        // Zoom in the Google Map
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));

        updateLocationUI();

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener()
        {
            @Override
            public boolean onMarkerClick(Marker arg0) {
                Toast toast = Toast.makeText( getApplicationContext(), arg0.getSnippet() , Toast.LENGTH_SHORT);
                toast.show();
                BankName.setText(arg0.getTitle());
                Address.setText(arg0.getSnippet());

                bottomSheet.setVisibility(View.VISIBLE);
                return true;
            }
        });
    }

    /**
     * Updates the map's UI settings based on whether the user has granted location permission.
     */
    private void updateLocationUI() {
        if (mMap == null) {
            return;
        }
        try {
          //  if (mLocationPermissionGranted) {
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
           /* } else {
                mMap.setMyLocationEnabled(false);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
                mLastKnownLocation = null;
                getLocationPermission();
            }*/
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }
/*
@SuppressWarnings("StatementWithEmptyBody")
@Override
public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.Beranda) {
        // Handle the camera action
        } else if (id == R.id.Jual) {
        Intent intent = new Intent(this, JualActivity.class);
        startActivity(intent);


        } else if (id == R.id.EduSampa) {


        } else if (id == R.id.BankSampah) {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);

        } else if (id == R.id.Informasi) {

        } else if (id == R.id.Berita) {

        } else if (id == R.id.Akun) {

        } else if (id == R.id.Pengaturan) {

        } else if (id == R.id.Tentang) {

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
        }

*/
}
