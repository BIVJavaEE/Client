package servlets.dashboard;

import listeners.ApplicationData;

public class TypeAverage
{
    private String unit;
    private Double displayedAverage;

    public TypeAverage(double average, String type) {
        unit = ApplicationData.UNITS.get(type);
        displayedAverage = Math.round(average * 100.0) / 100.0;
    }

    public String getUnit() {
        return unit;
    }

    public Double getDisplayedAverage() {
        return displayedAverage;
    }
}
