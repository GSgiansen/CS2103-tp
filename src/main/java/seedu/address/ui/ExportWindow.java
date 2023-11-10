package seedu.address.ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class ExportWindow extends UiPart<Stage> {

    private static String exportData = "No Data";
    private static final Logger logger = LogsCenter.getLogger(ExportWindow.class);
    private static final String FXML = "ExportWindow.fxml";

    private String filePath = "data/deck.json";

    @FXML
    private Button copyButton;

    @FXML
    private Label exportMessage;

    /**
     * Creates a new ExportWindow.
     *
     * @param root Stage to use as the root of the ExportWindow.
     */
    public ExportWindow(Stage root) {
        super(FXML, root);
        exportMessage.setText("");
        autoReadDeckFileAndDisplayContent(filePath);
    }

    /**
     * Creates a new ExportWindow.
     */
    public ExportWindow() {
        this(new Stage());
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
        logger.fine("Showing export page about the application.");
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

    /**
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void copyUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(exportData);
        clipboard.setContent(url);
    }

    @FXML
    private void autoReadDeckFileAndDisplayContent(String filePath) {
        //specify the path to the data/deck.json file
        boolean verify = verifyFilePath(filePath);
        File selectedFile = new File(filePath);
        if (selectedFile.exists()) {
            //check if the selected file is data/deck.json
            if (selectedFile.getName().equals("deck.json") && verify) {
                try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                    StringBuilder content = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        content.append(line).append("\n");
                    }
                    exportMessage.setText(content.toString());
                    exportData = content.toString();
                } catch (IOException e) {
                    exportMessage.setText("Error reading the file: " + e.getMessage());
                }
            } else {
                exportMessage.setText("Selected file is not data/deck.json.");
            }
        } else {
            exportMessage.setText("The data/deck.json file does not exist.");
        }
    }

    /**
     * Checks if file path for deck.json works
     * @param filePath
     * @return boolean if filepath is present
     */

    public static boolean verifyFilePath(String filePath) {
        File selectedFile = new File(filePath);
        return selectedFile.exists() && selectedFile.getName().equals("deck.json");
    }
}
