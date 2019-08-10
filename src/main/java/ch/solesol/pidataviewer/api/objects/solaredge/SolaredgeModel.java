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
    private EnergyOverviewSolaredge timeFrame;


    public SolaredgeModel() {
        Configuration config = Configuration.fromConfigFile();
        this.client = new APIClient("https://monitoringapi.solaredge.com/", config.siteId, config.apiKey, APIClient.InverterType.SOLAREDGE);
        this.refreshAll();
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
    public boolean currentlySelling() {
        return powerFlow.currentlySelling();
    }

    @Override
    public EnergyMeasure getDailyProduction() {
        return timeFrame.getDailyProduction();
    }

    @Override
    public EnergyMeasure getMonthlyProduction() {
        return timeFrame.getMonthlyProduction();
    }

    @Override
    public EnergyMeasure getYearlyProduction() {
        return timeFrame.getYearlyProduction();
    }

    @Override
    public List<EnergyMeasure> getProductionMeasures() {
        return details.getProductionMeasures();
    }

    @Override
    public void refreshAll() {
        this.refreshCurrentPowerFlow();
        this.details = (EnergyDetailsSolaredge) client.getEnergyDetails();
        this.timeFrame = (EnergyOverviewSolaredge) client.getEnergyOverview();
    }

    @Override
    public void refreshCurrentPowerFlow() {
        this.powerFlow = (CurrentPowerFlowSolaredge) client.getCurrentPowerFlow();
    }
}
