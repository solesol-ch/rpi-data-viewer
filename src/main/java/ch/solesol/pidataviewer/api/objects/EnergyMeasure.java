package ch.solesol.pidataviewer.api.objects;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class EnergyMeasure {

    @SerializedName("date")
    public Date timestamp;
    public String unit;
    @SerializedName(value="value", alternate={"energy"})
    public float value;
    public float revenue;

    public EnergyMeasure(float value, String unit){
        this.value = value;
        this.unit = unit;
    }
}
