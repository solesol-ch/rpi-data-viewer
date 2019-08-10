package ch.solesol.pidataviewer.api.objects.solaredge;

import ch.solesol.pidataviewer.api.objects.EnergyMeasure;
import ch.solesol.pidataviewer.api.objects.EnergyOverview;
import com.google.gson.annotations.SerializedName;

public class EnergyOverviewSolaredge implements EnergyOverview {

    private static class EnergyOverviewElement {

        @SerializedName("lastDayData")
        private EnergyMeasure dailyProduction;
        @SerializedName("lastMonthData")
        private EnergyMeasure monthlyProduction;
        @SerializedName("LastYearData")
        private EnergyMeasure yearlyProduction;
    }

    private EnergyOverviewElement overview;

    @Override
    public EnergyMeasure getDailyProduction() {
        return this.overview.dailyProduction;
    }

    @Override
    public EnergyMeasure getMonthlyProduction() {
        return this.overview.monthlyProduction;
    }

    @Override
    public EnergyMeasure getYearlyProduction() {
        return this.overview.yearlyProduction;
    }
}
