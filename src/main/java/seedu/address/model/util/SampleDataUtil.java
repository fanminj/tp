package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.Module;
import seedu.address.model.ReadOnlyTrackr;
import seedu.address.model.Showable;
import seedu.address.model.Trackr;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Student;
import seedu.address.model.person.StudentId;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil<T extends Showable<T>> {
    public static Module[] getSampleModules() {
        return new Module[] {
            new Module("CS2103T")
        };
    }

    public static <T extends Showable<T>> ReadOnlyTrackr<T> getSampleModuleList() {
        Trackr<T> sampleAb = new Trackr<>();
        for (Module sampleModule : getSampleModules()) {
            T sample = (T) sampleModule;
            sampleAb.addObject(sample);
        }
        return sampleAb;
    }

    public static Student[] getSampleStudents() {
        return new Student[] {new Student(new Name("john"), new Phone("12345678"), new Email("hello@email.com"),
                        getTagSet("friends"), new StudentId("A1234567X"))
        };
    }

    public static ReadOnlyTrackr<Student> getSampleStudentList() {
        Trackr<Student> sample = new Trackr<>();
        for (Student sampleStudent : getSampleStudents()) {
            sample.addObject(sampleStudent);
        }
        return sample;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
