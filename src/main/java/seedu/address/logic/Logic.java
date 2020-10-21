package seedu.address.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.Module;
import seedu.address.model.ReadOnlyTrackr;
import seedu.address.model.Showable;
import seedu.address.model.person.Student;

/**
 * API of the Logic component
 */
public interface Logic<T extends Showable<T>> {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the showable list.
     *
     * @see Model#getShowableList()
     */
    ReadOnlyTrackr<T> getShowableList();

    /** Returns an unmodifiable view of the filtered list of showables */
    ObservableList<T> getFilteredShowableList();

    /**
     * Returns the user prefs' trackr file path.
     */
    Path getTrackrFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
