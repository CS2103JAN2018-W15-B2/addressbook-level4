package seedu.address.logic.record;

import seedu.address.model.patient.Record;

/**
 * RecordManager class to handle user inputs to medical record.
 */
public final class RecordManager {


    private static Record currRecord = null;

    private RecordManager() {
        currRecord = new Record();
    }

    public static Record getRecord() {
        if (currRecord == null) {
            return null;
        }
        Record temp = new Record(currRecord.getDate(), currRecord.getSymptom(), currRecord.getIllness(), currRecord.getTreatment());
        //reset current record so it cannot be duplicated to other patients
        currRecord = null;
        return temp;
    }

    /**
     * Check if all medical record fields are valid.
     * Current medical record will be updated.
     */
    public static boolean authenticate (String date, String symptom, String illness, String treatment) {
        if(date == null || symptom == null || illness == null || treatment == null){
            //currently all non-null strings are valid
            return false;
        }
        currRecord = new Record(date, symptom, illness, treatment);
        return true;
    }
}
