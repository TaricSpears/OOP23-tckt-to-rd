package it.unibo.controller.readercontroller.impl;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import java.util.List;
import java.util.logging.Logger;
import java.util.LinkedList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * This class models a ReaderController for reading MapData.json file.
 */
public class MapReaderController extends AbstractReaderController<List<Integer>> {

    private static final String MAP_FILE_PATH = "/configuration/EuropeConfiguration/MapData.json";
    private final List<Integer> mapData;

    /**
     * Initializes the MapReaderController.
     */
    public MapReaderController() {
        super(MAP_FILE_PATH);
        final JSONParser parser = new JSONParser();
        final List<Integer> retList = new LinkedList<>();
        try {
            final InputStreamReader inputStreamReader = new InputStreamReader(
                    this.getClass().getResourceAsStream(MAP_FILE_PATH),
                    StandardCharsets.UTF_8);
            final JSONObject obj = (JSONObject) parser.parse(inputStreamReader);
            retList.add(Integer.parseInt(obj.get("mapWidth").toString()));
            retList.add(Integer.parseInt(obj.get("mapHeight").toString()));
            retList.add(Integer.parseInt(obj.get("cityRadius").toString()));
            retList.add(Integer.parseInt(obj.get("railWidth").toString()));
            retList.add(Integer.parseInt(obj.get("railLength").toString()));
            inputStreamReader.close();
        } catch (IOException e) {
            Logger.getLogger(MapReaderController.class.getName()).fine("Exception in file path operations");
        } catch (ParseException e1) {
            Logger.getLogger(MapReaderController.class.getName()).fine("Exception in file parsing operations");
        }
        this.mapData = retList;
    }

    /**
     * @return the result of the reading operation
     */
    @Override
    public List<Integer> read() {
        return this.mapData;
    }

    /**
     * @return the map's width
     */
    public int getMapWidth() {
        return this.mapData.get(0);
    }

    /**
     * @return the map's heigth
     */
    public int getMapHeight() {
        return this.mapData.get(1);
    }

    /**
     * @return the map's city radius
     */
    public int getCityRadius() {
        return this.mapData.get(2);
    }

    /**
     * @return the map's rail width
     */
    public int getRailWidth() {
        return this.mapData.get(3);
    }

    /**
     * @return the map's rail length
     */
    public int getRailLength() {
        return this.mapData.get(4);
    }

}
