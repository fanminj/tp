package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager<T extends Showable<T>> implements Model<T> {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Trackr<T> showableList;
    private final FilteredList<T> filteredShowables;
    private final UserPrefs userPrefs;

    /**
     * Initializes a ModelManager with the given ReadOnlyTrackr and userPrefs.
     */
    public ModelManager(ReadOnlyTrackr<T> showableList, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(showableList, userPrefs);
        logger.fine("Initializing with data: " + showableList
                + " and user prefs: " + userPrefs);

        this.showableList = new Trackr<>(showableList);
        this.filteredShowables = new FilteredList<>(this.showableList.getList());
        this.userPrefs = new UserPrefs(userPrefs);
    }

    public ModelManager() {
        this(new Trackr<T>(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getTrackrFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setTrackrFilePath(Path trackrFilePath) {
        requireNonNull(trackrFilePath);
        userPrefs.setAddressBookFilePath(trackrFilePath);
    }

    //=========== showableList ================================================================================

    @Override
    public void setShowableList(ReadOnlyTrackr<T> showableList) {
        this.showableList.resetData(showableList);
    }

    @Override
    public ReadOnlyTrackr<T> getShowableList() {
        return showableList;
    }

    @Override
    public boolean hasShowable(T showable) {
        requireNonNull(showable);
        return showableList.hasObject(showable);
    }

    @Override
    public void deleteShowable(T target) {
        showableList.removeObject(target);
    }

    @Override
    public void addShowable(T showable) {
        showableList.addObject(showable);
        updateFilteredShowableList(unused -> true);
    }

    @Override
    public void setShowable(T target, T editedShowable) {
        requireAllNonNull(target, editedShowable);
        showableList.setObject(target, editedShowable);
    }

    //=========== Filtered Showable List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Showable}.
     */
    @Override
    public ObservableList<T> getFilteredShowableList() {
        return filteredShowables;
    }

    @Override
    public void updateFilteredShowableList(Predicate<T> predicate) {
        requireNonNull(predicate);
        filteredShowables.setPredicate(predicate);
    }

    //=======================================================================================================

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return showableList.equals(other.showableList)
                && userPrefs.equals(other.userPrefs)
                && filteredShowables.equals(other.filteredShowables);
    }

}
