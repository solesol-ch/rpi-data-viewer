package ch.solesol.pidataviewer.api.objects.solaredge;

import ch.solesol.pidataviewer.Configuration;
import ch.solesol.pidataviewer.api.APIClient;
import ch.solesol.pidataviewer.api.objects.DataModel;

public class SolaredgeModel implements DataModel {

    private APIClient client;
    private EnergyDetailsSolaredge details;
    private CurrentPowerFlowSolaredge powerFlow;
    private EnergyTimeFrameSolaredge timeFrame;


    public SolaredgeModel() {
        // TODO : Use informations from config file
        Configuration config = Configuration.fromConfigFile();
        this.client = new APIClient("https://monitoringapi.solaredge.com/", config.siteId, config.apiKey, APIClient.InverterType.SOLAREDGE);
        this.powerFlow = (CurrentPowerFlowSolaredge) client.getCurrentPowerFlow();
    }


    @Override
    public float getCurrentProduction() {
        return powerFlow.getCurrentProduction();
    }

    @Override
    public float getCurrentUsage() {
        return powerFlow.getCurrentUsage();
    }

    @Override
    public float getCurrentFeedInOut() {
        return powerFlow.getCurrentFeedInOut();
    }

    @Override
    public boolean currentlyProducing() {
        return powerFlow.currentlyProducing();
    }

    @Override
    public boolean currentlyConsuming() {
        return powerFlow.currentlyConsuming();
    }

    @Override
    public float getDailyProduction() {
        return timeFrame.getDailyProduction();
    }

    @Override
    public float getWeeklyProduction() {
        return timeFrame.getWeeklyProduction();
    }

    @Override
    public float getYearlyProduction() {
        return timeFrame.getYearlyProduction();
    }
}
