package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import javafx.scene.control.Label;
import javafx.stage.Stage;

@ExtendWith(ApplicationExtension.class)
public class ExportWindowTest {

    // @@author GSgiansen-reused
    // Adapted code from 2103 23/24 forum raised by team T13-2, as well as T16-3

    // Sources: https://github.com/nus-cs2103-AY2324S1/forum/issues/284
    // https://ay2324s1-cs2103-t16-3.github.io/tp/DeveloperGuide.html

    private ExportWindow exportWindow;
    @Start
    public void start(Stage stage) {
        exportWindow = new ExportWindow(stage);
        stage.show();
    }
    @Test
    public void constructor_setTest(FxRobot robot) {
        var message = robot.lookup("#exportMessage").tryQuery();
        assertTrue(message.isPresent());
        assertTrue(message.get() instanceof Label);
        var label = (Label) message.get();
        assertEquals(ExportWindow.getExportData(), label.getText());
    }

    @Test
    public void show_exportWindow(FxRobot robot) {
        assertTrue(exportWindow.isShowing());
    }

}

