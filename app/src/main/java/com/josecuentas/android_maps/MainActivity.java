package com.josecuentas.android_maps;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;
import com.josecuentas.android_maps.data.rest.ApiClient;
import com.josecuentas.android_maps.data.rest.entity.DirectionResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
* base: https://developers.google.com/maps/documentation/android-api/start?hl=es-419
* */
public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    SupportMapFragment mapFragment;
    List<LatLng> mPolys;
    GoogleMap mGoogleMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        LatLng sydney = new LatLng(-34, 151);
        googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        searchDirections("42.4614275,-71.0552091", "42.3598305,-71.3496743");
    }

    private void searchDirections(String origin, String destination){
        String key = getString(R.string.google_maps_api_key);
        Call<DirectionResponse> call = ApiClient.getInterface().searchDirecction(origin, destination, key);
        call.enqueue(new Callback<DirectionResponse>() {
            @Override public void onResponse(Call<DirectionResponse> call, Response<DirectionResponse> response) {
                if (response.isSuccessful()) {
                    DirectionResponse body = response.body();
                    mPolys = PolyUtil.decode(body.routes.get(0).overviewPolyline.points);
                    mGoogleMap.addPolyline(new PolylineOptions().addAll(mPolys));
                    mapCenter(body);
                }
            }

            @Override public void onFailure(Call<DirectionResponse> call, Throwable t) {

            }
        });
    }

    private void mapCenter(DirectionResponse directionResponse) {
        DirectionResponse.Location northeast = directionResponse.routes.get(0).bounds.northeast;
        DirectionResponse.Location southwest = directionResponse.routes.get(0).bounds.southwest;

        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(new LatLng(northeast.lat, northeast.lng));
        builder.include(new LatLng(southwest.lat, southwest.lng));
        LatLngBounds latLngCenter = builder.build();
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(latLngCenter, 20));
    }
}
