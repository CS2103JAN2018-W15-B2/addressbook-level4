package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.patient.Patient;
import seedu.address.model.tag.Tag;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyImdb {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Patient> getPersonList();

    /**
     * Returns an unmodifiable view of the tags list.
     * This list will not contain any duplicate tags.
     */
    ObservableList<Tag> getTagList();

    /**
     * Returns an unmodifiable view of the appointment list.
     * This list will not contain any duplicate appointment.
     */
    ObservableList<Appointment> getAppointmentList();

    /**
     * Returns an unmodifiable view of the patient queue.
     * This list will not contain any duplicate patient in the queue.
     */
    ObservableList<Patient> getUniquePatientQueue();

    /**
     * Returns an unmodifiable view of the past appointments of a patient.
     * This list will not contain any duplicate appointments.
     */
    ObservableList<Appointment> getPastAppointments();

    /**
     * Returns an unmodifiable view of the upcoming appointments of a patient.
     * This list will not contain any duplicate appointments.
     */
    ObservableList<Appointment> getUpcomingAppointments();
}
