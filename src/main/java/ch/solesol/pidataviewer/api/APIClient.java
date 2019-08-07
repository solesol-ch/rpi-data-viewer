package ch.solesol.pidataviewer.api;

import ch.solesol.pidataviewer.api.objects.CurrentPowerFlow;
import ch.solesol.pidataviewer.api.objects.EnergyDetails;
import ch.solesol.pidataviewer.api.objects.EnergyMeasure;
import ch.solesol.pidataviewer.api.objects.EnergyTimeFrame;
import ch.solesol.pidataviewer.api.objects.solaredge.CurrentPowerFlowSolaredge;
import ch.solesol.pidataviewer.api.objects.solaredge.EnergyDetailsSolaredge;
import ch.solesol.pidataviewer.api.objects.solaredge.EnergyTimeFrameSolaredge;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.glassfish.jersey.logging.LoggingFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

        this.gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        this.client = ClientBuilder.newClient();

        // TODO : Only for debug purpose
        Logger logger = Logger.getLogger(getClass().getName());
        Feature feature = new LoggingFeature(logger, Level.INFO, null, null);
        client.register(feature);


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

    public EnergyDetails getEnergyDetails() {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();


        String res = client
                .target(this.url)
                .path(String.format("site/%d/energyDetails", siteId))
                .queryParam("api_key", this.apiKey)
                .queryParam("timeUnit", "HOUR")
                .queryParam("startTime", dtf.format(now.minusHours(24)))
                .queryParam("endTime", dtf.format(now))
                .request(MediaType.APPLICATION_JSON)
                .get(String.class);

        return gson.fromJson(res, EnergyDetailsSolaredge.class);
    }

    public EnergyTimeFrame getEnergyTimeFrame() {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();

        EnergyTimeFrameSolaredge etf = new EnergyTimeFrameSolaredge();
        String dailyProduction = client
                .target(this.url)
                .path(String.format("site/%d/timeFrameEnergy", siteId))
                .queryParam("api_key", this.apiKey)
                .queryParam("timeUnit", "MONTH")
                .queryParam("startDate", dtf.format(now))
                .queryParam("endDate", dtf.format(now.plusDays(1)))
                .request(MediaType.APPLICATION_JSON)
                .get(String.class);

        String weeklyProduction = client
                .target(this.url)
                .path(String.format("site/%d/timeFrameEnergy", siteId))
                .queryParam("api_key", this.apiKey)
                .queryParam("timeUnit", "MONTH")
                .queryParam("startDate", dtf.format(now.minusWeeks(1)))
                .queryParam("endDate", dtf.format(now.plusDays(1)))
                .request(MediaType.APPLICATION_JSON)
                .get(String.class);

        String yearlyProduction = client
                .target(this.url)
                .path(String.format("site/%d/timeFrameEnergy", siteId))
                .queryParam("api_key", this.apiKey)
                .queryParam("timeUnit", "MONTH")
                .queryParam("startDate", dtf.format(now.minusDays(364)))
                .queryParam("endDate", dtf.format(now.plusDays(1)))
                .request(MediaType.APPLICATION_JSON)
                .get(String.class);
        etf.setDailyProduction(gson.fromJson(dailyProduction, EnergyMeasure.class));
        etf.setDailyProduction(gson.fromJson(weeklyProduction, EnergyMeasure.class));
        etf.setDailyProduction(gson.fromJson(yearlyProduction, EnergyMeasure.class));
        return etf;
    }


}
