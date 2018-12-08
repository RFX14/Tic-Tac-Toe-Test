import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by coffeemate on 8/28/17.
 */
public class Main extends Application implements EventHandler <ActionEvent> {
    Button tLeft, tMid, tRight, mLeft, mMid, mRight, bLeft, bMid, bRight;
    Stage window;
    VBox gameLayout;
    Scene gameScene;
    int xWindow = 700;
    int yWindow = 600;
    int turn = 1; //0 is player one, 1 is player two
    String piece = "X";
    Label status;
    String[][] board = new String[3][3];
    String[] row1 = new String[3];
    String[] row2 = new String[3];
    String[] row3 = new String[3];
    String [] col1 = new String[3];
    String [] col2 = new String[3];
    String [] col3 = new String[3];
    String[] diag1 = new String[3];
    String[] diag2 = new String[3];

    @Override
    public void start(Stage primaryStage) throws Exception {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = "-";
            }
        }

        window = primaryStage;
        window.setOnCloseRequest(e -> Platform.exit());

        status = new Label("Player 1's Turn");
        status.setTranslateX(500);
        status.setTranslateY(-400);

        //Buttons and their actions
        tLeft = new Button(board[0][0]);
            tLeft.setOnAction(this);
            tLeft.setPrefSize(100, 50);
        tMid = new Button(board[0][1]);
            tMid.setOnAction(this);
            tMid.setPrefSize(100, 50);
            tMid.setTranslateX(100);
            tMid.setTranslateY(-50);
        tRight = new Button(board[0][2]);
            tRight.setOnAction(this);
            tRight.setPrefSize(100, 50);
            tRight.setTranslateX(200);
            tRight.setTranslateY(-100);
        mLeft = new Button(board[1][0]);
            mLeft.setOnAction(this);
            mLeft.setPrefSize(100, 50);
            mLeft.setTranslateY(-100);
        mMid = new Button(board[1][1]);
            mMid.setOnAction(this);
            mMid.setPrefSize(100, 50);
            mMid.setTranslateX(100);
            mMid.setTranslateY(-150);
        mRight = new Button(board[1][2]);
            mRight.setOnAction(this);
            mRight.setPrefSize(100, 50);
            mRight.setTranslateX(200);
            mRight.setTranslateY(-200);
        bLeft = new Button(board[2][0]);
            bLeft.setOnAction(this);
            bLeft.setPrefSize(100, 50);
            bLeft.setTranslateY(-200);
        bMid = new Button(board[2][1]);
            bMid.setOnAction(this);
            bMid.setPrefSize(100, 50);
            bMid.setTranslateX(100);
            bMid.setTranslateY(-250);
        bRight = new Button(board[2][2]);
            bRight.setOnAction(this);
            bRight.setPrefSize(100, 50);
            bRight.setTranslateX(200);
            bRight.setTranslateY(-300);

        gameLayout = new VBox();
        gameLayout.setPadding(new Insets(50));
        gameLayout.getChildren().addAll(tLeft, tMid, tRight, mLeft, mMid, mRight, bLeft, bMid, bRight, status);

        gameScene = new Scene(gameLayout, xWindow, yWindow);

        window.setScene(gameScene);
        window.show();
    }

    public void handle(ActionEvent e) {
        if (turn == 1) {
            piece = "X";
            turn++;
        } else if (turn == 2) {
            piece = "O";
            turn--;
        }

        status.setText("Player " + turn +"'s Turn");

        if (e.getSource() == tLeft) {
            board[0][0] = piece;
            tLeft.setText(board[0][0]);
        } else if (e.getSource() == tMid) {
            board[0][1] = piece;
            tMid.setText(board[0][1]);
        } else if(e.getSource() == tRight) {
            board[0][2] = piece;
            tRight.setText(board[0][2]);
        } else if(e.getSource() == mLeft) {
            board[1][0] = piece;
            mLeft.setText(board[1][0]);
        } else if(e.getSource() == mMid) {
            board[1][1] = piece;
            mMid.setText(board[1][1]);
        } else if(e.getSource() == mRight) {
            board[1][2] = piece;
            mRight.setText(board[1][2]);
        } else if(e.getSource() == bLeft) {
            board[2][0] = piece;
            bLeft.setText(board[2][0]);
        } else if(e.getSource() == bMid) {
            board[2][1] = piece;
            bMid.setText(board[2][1]);
        } else if(e.getSource() == bRight) {
            board[2][2] = piece;
            bRight.setText(board[2][2]);
        }

        checkRows();
        checkCol();
        checkDiag();
    }

    public void checkRows() {
        for(int i = 0; i < row1.length; i++) {
            row1[i] = board[0][i];
            row2[i] = board[1][i];
            row3[i] = board[2][i];
        }
            if(row1[0] == row1[1] && row1[1] == row1[2] && !row1[1].equals("-")) {
                status.setText(row1[1] + " WINS!");
            } else if(row2[0] == row2[1] && row2[1] == row2[2] && !row2[1].equals("-")) {
                status.setText(row2[1] + " WINS!");
            } else if(row3[0] == row3[1] && row2[1] == row3[2] && !row3[1].equals("-")) {
                status.setText(row3[1] + " WINS!");
            }

    }

    public void checkCol() {
        for(int i = 0; i < col1.length; i++) {
            col1[i] = board[i][0];
            col2[i] = board[i][1];
            col3[i] = board[i][2];

        }

        if(col1[0] == col1[1] && col1[1] == col1[2] && !col1[1].equals("-")) {
            status.setText(col1[1] + " WINS!");
        } else if(col2[0] == col2[1] && col2[1] == col2[2] && !col2[1].equals("-")) {
            status.setText(col2[1] + " WINS!");
        } else if(col3[0] == col3[1] && col3[1] == row3[2] && !col3[1].equals("-")) {
            status.setText(col3[1] + " WINS!");
        }

    }

    public void checkDiag() {
        //diag1 TOP RIGHT to BOTTOM LEFT
        //diag2 TOP LEFT to BOTTOM RIGHT
        for(int i = 0; i < 3; i++) {
            if(i == 0) {
                diag1[i] = board[i][2];
                diag2[i] = board[i][i];
            } else if(i == 1) {
                diag1[i] = board[i][i];
                diag2[i] = diag1[i];
            } else if(i == 2) {
                diag1[i] = board[i][0];
                diag2[i] = board[i][2];
            }

            if(diag1[0] == diag1[1] && diag1[2] == diag1[1]) {
                status.setText(diag1[1] + " WINS!");
            } else if(diag2[0] == diag2[1] && diag2[2] == diag2[1]) {
                status.setText(diag2[1] + " WINS!");
            }
        }

    }
}
