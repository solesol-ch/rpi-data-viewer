package ch.solesol.pidataviewer.api.objects.solaredge;

import ch.solesol.pidataviewer.api.objects.EnergyDetails;
import ch.solesol.pidataviewer.api.objects.EnergyMeasure;

import java.util.List;

public class EnergyDetailsSolaredge implements EnergyDetails {

    private EnergyDetailsElement energyDetails;

    @Override
    public List<EnergyMeasure> getProductionMeasures() {
        // TODO : what if there is no production type ?
        return energyDetails.meters.stream().filter(v -> v.type.equalsIgnoreCase("production")).findFirst().get().values;
    }

    private static class EnergyDetailsElement {

        private static class Meter {
            private String type;
            private List<EnergyMeasure> values;
        }

        private String timeUnit;
        private String unit;
        private List<Meter> meters;
    }




}
