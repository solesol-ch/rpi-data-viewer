package ch.solesol.pidataviewer.api.objects.solaredge;

import ch.solesol.pidataviewer.Configuration;
import ch.solesol.pidataviewer.api.APIClient;
import ch.solesol.pidataviewer.api.objects.DataModel;
import ch.solesol.pidataviewer.api.objects.EnergyMeasure;

import java.util.List;

public class SolaredgeModel implements DataModel {

    private APIClient client;
    private EnergyDetailsSolaredge details;
    private CurrentPowerFlowSolaredge powerFlow;
    private EnergyTimeFrameSolaredge timeFrame;


    public SolaredgeModel() {
        Configuration config = Configuration.fromConfigFile();
        this.client = new APIClient("https://monitoringapi.solaredge.com/", config.siteId, config.apiKey, APIClient.InverterType.SOLAREDGE);
        this.refreshData();
    }

    @Override
    public EnergyMeasure getCurrentProduction() {
        return powerFlow.getCurrentProduction();
    }

    @Override
    public EnergyMeasure getCurrentUsage() {
        return powerFlow.getCurrentUsage();
    }

    @Override
    public EnergyMeasure getCurrentFeedInOut() {
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

    @Override
    public List<EnergyMeasure> getProductionMeasures() {
        return details.getProductionMeasures();
    }

    @Override
    public void refreshData() {
        this.powerFlow = (CurrentPowerFlowSolaredge) client.getCurrentPowerFlow();
        this.details = (EnergyDetailsSolaredge) client.getEnergyDetails();
        this.timeFrame = (EnergyTimeFrameSolaredge) client.getEnergyTimeFrame();
    }
}
