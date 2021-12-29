package handlers;

import com.badlogic.gdx.Gdx;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Foodtype;
import entities.Gamelevel;
import entities.Gridposition;
import entities.Guest;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class JsonLevelReader {
    private List<Gridposition> gridpositions = new ArrayList<>();
    private List<Guest> guestList = new ArrayList<>();

    /**
     * this method reads the a level's corresponding JSON-File which determines the level' configuration
     *
     * @param levelName the identifier of the level
     * @return a Gamelevel object that was build based on the configuration from the JSON File
     */
    public Gamelevel readLevelConfiguration(String levelName) {
        JSONParser parser = new JSONParser();
        //String filepath = levelName;
        //TODO: for exporting the game to .jar the levelfiles have to be in the
        // same folder as the jar file itself (so the path is only levelname):
        // desktop/build/libs
        // USE "gradlew desktop:dist" to export the game
        // and "java -jar desktop-1.0.jar" to run the game from the folder where the jar is
        //String filepath = "C:\\Users\\evasc\\Desktop\\Uni\\Auslandssemester\\Game Design\\tddd23\\core\\assets\\" + levelName;
        try (Reader reader = new FileReader(levelName)) {
            Gdx.app.log("INFO: ", "Start reading JSON-Level-Config");
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            Gamelevel level = new Gamelevel();
            String name = jsonObject.get("name").toString();
            level.setName(name);
            long money = (long) jsonObject.get("money");
            level.setMoney(money);
            JSONArray allowedDishes = (JSONArray) jsonObject.get("allowedDishes");
            JSONArray positions = (JSONArray) jsonObject.get("positions");
            JSONArray guests = (JSONArray) jsonObject.get("guests");
            ArrayList<Foodtype> permittedDishes = new ArrayList<>();
            for (Object o : allowedDishes) {
                switch ((String) o) {
                    case "beer":
                        permittedDishes.add(Foodtype.BEER);
                        break;
                    case "pizza":
                        permittedDishes.add(Foodtype.PIZZA);
                        break;
                    case "chicken":
                        permittedDishes.add(Foodtype.CHICKEN);
                        break;
                    case "fish":
                        permittedDishes.add(Foodtype.FISH);
                        break;
                    case "pommes":
                        permittedDishes.add(Foodtype.POMMES);
                        break;
                }
            }
            level.setPermittedDishes(permittedDishes);
            for (Object o : positions) {
                Gridposition gridposition = convertJsonToGridPosition(o);
                gridpositions.add(gridposition);
            }
            int index = 0;
            JSONObject res;
            for (Object o : guests) {
                res = (JSONObject) guests.get(index);
                guestList.add(new Guest(
                        (long) res.get("spawntime"),
                        (long) res.get("patience"),
                        (long) res.get("wealth"),
                        (String) res.get("type")
                ));
                index++;
            }
            level.setGridpositionList(gridpositions);
            level.setGuests(guestList);
            Gdx.app.log("INFO: ", "Finished reading JSON-Level-Config");
            return level;
        } catch (IOException | ParseException e) {
            Gdx.app.error("ERROR: ", "reading from JSON File failed", e);
        }
        return null;
    }

    /**
     * this method converts a Json Object to a Gridposition Object
     *
     * @param o the object to be converted
     * @return a Gridposition Object
     */
    private Gridposition convertJsonToGridPosition(Object o) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return (objectMapper.readValue(o.toString(), Gridposition.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

