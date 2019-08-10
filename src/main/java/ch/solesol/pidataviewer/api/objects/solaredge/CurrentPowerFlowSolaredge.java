package ch.solesol.pidataviewer.api.objects.solaredge;

import ch.solesol.pidataviewer.api.objects.CurrentPowerFlow;
import ch.solesol.pidataviewer.api.objects.EnergyMeasure;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CurrentPowerFlowSolaredge implements CurrentPowerFlow {

    private CurrentPowerFlowElement siteCurrentPowerFlow;

    @Override
    public EnergyMeasure getCurrentProduction() {
        return new EnergyMeasure(siteCurrentPowerFlow.pv.currentPower, siteCurrentPowerFlow.unit);
    }

    @Override
    public EnergyMeasure getCurrentUsage() {
        return new EnergyMeasure(siteCurrentPowerFlow.load.currentPower, siteCurrentPowerFlow.unit);
    }

    @Override
    public EnergyMeasure getCurrentFeedInOut() {
        return new EnergyMeasure(siteCurrentPowerFlow.grid.currentPower, siteCurrentPowerFlow.unit);
    }

    /**
     * Checks if the installation is producing energy.
     * @return true if that is the case
     */
    @Override
    public boolean currentlyProducing() {
        return siteCurrentPowerFlow.connections.stream().anyMatch(c -> c.from.equalsIgnoreCase("PV"));
    }

    /**
     * Checks if the installation is consuming energy from the grid.
     * @return true if energy is consumed from the grid
     */
    @Override
    public boolean currentlyConsuming() {
        return siteCurrentPowerFlow.connections.stream().anyMatch(c -> c.from.equalsIgnoreCase("GRID"));
    }

    @Override
    public boolean currentlySelling() {
        return siteCurrentPowerFlow.connections.stream().anyMatch(c -> c.from.equalsIgnoreCase("LOAD"));
    }

    private static class Connection {
        private String from;
        private String to;
    }

    private static class CurrentPowerFlowElement {
        private String unit;
        private List<Connection> connections;

        @SerializedName("GRID")
        private PowerElement grid;
        @SerializedName("LOAD")
        private PowerElement load;
        @SerializedName("PV")
        private PowerElement pv;
    }

    private static class PowerElement{
        private String status;
        private float currentPower;
    }
}
