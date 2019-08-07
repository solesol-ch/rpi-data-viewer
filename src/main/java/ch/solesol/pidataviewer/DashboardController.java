package ch.solesol.pidataviewer;

import ch.solesol.pidataviewer.api.objects.DataModel;
import ch.solesol.pidataviewer.api.objects.solaredge.SolaredgeModel;
import javafx.beans.value.ObservableStringValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DashboardController {

    private DataModel model;

    private ObservableStringValue currentProductionProperty;

    @FXML
    private Label lblCurrentProduction;

    @FXML
    private Label lblCurrentUsage;

    @FXML
    private Label lblCurrentGrid;

    public DashboardController() {
        /*Configuration config = Configuration.fromConfigFile();
        if(config.inverter.equalsIgnoreCase("solaredge"))
            this.model = new SolaredgeModel();
        else
            this.model = null; // TODO : Add support for fronius models*/
        this.model = new SolaredgeModel();
    }

    @FXML
    private void initialize() {
        this.lblCurrentProduction.setText(String.format("%.2f %s", this.model.getCurrentProduction().value, this.model.getCurrentProduction().unit));
        this.lblCurrentUsage.setText(String.format("%.2f %s", this.model.getCurrentUsage().value, this.model.getCurrentUsage().unit));
        this.lblCurrentGrid.setText(String.format("%.2f %s", this.model.getCurrentFeedInOut().value, this.model.getCurrentFeedInOut().unit));
    }


}




