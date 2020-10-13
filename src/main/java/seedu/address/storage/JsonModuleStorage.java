package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyModuleList;

import seedu.address.commons.exceptions.DataConversionException;

public class JsonModuleStorage implements ModuleStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonModuleStorage.class);

    private Path filePath;

    public JsonModuleStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getModuleFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyModuleList> readModuleList() throws DataConversionException {
        return readModuleList(filePath);
    }


    public Optional<ReadOnlyModuleList> readModuleList(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableModuleList> jsonModuleList = JsonUtil.readJsonFile(
                filePath, JsonSerializableModuleList.class);
        if (!jsonModuleList.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonModuleList.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveModuleList(ReadOnlyModuleList moduleList) throws IOException {
        saveModuleList(moduleList, filePath);
    }

    public void saveModuleList(ReadOnlyModuleList moduleList, Path filePath) throws IOException {
        requireNonNull(moduleList);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableModuleList(moduleList), filePath);
    }

}
