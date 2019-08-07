package ch.solesol.pidataviewer;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Configuration {


    public String inverter;
    public String language;

    @SerializedName("api_key")
    public String apiKey;
    public int siteId;

    private static final String CONFIG_FILE = "/home/yannis/Documents/PRIVATE/code/solesol/rpi-data-viewer/config.json";
    //private static final String CONFIG_FILE = "/home/pi/config.json";

    private Configuration() {}

    public static Configuration fromConfigFile(){
        Gson gson = new Gson();
        JsonReader reader = null;
        try {
            reader = new JsonReader(new FileReader(CONFIG_FILE));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return gson.fromJson(reader, Configuration.class);
    }
}
