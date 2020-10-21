package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyTrackr;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.Showable;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of Trackr data in local storage.
 */
public class StorageManager<T extends Showable<T>> implements Storage<T> {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private ShowableStorage<T> showableStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given storages.
     */
    public StorageManager(ShowableStorage<T> showableStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.showableStorage = showableStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }

    // ================ Showable methods ==============================

    @Override
    public Path getTrackrFilePath() {
        return showableStorage.getTrackrFilePath();
    }

    @Override
    public Optional<ReadOnlyTrackr<T>> readTrackr() throws DataConversionException, IOException {
        return readTrackr(showableStorage.getTrackrFilePath());
    }

    @Override
    public Optional<ReadOnlyTrackr<T>> readTrackr(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return showableStorage.readTrackr(filePath);
    }

    @Override
    public void saveTrackr(ReadOnlyTrackr<T> trackr) throws IOException {
        saveTrackr(trackr, showableStorage.getTrackrFilePath());
    }

    @Override
    public void saveTrackr(ReadOnlyTrackr<T> trackr, Path filePath) throws IOException {
        logger.fine("Attempting to write to student data file: " + filePath);
        showableStorage.saveTrackr(trackr, filePath);
    }

}
