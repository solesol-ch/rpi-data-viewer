package ch.solesol.pidataviewer.api.objects.solaredge;

import ch.solesol.pidataviewer.api.objects.EnergyDetails;
import ch.solesol.pidataviewer.api.objects.EnergyMeasure;

import java.util.List;

public class EnergyDetailsSolaredge implements EnergyDetails {

    private static class Meter {
        private String type;
        private List<EnergyMeasure> values;
    }

    private String timeUnit;
    private String unit;
    private List<Meter> meters;
}
