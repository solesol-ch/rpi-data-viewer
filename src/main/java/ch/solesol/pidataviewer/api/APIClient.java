package ch.solesol.pidataviewer.api;

import ch.solesol.pidataviewer.api.objects.CurrentPowerFlow;
import ch.solesol.pidataviewer.api.objects.solaredge.CurrentPowerFlowSolaredge;
import com.google.gson.Gson;
import org.glassfish.jersey.logging.LoggingFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.MediaType;
import java.util.logging.Level;
import java.util.logging.Logger;

public class APIClient {

    public static enum InverterType {SOLAREDGE, FRONIUS}

    private Client client;
    private String url;
    private InverterType type;
    private int siteId;
    private String apiKey;
    private Gson gson;

    public APIClient(String url, InverterType type) {
        if (type == InverterType.FRONIUS)
            throw new UnsupportedOperationException("FRONIUS inverters are currently not supported");

    }

    public APIClient(String url, int siteId, String apiKey,  InverterType type) {
        if (type == InverterType.FRONIUS)
            throw new UnsupportedOperationException("FRONIUS inverters are currently not supported");

        this.gson = new Gson();
        this.client = ClientBuilder.newClient();

        // TODO : Only for debug purpose
        /*Logger logger = Logger.getLogger(getClass().getName());
        Feature feature = new LoggingFeature(logger, Level.INFO, null, null);
        client.register(feature);*/

        this.url = url;
        this.type = type;
        this.siteId = siteId;
        this.apiKey = apiKey;
    }

    public CurrentPowerFlow getCurrentPowerFlow() {
        String res = client
                .target(this.url)
                .path(String.format("site/%d/currentPowerFlow", siteId))
                .queryParam("api_key", this.apiKey)
                .request(MediaType.APPLICATION_JSON)
                .get(String.class);

        return gson.fromJson(res, CurrentPowerFlowSolaredge.class);
    }




}
