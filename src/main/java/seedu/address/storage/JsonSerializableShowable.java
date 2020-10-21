package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyTrackr;
import seedu.address.model.Showable;
import seedu.address.model.Trackr;
import seedu.address.model.person.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * An immutable Trackr that is serializable to JSON format.
 * @param <T> A class that implements {@code Showable}.
 */
@JsonRootName(value = "trackr")
public class JsonSerializableShowable<T extends Showable<T>> {
    public static final String MESSAGE_DUPLICATE_ITEM = "Item list contains duplicate item(s).";

    private final List<JsonAdaptedShowable<T>> showables = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableShowable} with the given persons.
     */
    @JsonCreator
    public JsonSerializableShowable(@JsonProperty("showables") List<JsonAdaptedShowable<T>> showables) {
        this.showables.addAll(showables);
    }

    /**
     * Converts a given {@code ReadOnlyTrackr} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableShowable}.
     */
    public JsonSerializableShowable(ReadOnlyTrackr<T> source) {
        showables.addAll(source.getList().stream()
                .map(JsonAdaptedShowable<T>::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code Trackr} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Trackr<T> toModelType() throws IllegalValueException {
        Trackr<T> trackr = new Trackr<>();
        for (JsonAdaptedShowable<T> jsonAdaptedShowable : showables) {
            T showable = jsonAdaptedShowable.toModelType();
            if (trackr.hasObject(showable)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ITEM);
            }
            trackr.addObject(showable);
        }
        return trackr;
    }
}
