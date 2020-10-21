package seedu.address.storage;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyTrackr;
import seedu.address.model.Showable;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

public interface ShowableStorage<T extends Showable<T>> {

    /**
     * Returns the file path of the data file.
     */
    Path getTrackrFilePath();

    /**
     * Returns Trackr data as a {@link ReadOnlyTrackr}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyTrackr<T>> readTrackr() throws DataConversionException, IOException;

    /**
     * @see #getTrackrFilePath()
     */
    Optional<ReadOnlyTrackr<T>> readTrackr(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyTrackr} to the storage.
     * @param trackr cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveTrackr(ReadOnlyTrackr<T> trackr) throws IOException;

    /**
     * @see #saveTrackr(ReadOnlyTrackr)
     */
    void saveTrackr(ReadOnlyTrackr<T> trackr, Path filePath) throws IOException;
}
