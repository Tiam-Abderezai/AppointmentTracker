package Model;

import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class Appointment {

    private String dateStart;
    private String dateEnd;
    private String type;

    public Appointment(String dateStart, String dateEnd, String type) {
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.type = type;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDateStart() {
        return dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public String getType() {
        return type;
    }
}
