/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hacerdedo.punto0;

import com.mapbox.api.directions.v5.DirectionsCriteria;
import com.mapbox.api.directions.v5.MapboxDirections;
import com.mapbox.api.directions.v5.models.DirectionsResponse;
import com.mapbox.geojson.Point;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *
 * @author estudiante.fit
 */
public class test {
    private static String TOKEN = "pk.eyJ1IjoicGF6Y2lmaWVyIiwiYSI6ImNrMjRyYjg5MzBhbWszb24xN3QxeDBiem4ifQ.aMMJqTUJrPzVa18k75z8bg";
    
    public void obtenerRuta(Point origen, Point destino) throws IOException {
        Point casa = Point.fromLngLat(-34.870303, -56.015101);
        MapboxDirections.Builder client = MapboxDirections.builder();
        client.origin(origen);
        client.destination(destino);
        client.accessToken(TOKEN);
        client.addWaypoint(origen);
        client.addWaypoint(destino);
        //client.overview(DirectionsCriteria.OVERVIEW_FULL);
        client.profile(DirectionsCriteria.PROFILE_DRIVING);
        System.out.println(client.build().toString());
        
        Response<DirectionsResponse> respuesta = client.build().executeCall();
        System.out.println(respuesta.toString());
        
        
        
        System.out.println(respuesta.body().routes().get(0));
    }
}
