package it.unibo.controller.readercontroller.impl;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import it.unibo.model.city.api.City;
import it.unibo.model.route.api.Route;

public class CityReaderController extends AbstractReaderController<Set<City>> {

    private static final String CITY_FILE_PATH = "/configuration/City.json";

    private final Set<City> cities;

    public CityReaderController(){
        super(CITY_FILE_PATH);
        this.cities = new LinkedHashSet<>();
    }

    @Override
    public Set<City> read() {
        final JSONParser parser = new JSONParser();
        JSONObject obj;
        try {
            final InputStreamReader inputStreamReader = new InputStreamReader(
                    this.getClass().getResourceAsStream(CITY_FILE_PATH), 
                    StandardCharsets.UTF_8);
            final JSONArray array = (JSONArray) parser.parse(inputStreamReader);
            for (final Object elem : array) {
                obj = (JSONObject) elem;
                final int id = Integer.parseInt(obj.get("id").toString());
                final int x = Integer.parseInt(obj.get("x").toString());
                final int y = Integer.parseInt(obj.get("y").toString());
                final JSONArray outGoingRoutes = (JSONArray)obj.get("outGoingRoutes");
                final List<Integer> extremes = new ArrayList<>();
                final Iterator<String> it = connectedCities.iterator(); 
                while(it.hasNext()){
                    extremes.add(Integer.parseInt(it.next()));
                }
            }
            inputStreamReader.close();
        } catch (IOException e) {
            System.out.println("Exception in file path operations");
        } catch (ParseException e1) {
            System.out.println("Exception in file parsing operations");
        }
        return Set.copyOf(this.cities);
    }
    
}
