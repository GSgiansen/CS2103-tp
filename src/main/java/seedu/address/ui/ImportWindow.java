package seedu.address.ui;

import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
/**
 * Controller for a importWindow page, used when user wishes to import cards
 */
public class ImportWindow extends UiPart<Stage> {

    public static final String WARNINGDATA = "Warning! Importing data will close app for best experience";
    private static final Logger logger = LogsCenter.getLogger(ImportWindow.class);
    private static final String FXML = "ImportWindow.fxml";

    @FXML
    private Button importButton;
    @FXML
    private Label warningMessage;
    @FXML
    private TextArea inputTextArea;
    @FXML
    private Stage stage;
    @FXML
    private Stage primaryStage;
    /**
     * Creates a new ImportWindow.
     *
     * @param root Stage to use as the root of the ImportWindow.
     */
    public ImportWindow(Stage root, Stage primaryStage) {
        super(FXML, root);
        this.stage = root;
        this.primaryStage = primaryStage;
        warningMessage.setText(WARNINGDATA);
    }

    /**
     * Creates a new ImportWindow.
     */
    public ImportWindow(Stage primaryStage) {
        this(new Stage(), primaryStage);
    }

    /**
     * Shows the help window.
     * @throws IllegalStateException
     *     <ul>
     *         <li>
     *             if this method is called on a thread other than the JavaFX Application Thread.
     *         </li>
     *         <li>
     *             if this method is called during animation or layout processing.
     *         </li>
     *         <li>
     *             if this method is called on the primary stage.
     *         </li>
     *         <li>
     *             if {@code dialogStage} is already showing.
     *         </li>
     *     </ul>
     */
    public void show() {
        logger.fine("Showing import page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }
    @FXML
    private void importData() {
        String contentToWrite = inputTextArea.getText();

        // Specify the path to the data/deck.json file
        String filePath = "data/deck.json";

        try (FileWriter writer = new FileWriter(filePath)) {
            verifyImportData(contentToWrite);
            writer.write(contentToWrite);
            writer.close();
            stage.close();
            primaryStage.close();
        } catch (IOException e) {
            logger.info("Error writing content to deck.json: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Needs to have the cards as starting input in json file
     * @return Boolean based on if the input has
     */

    private boolean verifyImportData(String inputData) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Parse the JSON string
            JsonNode jsonNode = objectMapper.readTree(inputData);
            return jsonNode.get("cards").isArray() && jsonNode.has("cards");

        } catch (Exception e) {
            return false;
        }
    }
}
