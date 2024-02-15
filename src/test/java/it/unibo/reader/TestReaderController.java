package it.unibo.reader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import it.unibo.commons.Pair;
import it.unibo.controller.readercontroller.impl.CityReaderController;
import it.unibo.controller.readercontroller.impl.MapReaderController;
import it.unibo.controller.readercontroller.impl.RouteReaderController;
import it.unibo.model.city.api.City;
import it.unibo.model.city.impl.CityImpl;

class TestReaderController {
    private static final int MAP_HEIGHT = 3640;
    private static final int MAP_WIDTH = 5421;
    private static final int CITY_RADIUS = 34;
    private static final int RAIL_WIDTH = 58;
    private static final int RAIL_LENGTH = 180;

    @Test
    void testMapReaderController(){
        final var mapReader = new MapReaderController();
        assertEquals(MAP_WIDTH, mapReader.getMapWidth());
        assertEquals(MAP_HEIGHT, mapReader.getMapHeight());
        assertEquals(CITY_RADIUS, mapReader.getCityRadius());
        assertEquals(RAIL_WIDTH, mapReader.getRailWidth());
        assertEquals(RAIL_LENGTH, mapReader.getRailLength());
    }

    @Test
    void testCityReaderController(){
        final var cityReaderController = new CityReaderController();
        final Double xCoord = (Double)(2891.0/MAP_WIDTH);
        final Double yCoord = (Double)(2308.0/MAP_HEIGHT);
        final var coords = new Pair<Double, Double>(xCoord, yCoord);
        final City zagrab = new CityImpl("ZAGRAB", 25, coords, CITY_RADIUS);
        /*final List<City> cityList = cityReaderController.read();
        for(int i=0; i<cityList.size(); i++){
            System.out.println("CITY " + i + ":");
            System.out.println("NAME: " + cityList.get(i).getName());
            System.out.println("ID: " + cityList.get(i).getId());
            System.out.println("RADIUS: " + cityList.get(i).getRadius());
            System.out.println("COORDS: " + cityList.get(i).getCoordinates());
        }*/
        final City testCity = cityReaderController.read().get(25);
        assertEquals(zagrab.getId(), testCity.getId());
        assertTrue(zagrab.getName().equals(testCity.getName()));
        assertEquals(1, Double.compare(zagrab.getCoordinates().first(), testCity.getCoordinates().first()));
        assertEquals(1, Double.compare(zagrab.getCoordinates().second(), testCity.getCoordinates().second()));
        assertEquals(1, Double.compare(zagrab.getRadius(), testCity.getRadius()));
    }

    @Test
    void testRouteReaderController(){
        final var routeReaderController = new RouteReaderController();
        final var routeSet = routeReaderController.read();
        
    }
}
