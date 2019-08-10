package ch.solesol.pidataviewer;

import ch.solesol.pidataviewer.api.objects.DataModel;
import javafx.scene.input.TouchEvent;

import java.util.function.Function;

public abstract class ScreenController {

    protected DataModel model;

    public Function<TouchEvent, Boolean> switchScreenAction;

    public void setDataModel(DataModel m){
        model = m;
    }

    public abstract void refreshData();

    public boolean switchScreen(TouchEvent e) {
        return switchScreenAction.apply(e);
    }

}
