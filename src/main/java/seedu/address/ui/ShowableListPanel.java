package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.Module;
import seedu.address.model.Showable;
import seedu.address.model.person.Student;

public class ShowableListPanel<T extends Showable<T>> extends UiPart<Region> {
    private static final String FXML = "ShowableListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ShowableListPanel.class);

    @FXML
    private ListView<T> showableListView;

    /**
     * Creates a {@code ShowableListPanel} with the given {@code ObservableList}.
     */
    public ShowableListPanel(ObservableList<T> showableList) {
        super(FXML);
        showableListView.setItems(showableList);
        showableListView.setCellFactory(listView -> new ShowableListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Showable} using a {@code ShowableCard}.
     */
    class ShowableListViewCell extends ListCell<T> {
        @Override
        protected void updateItem(T showable, boolean empty) {
            super.updateItem(showable, empty);

            if (empty || showable == null) {
                setGraphic(null);
                setText(null);
            } else {
                if (showable.getType().equals("student")) {
                    Student student = (Student) showable;
                    setGraphic(new StudentCard(student, getIndex() + 1).getRoot());
                } else if (showable.getType().equals("module")) {
                    Module module = (Module) showable;
                    setGraphic(new ModuleCard(module, getIndex() + 1).getRoot());
                }

            }
        }
    }
}
