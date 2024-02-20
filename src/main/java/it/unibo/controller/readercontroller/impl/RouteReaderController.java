package it.unibo.controller.readercontroller.impl;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import java.util.Set;
import java.util.logging.Logger;
import java.util.List;
import java.util.LinkedList;
import java.util.LinkedHashSet;

import it.unibo.commons.EdgeData;
import it.unibo.commons.IntToColorConverter;
import it.unibo.model.city.api.City;
import it.unibo.model.route.api.Route;
import it.unibo.model.route.impl.RouteImpl;
import it.unibo.model.carriage.impl.Carriage;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.Color;

/**
 * This class models a ReaderController for reading Routes.json file.
 */
public final class RouteReaderController extends AbstractReaderController<List<Route>> {

    private static final String ROUTE_FILE_PATH = "/configuration/EuropeConfiguration/Routes.json";

    private final int mapWidth;
    private final int mapHeight;
    private final int railWidth;
    private final int railLength;
    private final List<Route> routes;
    private final List<City> cities;

    /**
     * Initializes the RouteReaderController.
     */
    public RouteReaderController() {
        super(ROUTE_FILE_PATH);
        this.routes = new LinkedList<>();
        final var mapReader = new MapReaderController();
        this.mapWidth = mapReader.getMapWidth();
        this.mapHeight = mapReader.getMapHeight();
        this.railWidth = mapReader.getRailWidth();
        this.railLength = mapReader.getRailLength();
        final var cityReader = new CityReaderController();
        cities = cityReader.read();
    }

    /**
     * @return the result of the reading operation
     */
    @Override
    public List<Route> read() {
        final JSONParser parser = new JSONParser();
        JSONObject obj;
        try {
            final InputStreamReader inputStreamReader = new InputStreamReader(
                    this.getClass().getResourceAsStream(ROUTE_FILE_PATH),
                    StandardCharsets.UTF_8);
            final JSONArray array = (JSONArray) parser.parse(inputStreamReader);
            for (final Object elem : array) {
                obj = (JSONObject) elem;
                final int id = Integer.parseInt(obj.get("id").toString());

                final int intColor = Integer.parseInt(obj.get("color").toString());

                final Set<Carriage> railUnits = new LinkedHashSet<>();
                final JSONArray xArray = (JSONArray) obj.get("x");
                final JSONArray yArray = (JSONArray) obj.get("y"); 
                final JSONArray angleArray = (JSONArray) obj.get("angle");
                for(int i=0; i<xArray.size(); i++){
                    final double xCoord = (double) Integer.parseInt(xArray.get(i).toString()) / this.mapWidth;
                    final double yCoord = (double) Integer.parseInt(yArray.get(i).toString()) / this.mapHeight;
                    final double angle = (double) Double.parseDouble(angleArray.get(i).toString());
                    final var carriage = new Carriage(xCoord, yCoord, (double) railLength / this.mapWidth, (double) this.railWidth / this.mapWidth, angle);
                    railUnits.add(carriage);
                }

                final int weight = railUnits.size();
                final JSONArray routeExtremes = (JSONArray) obj.get("connectedCities");
                final var city1 = this.cities.get(Long.valueOf((Long) routeExtremes.get(0)).intValue());
                final var city2 = this.cities.get(Long.valueOf((Long) routeExtremes.get(1)).intValue());
                final EdgeData connectedCity = new EdgeData(city1, city2, weight);

                final Color color = new IntToColorConverter().apply(intColor);

                this.routes.add(new RouteImpl(connectedCity, color, id, railUnits));
            }
            inputStreamReader.close();
        } catch (IOException e) {
            Logger.getLogger(MapReaderController.class.getName()).fine("Exception in file path operations");
        } catch (ParseException e1) {
            Logger.getLogger(MapReaderController.class.getName()).fine("Exception in file parsing operations");
        }
        return List.copyOf(this.routes);
    }

}
