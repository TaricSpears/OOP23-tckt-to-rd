package it.unibo.reader;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import java.awt.Color;

import it.unibo.commons.EdgeData;
import it.unibo.commons.Pair;
import it.unibo.controller.readercontroller.impl.CityReaderController;
import it.unibo.controller.readercontroller.impl.MapReaderController;
import it.unibo.controller.readercontroller.impl.RouteReaderController;
import it.unibo.model.carriage.impl.Carriage;
import it.unibo.model.city.api.City;
import it.unibo.model.city.impl.CityImpl;
import it.unibo.model.route.api.Route;
import it.unibo.model.route.impl.RouteImpl;

// CHECKSTYLE: MagicNumber OFF
class TestReaderController {
    private static final int MAP_HEIGHT = 3640;
    private static final int MAP_WIDTH = 5421;
    private static final int CITY_RADIUS = 34;
    private static final int RAIL_WIDTH = 58;
    private static final int RAIL_LENGTH = 180;

    @Test
    void testMapReaderController() {
        final var mapReader = new MapReaderController();
        assertEquals(MAP_WIDTH, mapReader.getMapWidth());
        assertEquals(MAP_HEIGHT, mapReader.getMapHeight());
        assertEquals(CITY_RADIUS, mapReader.getCityRadius());
        assertEquals(RAIL_WIDTH, mapReader.getRailWidth());
        assertEquals(RAIL_LENGTH, mapReader.getRailLength());
    }

    @Test
    void testCityReaderController() {
        final var cityReaderController = new CityReaderController();
        final Double xCoord = (Double) (2891.0 / MAP_WIDTH);
        final Double yCoord = (Double) (2308.0 / MAP_HEIGHT);
        final var coords = new Pair<Double, Double>(xCoord, yCoord);
        final City zagrab = new CityImpl("ZAGRAB", 25, coords, (double) CITY_RADIUS / MAP_WIDTH);
        /*
         * final List<City> cityList = cityReaderController.read();
         * for(int i=0; i<cityList.size(); i++){
         * System.out.println("CITY " + i + ":");
         * System.out.println("NAME: " + cityList.get(i).getName());
         * System.out.println("ID: " + cityList.get(i).getId());
         * System.out.println("RADIUS: " + cityList.get(i).getRadius());
         * System.out.println("COORDS: " + cityList.get(i).getCoordinates());
         * }
         */
        final City testCity = cityReaderController.read().get(25);
        assertEquals(zagrab.getId(), testCity.getId());
        assertEquals(zagrab.getName(), testCity.getName());
        assertEquals(0, Double.compare(zagrab.getCoordinates().first(), testCity.getCoordinates().first()));
        assertEquals(0, Double.compare(zagrab.getCoordinates().second(), testCity.getCoordinates().second()));
        assertEquals(0, Double.compare(zagrab.getRadius(), testCity.getRadius()));
    }

    @Test
    void testRouteReaderController() {
        final var routeReaderController = new RouteReaderController();
        final var routeList = routeReaderController.read();
        final var cityReaderController = new CityReaderController();
        final var cityList = cityReaderController.read();
        final Carriage c1 = new Carriage((float) 2207 / MAP_WIDTH, (float) 1499
                / MAP_HEIGHT,
                (float) RAIL_LENGTH / MAP_WIDTH,
                (float) RAIL_WIDTH / MAP_WIDTH, 0.5);
        final Carriage c2 = new Carriage((float) 2381 / MAP_WIDTH, (float) 1415
                / MAP_HEIGHT,
                (float) RAIL_LENGTH / MAP_WIDTH,
                (float) RAIL_WIDTH / MAP_WIDTH, 0.5);
        final Carriage c3 = new Carriage((float) 2558 / MAP_WIDTH, (float) 1332
                / MAP_HEIGHT,
                (float) RAIL_LENGTH / MAP_WIDTH,
                (float) RAIL_WIDTH / MAP_WIDTH, 0.5);
        final Set<Carriage> carriageSet = new HashSet<>();
        carriageSet.add(c1);
        carriageSet.add(c2);
        carriageSet.add(c3);
        final Route newRoute = new RouteImpl(new EdgeData(cityList.get(19),
                cityList.get(20), 3),
                Color.RED, 34);
        newRoute.setRailUnits(carriageSet);
        assertEquals(newRoute, routeList.get(34));
    }
}
