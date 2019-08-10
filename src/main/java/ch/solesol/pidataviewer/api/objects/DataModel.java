package ch.solesol.pidataviewer.api.objects;

public interface DataModel extends EnergyDetails, EnergyOverview, CurrentPowerFlow {

    void refreshAll();
    void refreshCurrentPowerFlow();
}
