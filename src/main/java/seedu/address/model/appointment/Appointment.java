package seedu.address.model.appointment;

/**
 * Patient appointment in IMDB
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Appointment {

    public static final int APPOINTMENT_DURATION = 30;
    private String appointmentDateTime;
    private String patientName;
    public Appointment(String patientName, String appointmentDateTime) {
        this.patientName = patientName;
        this.appointmentDateTime = appointmentDateTime;
    }

    public String getPatientName() {
        return this.patientName;
    }

    public String getAppointmentDateTime() {
        return this.appointmentDateTime;
    }
}
