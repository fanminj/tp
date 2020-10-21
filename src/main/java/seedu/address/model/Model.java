package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;

/**
 * The API of the Model component.
 */
public interface Model<T extends Showable<T>> {
    /** {@code Predicate} that always evaluate to true */
    Predicate<?> PREDICATE_SHOW_ALL_ITEMS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' Trackr file path.
     */
    Path getTrackrFilePath();

    /**
     * Sets the user prefs' Trackr file path.
     */
    void setTrackrFilePath(Path trackrFilePath);

    // showable operations

    /**
     * Replaces Trackr data with the data in {@code showableList}.
     */
    void setShowableList(ReadOnlyTrackr<T> showableList);

    /** Returns the Showable Trackr */
    ReadOnlyTrackr<T> getShowableList();

    /**
     * Returns true if a showable with the same identity as {@code showable} exists in trackr.
     */
    boolean hasShowable(T showable);

    /**
     * Deletes the given showable.
     * The showable must exist in trackr.
     */
    void deleteShowable(T target);

    /**
     * Adds the given showable.
     * {@code showable} must not already exist in trackr.
     */
    void addShowable(T showable);

    /**
     * Replaces the given showable {@code target} with {@code editedShowable}.
     * {@code target} must exist in trackr.
     * The showable identity of {@code editedShowable} must not be the same as another existing showable in trackr.
     */
    void setShowable(T target, T editedStudent);

    /** Returns an unmodifiable view of the filtered showable list */
    ObservableList<T> getFilteredShowableList();

    /**
     * Updates the filter of the filtered showable list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredShowableList(Predicate<T> predicate);
}
