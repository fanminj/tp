package seedu.address.model;

import javafx.collections.ObservableList;

/**
 * Unmodifiable view of Track.
 * @param <T> A class that implements {@code Showable}.
 */
public interface ReadOnlyTrackr<T extends Showable<T>> {

    /**
     * Returns an unmodifiable view of the {@code Showable} objects list.
     * This list will not contain any duplicate objects.
     */
    ObservableList<T> getList();
}
