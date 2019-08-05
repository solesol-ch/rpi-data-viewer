package ch.solesol.pidataviewer.api.objects.solaredge;

import ch.solesol.pidataviewer.api.objects.CurrentPowerFlow;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CurrentPowerFlowSolaredge implements CurrentPowerFlow {

    private SiteCurrentPowerFlow siteCurrentPowerFlow;

    @Override
    public float getCurrentProduction() {
        return siteCurrentPowerFlow.pv.currentPower;
    }

    @Override
    public float getCurrentUsage() {
        return siteCurrentPowerFlow.load.currentPower;
    }

    @Override
    public float getCurrentFeedInOut() {
        return siteCurrentPowerFlow.grid.currentPower;
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

    private static class Connection {
        private String from;
        private String to;
    }

    private static class SiteCurrentPowerFlow {
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
