package ch.solesol.pidataviewer.api.objects;

public interface EnergyOverview {
    EnergyMeasure getDailyProduction();
    EnergyMeasure getMonthlyProduction();
    EnergyMeasure getYearlyProduction();
}
