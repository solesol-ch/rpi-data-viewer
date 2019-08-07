package ch.solesol.pidataviewer.api.objects;

public interface DataModel extends EnergyDetails, EnergyTimeFrame, CurrentPowerFlow {

    void refreshData();
}
