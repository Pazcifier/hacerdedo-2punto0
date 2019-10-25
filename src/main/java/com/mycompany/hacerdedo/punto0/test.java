/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hacerdedo.punto0;

import com.mapbox.api.directions.v5.MapboxDirections;
import com.mapbox.api.directions.v5.models.DirectionsRoute;
import com.mapbox.api.matching.v5.models.MapMatchingResponse;
import com.mapbox.geojson.Point;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *
 * @author estudiante.fit
 */
public class test {
    private Point directionsOriginPoint = Point.fromLngLat(24.9383791, 60.1698556);
    public void getRoute(Point destination) {
        MapboxDirections.Builder client = MapboxDirections.builder();
        client.origin(directionsOriginPoint);
        client.destination(destination);
        client.accessToken("pk.eyJ1IjoicGF6Y2lmaWVyIiwiYSI6ImNrMjRyYjg5MzBhbWszb24xN3QxeDBiem4ifQ.aMMJqTUJrPzVa18k75z8bg");
        System.out.println(client.build().toString()); //IMPORTANTE: Este tiene los datos de toda la ruta
    }
}
