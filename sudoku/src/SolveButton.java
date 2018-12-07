import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 * @author      Alexander Goobar
 * @version     1.0
 */

public class SolveButton implements EventHandler<ActionEvent> {

    TextField[][] boxes;
    SudokuKiller solver;
    Alert alert;

    /**
     * A constructor for the eventhandler connected to the solve-button.
     * @param boxes textfield boxes that each contain one number between 0-9
     * @param solver the algoritm used to solve the sudoku
     * @param alert the relevant dialog object
     */
    public SolveButton(TextField[][] boxes, SudokuKiller solver, Alert alert) {
        this.boxes = boxes;
        this.solver = solver;
        this.alert = alert;
    }

    /**
     * Handles the event triggered by the button.
     * @param event the event
     */

    @Override
    public void handle(ActionEvent event) {
        boolean onlySingleNumbers = true;
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                TextField tf = boxes[r][c];
                int value = 1;
                try {
                    if (!tf.getText().equals("")) {
                        value = Integer.parseInt(tf.getText());
                    }
                } catch (NumberFormatException ex) {
                    onlySingleNumbers = false;
                    alert.setContentText("Endast siffror (1-9) är tillåtna.");
                    alert.showAndWait();
                    break;
                }
                if (value < 1 || value > 9) {
                    onlySingleNumbers = false;
                    alert.setContentText("Sifforna måste vara mellan 1 och 9.");
                    alert.showAndWait();
                    break;
                }
            }
        }
        if (onlySingleNumbers) {
            for (int r = 0; r < 9; r++) {
                for (int c = 0; c < 9; c++) {
                    if (boxes[r][c].getText().equals("")) {
                        solver.setValue(r, c, 0);
                    } else {
                        int value = Integer.parseInt(boxes[r][c].getText());
                        solver.setValue(r, c, value);
                    }
                }
            }
            if (solver.userInputCheck()) {
                if (solver.solve()) {
                    for (int r = 0; r < 9; r++) {
                        for (int c = 0; c < 9; c++) {
                            int solutionValue = solver.getValue(r, c);
                            boxes[r][c].setText(Integer.toString(solutionValue));
                        }
                    }
                    alert.setContentText("Lösning hittad!");
                    alert.showAndWait();
                } else {
                    alert.setContentText("Ingen lösning finns.");
                    alert.showAndWait();
                }

            } else {
                alert.setContentText("De inmatade värdena följer inte reglerna.");
                alert.showAndWait();
            }
        }
    }
}