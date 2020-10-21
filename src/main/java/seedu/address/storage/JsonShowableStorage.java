package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.Module;
import seedu.address.model.ReadOnlyTrackr;
import seedu.address.model.Showable;

/**
 * A class to access AddressBook data stored as a json file on the hard disk.
 */
public class JsonShowableStorage<T extends Showable<T>> implements ShowableStorage<T> {

    private static final Logger logger = LogsCenter.getLogger(JsonAddressBookStorage.class);

    private Path filePath;

    public JsonShowableStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getTrackrFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyTrackr<T>> readTrackr() throws DataConversionException {
        return readTrackr(filePath);
    }

    /**
     * Similar to {@link #readTrackr()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyTrackr<T>> readTrackr(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableShowable<T>> jsonShowable = JsonUtil.readJsonFile(
                filePath, JsonSerializableShowable<T>.class);
        if (!jsonShowable.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonShowable.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveTrackr(ReadOnlyTrackr<T> trackr) throws IOException {
        saveTrackr(trackr, filePath);
    }

    /**
     * Similar to {@link #saveTrackr(ReadOnlyTrackr)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveTrackr(ReadOnlyTrackr<T> trackr, Path filePath) throws IOException {
        requireNonNull(trackr);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableShowable<>(trackr), filePath);
    }

}
