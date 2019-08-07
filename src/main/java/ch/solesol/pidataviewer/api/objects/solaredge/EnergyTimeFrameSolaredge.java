package ch.solesol.pidataviewer.api.objects.solaredge;

import ch.solesol.pidataviewer.api.objects.EnergyMeasure;
import ch.solesol.pidataviewer.api.objects.EnergyTimeFrame;

public class EnergyTimeFrameSolaredge implements EnergyTimeFrame {

    private EnergyMeasure dailyProduction;
    private EnergyMeasure weeklyProduction;
    private EnergyMeasure yearlyProduction;

    public void setDailyProduction(EnergyMeasure dailyProduction) {
        this.dailyProduction = dailyProduction;
    }

    public void setWeeklyProduction(EnergyMeasure weeklyProduction) {
        this.weeklyProduction = weeklyProduction;
    }

    public void setYearlyProduction(EnergyMeasure yearlyProduction) {
        this.yearlyProduction = yearlyProduction;
    }

    @Override
    public float getDailyProduction() {
        return this.dailyProduction.value;
    }

    @Override
    public float getWeeklyProduction() {
        return this.weeklyProduction.value;
    }

    @Override
    public float getYearlyProduction() {
        return this.yearlyProduction.value;
    }
}
