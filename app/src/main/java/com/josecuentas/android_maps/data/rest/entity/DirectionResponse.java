package com.josecuentas.android_maps.data.rest.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by jcuentas on 16/02/17.
 */

public class DirectionResponse {

    @SerializedName("geocoded_waypoints")public List<GeocodedWaypoints> geocodedWaypoints;
    @SerializedName("routes")public List<Routes> routes;
    @SerializedName("status")public String status;

    public static class GeocodedWaypoints {
        @SerializedName("geocoder_status")public String geocoderStatus;
        @SerializedName("place_id")public String placeId;
        @SerializedName("types")public List<String> types;
    }

    public static class Location {
        @SerializedName("lat")public double lat;
        @SerializedName("lng")public double lng;
    }

    public static class Bounds {
        @SerializedName("northeast")public Location northeast;
        @SerializedName("southwest")public Location southwest;
    }

    public static class Distance {
        @SerializedName("text")public String text;
        @SerializedName("value")public int value;
    }

    public static class Duration {
        @SerializedName("text")public String text;
        @SerializedName("value")public int value;
    }

    public static class Polyline {
        @SerializedName("points")public String points;
    }

    public static class Steps {
        @SerializedName("distance")public Distance distance;
        @SerializedName("duration")public Duration duration;
        @SerializedName("end_location")public Location endLocation;
        @SerializedName("html_instructions")public String htmlInstructions;
        @SerializedName("polyline")public Polyline polyline;
        @SerializedName("start_location")public Location startLocation;
        @SerializedName("travel_mode")public String travelMode;
    }

    public static class Traffic_speed_entry {}

    public static class Via_waypoint {}

    public static class Legs {
        @SerializedName("distance")public Distance distance;
        @SerializedName("duration")public Duration duration;
        @SerializedName("end_address")public String endAddress;
        @SerializedName("end_location")public Location endLocation;
        @SerializedName("start_address")public String startAddress;
        @SerializedName("start_location")public Location startLocation;
        @SerializedName("steps")public List<Steps> steps;
        @SerializedName("traffic_speed_entry")public List<Object> trafficSpeedEntry;
        @SerializedName("via_waypoint")public List<Object> viaWaypoint;
    }

    public static class OverviewPolyline {
        @SerializedName("points")public String points;
    }

    public static class Warnings {}

    public static class Routes {
        @SerializedName("bounds")public Bounds bounds;
        @SerializedName("copyrights")public String copyrights;
        @SerializedName("legs")public List<Legs> legs;
        @SerializedName("overview_polyline")public OverviewPolyline overviewPolyline;
        @SerializedName("summary")public String summary;
        @SerializedName("warnings")public List<Warnings> warnings;
        @SerializedName("waypoint_order")public List<Integer> waypointOrder;
    }
}
