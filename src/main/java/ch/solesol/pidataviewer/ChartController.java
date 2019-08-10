package ch.solesol.pidataviewer;

import javafx.fxml.FXML;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;

import java.util.Calendar;

public class ChartController extends ScreenController {

    @FXML
    private AreaChart<String, Number> chrtEnergy;

    @FXML
    private void initialize() {
        chrtEnergy.setLegendVisible(false);
    }

    @Override
    public void refreshData() {
        XYChart.Series<String, Number> serie = new XYChart.Series<>();
        Calendar cal = Calendar.getInstance();

        // FIXME: Labels are the same. ex 16h yesterday and 16h today (label = 16)
        serie.getData().addAll(this.model.getProductionMeasures().stream().map(em -> {
            cal.setTime(em.timestamp);
            return new XYChart.Data(String.format("%02d", cal.get(Calendar.HOUR_OF_DAY)), em.value);
        }).toArray(XYChart.Data[]::new));
        chrtEnergy.getData().add(serie);
    }
}
