package seedu.address.model.patient;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ILLNESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SYMPTOM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TREATMENT;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

import seedu.address.logic.commands.RecordCommand;
import seedu.address.logic.parser.RecordCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Represents a list of Medical Records in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class RecordList {

    public static final String MESSAGE_RECORDLIST_CONSTRAINTS =
            "Patient record list can take any values, but each field must be populated";

    private ArrayList<Record> recordList;
    private int numRecord;

    public RecordList() {
        this.recordList = new ArrayList<Record>();
        recordList.add(new Record());
        this.numRecord = 1;
    }

    /**
     * Every field must be present and not null.
     */
    public RecordList(ArrayList<Record> recordList) {
        requireAllNonNull(recordList);
        this.recordList = recordList;
        this.numRecord = recordList.size();
    }

    public RecordList(String string) throws ParseException { //placeholder code
        this.recordList = new ArrayList<Record>();
        recordList.add(new Record());
        this.numRecord = 1;
    }

    public int getNumberOfRecords() {
        return numRecord;
    }

    public ArrayList<Record> getRecordList() {
        return recordList;
    } //currently not very defensive

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof RecordList)) {
            return false;
        }

        RecordList otherPatient = (RecordList) other;
        return otherPatient.getNumberOfRecords() == (this.getNumberOfRecords())
                && otherPatient.getRecordList().equals(this.getRecordList());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(numRecord, recordList);
    }

    /**
     * Returns true if all fields of record are non-null.
     */
    public static boolean isValidRecord(RecordList test) {
        requireAllNonNull(test, test.getNumberOfRecords(), test.getRecordList());
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for(int i = 0; i < numRecord; i++){
            builder.append("Index: ")
                    .append(recordList.get(i).toString())
                    .append("\n");
        }
        return builder.toString();
    }

    /**
     * Returns the string that is equivalent to the command that created this class.
     */
    public String toCommandString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("1 "); //as the command will not be executed, we will be placing a dummy patient index
        for(int i = 0; i < numRecord; i++){
            builder.append(PREFIX_INDEX)
                    .append(recordList.get(i).toCommandStringRecordList())
                    .append("\n");
        }
        return builder.toString();
    }

}
