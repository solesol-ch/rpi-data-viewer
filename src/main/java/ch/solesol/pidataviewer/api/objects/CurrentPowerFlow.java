package ch.solesol.pidataviewer.api.objects;

public interface CurrentPowerFlow {
    EnergyMeasure getCurrentProduction();
    EnergyMeasure getCurrentUsage();
    EnergyMeasure getCurrentFeedInOut();
    boolean currentlyProducing();
    boolean currentlyConsuming();
    boolean currentlySelling();
}
