package ch.solesol.pidataviewer.api.objects;

public interface CurrentPowerFlow {

    float getCurrentProduction();
    float getCurrentUsage();
    float getCurrentFeedInOut();
    boolean currentlyProducing();
    boolean currentlyConsuming();

}
