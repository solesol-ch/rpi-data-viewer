package ch.solesol.pidataviewer.api.objects.solaredge;

import ch.solesol.pidataviewer.api.objects.EnergyTimeFrame;
import com.google.gson.annotations.SerializedName;

public class EnergyTimeFrameSolaredge implements EnergyTimeFrame {

    @SerializedName("energy")
    private float value;
    private String unit;

    @Override
    public float getDailyProduction() {
        return 0;
    }

    @Override
    public float getWeeklyProduction() {
        return 0;
    }

    @Override
    public float getYearlyProduction() {
        return 0;
    }
}
