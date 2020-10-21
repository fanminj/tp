package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;
import java.util.Set;

import seedu.address.model.Showable;
import seedu.address.model.tag.Tag;

/**
 * Represents a Student in the app.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student extends Person implements Showable<Student> {

    private final StudentId studentId;

    /**
     * Constructor for StudentId.
     * @param studentId a valid string representing a Student's id.
     */
    public Student(Name name, Phone phone, Email email, Set<Tag> tags, StudentId studentId) {
        super(name, phone, email, tags);
        requireAllNonNull(studentId);
        assert studentId != null;
        this.studentId = studentId;
    }

    public StudentId getStudentId() {
        return studentId;
    }

    /**
     * Returns true if both students of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two students.
     */
    public boolean isSame(Student otherStudent) {
        if (otherStudent == this) {
            return true;
        }

        return otherStudent != null
                && otherStudent.getName().equals(getName())
                && (otherStudent.getPhone().equals(getPhone()) || otherStudent.getEmail().equals(getEmail()))
                && otherStudent.getStudentId().equals(getStudentId());
    }

    public String getType() {
        return "student";
    }

    /**
     * Returns true if both students have the same identity and data fields.
     * This defines a stronger notion of equality between two students.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Student)) {
            return false;
        }

        Student otherStudent = (Student) other;
        return otherStudent.getName().equals(getName())
                && otherStudent.getPhone().equals(getPhone())
                && otherStudent.getEmail().equals(getEmail())
                && otherStudent.getTags().equals(getTags())
                && otherStudent.getStudentId().equals(getStudentId());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(getName(), getPhone(), getEmail(), getTags(), studentId);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        // todo add tut group
        builder.append(getName())
                .append(" Phone: ")
                .append(getPhone())
                .append(" Email: ")
                .append(getEmail())
                .append(" Student ID: ")
                .append(getStudentId())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }
}
