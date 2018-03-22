package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.patient.Address;
import seedu.address.model.patient.BloodType;
import seedu.address.model.patient.DateOfBirth;
import seedu.address.model.patient.Email;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Nric;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.Phone;
import seedu.address.model.patient.Record;
import seedu.address.model.patient.Remark;
import seedu.address.model.tag.Tag;

/**
 * JAXB-friendly version of the Patient.
 */
public class XmlAdaptedPatient {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Patient's %s field is missing!";

    @XmlElement(required = true)
    private String name;
    @XmlElement(required = true)
    private String nric;
    @XmlElement(required = true)
    private String phone;
    @XmlElement(required = true)
    private String email;
    @XmlElement(required = true)
    private String address;
    @XmlElement(required = true)
    private String dob;
    @XmlElement(required = true)
    private String bloodType;
    @XmlElement(required = true)
    private String remark;
    @XmlElement(required = true)
    private Record record;

    @XmlElement
    private List<XmlAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs an XmlAdaptedPatient.
     * This is the no-arg constructor that is required by JAXB.
     */
    public XmlAdaptedPatient() {}

    /**
     * Constructs an {@code XmlAdaptedPatient} with the given patient details.
     */
    public XmlAdaptedPatient(String name, String nric, String phone, String email, String address, String dob,
                             String bloodType, List<XmlAdaptedTag> tagged) {
        this.name = name;
        this.nric = nric;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.dob = dob;
        this.bloodType = bloodType;
        if (tagged != null) {
            this.tagged = new ArrayList<>(tagged);
        }
    }

    /**
     * Converts a given Patient into this class for JAXB use.
     *
     * @param source future changes to this will not affect the created XmlAdaptedPatient
     */
    public XmlAdaptedPatient(Patient source) {
        name = source.getName().fullName;
        nric = source.getNric().value;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        dob = source.getDob().value;
        bloodType = source.getBloodType().value;
        remark = source.getRemark().value;
        record = source.getRecord();
        tagged = new ArrayList<>();
        for (Tag tag : source.getTags()) {
            tagged.add(new XmlAdaptedTag(tag));
        }
    }

    /**
     * Converts this jaxb-friendly adapted patient object into the model's Patient object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted patient
     */
    public Patient toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (XmlAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }

        if (this.name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(this.name)) {
            throw new IllegalValueException(Name.MESSAGE_NAME_CONSTRAINTS);
        }
        final Name name = new Name(this.name);

        if (this.nric == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Nric.class.getSimpleName()));
        }
        if (!Nric.isValidNric(this.nric)) {
            throw new IllegalValueException(Nric.MESSAGE_NRIC_CONSTRAINTS);
        }
        final Nric nric = new Nric(this.nric);

        if (this.phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(this.phone)) {
            throw new IllegalValueException(Phone.MESSAGE_PHONE_CONSTRAINTS);
        }
        final Phone phone = new Phone(this.phone);

        if (this.email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(this.email)) {
            throw new IllegalValueException(Email.MESSAGE_EMAIL_CONSTRAINTS);
        }
        final Email email = new Email(this.email);

        if (this.address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(this.address)) {
            throw new IllegalValueException(Address.MESSAGE_ADDRESS_CONSTRAINTS);
        }
        final Address address = new Address(this.address);

        if (this.dob == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    DateOfBirth.class.getSimpleName()));
        }
        if (!DateOfBirth.isValidDob(this.dob)) {
            throw new IllegalValueException(DateOfBirth.MESSAGE_DOB_CONSTRAINTS);
        }
        final DateOfBirth dob = new DateOfBirth(this.dob);

        if (this.bloodType == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    BloodType.class.getSimpleName()));
        }
        if (!BloodType.isValidBloodType(this.bloodType)) {
            throw new IllegalValueException(BloodType.MESSAGE_BLOODTYPE_CONSTRAINTS);
        }
        final BloodType bloodType = new BloodType(this.bloodType);

        final Remark remark = new Remark(this.remark);

        if (!Record.isValidRecord(this.record)) {
            throw new IllegalValueException(Record.MESSAGE_RECORD_CONSTRAINTS);
        }
        final Record record = new Record(this.record);

        final Set<Tag> tags = new HashSet<>(personTags);
        return new Patient(name, nric, phone, email, address, dob, bloodType, remark, record, tags);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof XmlAdaptedPatient)) {
            return false;
        }

        XmlAdaptedPatient otherPerson = (XmlAdaptedPatient) other;
        return Objects.equals(name, otherPerson.name)
                && Objects.equals(nric, otherPerson.nric)
                && Objects.equals(phone, otherPerson.phone)
                && Objects.equals(email, otherPerson.email)
                && Objects.equals(address, otherPerson.address)
                && Objects.equals(dob, otherPerson.dob)
                && Objects.equals(bloodType, otherPerson.bloodType)
                && tagged.equals(otherPerson.tagged);
    }
}
