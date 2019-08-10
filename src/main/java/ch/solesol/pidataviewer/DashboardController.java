package ch.solesol.pidataviewer;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class DashboardController extends ScreenController {

    @FXML
    private Label lblCurrentProduction;

    @FXML
    private Label lblCurrentUsage;

    @FXML
    private Label lblCurrentGrid;

    @FXML
    private ImageView imgSolarProduction;

    @FXML
    private ImageView imgGridFlow;

    public DashboardController() { }

    @FXML
    private void initialize() { }

    @Override
    public void refreshData() {
        this.lblCurrentProduction.setText(String.format("%.2f %s", this.model.getCurrentProduction().value, this.model.getCurrentProduction().unit));
        this.lblCurrentUsage.setText(String.format("%.2f %s", this.model.getCurrentUsage().value, this.model.getCurrentUsage().unit));
        this.lblCurrentGrid.setText(String.format("%.2f %s", this.model.getCurrentFeedInOut().value, this.model.getCurrentFeedInOut().unit));
        if(this.model.currentlyProducing()) this.imgSolarProduction.setImage(new Image(getClass().getResource("/images/right-arrow.png").toString()));
        if(this.model.currentlyConsuming()) this.imgGridFlow.setImage(new Image(getClass().getResource("/images/left-arrow.png").toString()));
        if(this.model.currentlySelling()) this.imgGridFlow.setImage(new Image(getClass().getResource("/images/right-arrow.png").toString()));
    }
}




