package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.Module;
import seedu.address.model.ReadOnlyTrackr;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.Showable;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Student;

/**
 * API of the Storage component
 */
public interface Storage<T extends Showable<T>> extends ShowableStorage<T>, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getTrackrFilePath();

    @Override
    Optional<ReadOnlyTrackr<T>> readTrackr() throws DataConversionException, IOException;

    @Override
    void saveTrackr(ReadOnlyTrackr<T> trackr) throws IOException;

}
