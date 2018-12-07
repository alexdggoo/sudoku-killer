import java.util.Random;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

/**
 * @author      Alexander Goobar
 * @version     1.0
 */

public class BoardView extends Application {


    /**
     * Defines te UI and acts as a constuctor for the game.
     * @param primaryStage the stage used
     */
    @Override
    public void start(Stage primaryStage) {

        TilePane tile = new TilePane();
        BorderPane root = new BorderPane();
        HBox hbox = new HBox();
        SudokuKiller solver = new SudokuKiller();

        TextField[][] boxes = new TextField[9][9];
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {

                Random rand = new Random();
                int rand1 = rand.nextInt(9) + 1;

                TextField tf = new TextField();
                tf.setPrefHeight(45);
                tf.setPrefWidth(45);
                tf.setAlignment(Pos.CENTER);
                tf.setText("" + rand1 + "");

                if (((x < 3 || x > 5) && y < 3) || ((x > 2 && x < 6) && (y > 2 && y < 6))
                        || ((x < 3 || x > 5) && y > 5)) {
                    tf.setStyle("-fx-background-color: orange;");
                }
                boxes[y][x] = tf;
                tile.getChildren().add(tf);
            }
        }
        tile.setHgap(5);
        tile.setVgap(5);
        tile.setMinWidth(450);
        tile.setMaxWidth(450);

        Button solve = new Button();
        Button clear = new Button();

        solve.setText("Solve");
        clear.setText("Clear");

        hbox.getChildren().add(solve);
        hbox.getChildren().add(clear);
        hbox.setPadding(new Insets(10, 10, 10, 10));
        hbox.setSpacing(10);

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(null);
        alert.setHeaderText(null);
        solve.setOnAction(new SolveButton(boxes, solver, alert));

        clear.setOnAction(e -> {
            for (Node n : tile.getChildren()) {
                TextField tf = (TextField) n;
                tf.setText("");

            }
        });

        root.setTop(tile);
        root.setBottom(hbox);
        Scene scene = new Scene(root, 450, 490);
        primaryStage.setTitle("Sudoku");
        primaryStage.setScene(scene);
        //primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * The main method.
     * @param args starting arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}