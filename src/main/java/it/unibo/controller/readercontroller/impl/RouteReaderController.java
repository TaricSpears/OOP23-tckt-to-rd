package it.unibo.controller.readercontroller.impl;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import java.util.Set;
import java.util.LinkedHashSet;
import it.unibo.model.route.api.Route;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class RouteReaderController extends AbstractReaderController<Set<Route>>{

    private static final String ROUTE_FILE_PATH = "/configuration/Routes.json";

    private final Set<Route> routes;

    public RouteReaderController() {
        super(ROUTE_FILE_PATH);
        this.routes = new LinkedHashSet<>();
    }

    @Override
    public Set<Route> read() {
        final JSONParser parser = new JSONParser();
        JSONObject obj;
        try {
            final InputStreamReader inputStreamReader = new InputStreamReader(
                    this.getClass().getResourceAsStream(ROUTE_FILE_PATH), 
                    StandardCharsets.UTF_8);
            final JSONArray array = (JSONArray) parser.parse(inputStreamReader);
            for (final Object elem : array) {
                /*obj = (JSONObject) elem;
                final String name = obj.get("name").toString();
                final List<Double> values = new ArrayList<>();
                values.add(Double.parseDouble(obj.get("x").toString()));
                values.add(Double.parseDouble(obj.get("y").toString()));
                values.add(Double.parseDouble(obj.get("width").toString()));
                values.add(Double.parseDouble(obj.get("height").toString()));
                this.territories.add(new Pair<>(name, values));*/
            }
            inputStreamReader.close();
        } catch (IOException e) {
            System.out.println("Exception in file path operations");
        } catch (ParseException e1) {
            System.out.println("Exception in file parsing operations");
        }
        return Set.copyOf(this.routes);
    }

}
