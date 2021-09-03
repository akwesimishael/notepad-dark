package Classes;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class LoadFileLightController {

    private static Stage clearPageStage = null;
    private static Stage replaceStage = null;
    private static Stage fontStage = null;
    private static Stage stickyStage = null;
    private static Stage saveWorkStage = null;
    private static Stage fileLoadStage = null;
    String errorSound = "/Users/akwesimishael/Documents/Java Files/Intellij Projects/notepad-dark/src/Media/windows-10-error-sound.mp3";
    @FXML
    private TextArea textArea;
    @FXML
    private CheckMenuItem fiftyPercent;
    @FXML
    private CheckMenuItem hundredPercent;
    @FXML
    private CheckMenuItem hundredTwentyFivePercent;
    @FXML
    private CheckMenuItem hundredFiftyPercent;
    @FXML
    private TitledPane statusBar;
    @FXML
    private StackPane mainStack;
    @FXML
    private MenuBar menuBar;
    @FXML
    private BorderPane mainBorderPane;


    public static Stage getSaveWorkStage() {
        return saveWorkStage;
    }

    public static Stage getClearPageStage()    {
        return clearPageStage;
    }

    public static Stage getReplaceStage()    {
        return replaceStage;
    }

    public static Stage getFontStage()    {
        return fontStage;
    }

    public static Stage getStickyStage() {
        return stickyStage;
    }

    public TextArea getTextArea() {
        return textArea;
    }

    public void setOnAction(ActionEvent actionEvent) {
        Main.getAboutNotepadStage().show();
    }

    public void openNewWindow(ActionEvent actionEvent) throws IOException {
        if (!textArea.getText().equals("")) {
            AudioClip audioClip = new AudioClip(Paths.get(errorSound).toUri().toString());
            audioClip.play();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/FXMLFiles/SavePagePrompt.fxml"));
            Parent root = loader.load();
            SaveWorkController saveWorkController = loader.getController();
            saveWorkController.primaryTextArea = textArea;

            saveWorkStage = new Stage();
            saveWorkStage.setTitle("Save Page");
            saveWorkStage.setResizable(false);
            saveWorkStage.initModality(Modality.APPLICATION_MODAL);
            saveWorkStage.initStyle(StageStyle.UTILITY);
            saveWorkStage.setAlwaysOnTop(true);
            saveWorkStage.setScene(new Scene(root));
            saveWorkStage.show();
        } else  {
            Main.getPrimaryStage().close();
            textArea.setText("");
            Main.getPrimaryStage().show();
        }
    }

    public void saveTextToFile(ActionEvent actionEvent) {
        if (!textArea.getText().equals("")) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File("/Users/akwesimishael/Desktop"));
            fileChooser.setInitialFileName("Document 1");
            fileChooser.setTitle("Save Document");
            File file = fileChooser.showSaveDialog(null);

            try {
                if (file != null) {
                    SaveFile(textArea.getText(), file);
                }
            } catch (NullPointerException ne) {
                System.out.println(ne);
            }
        } else {
            AudioClip audioClip = new AudioClip(Paths.get(errorSound).toUri().toString());
            audioClip.play();
        }
    }

    public void SaveFile(String content, File file) {
        try {
            FileWriter fileWriter;
            fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public void handleCloseButtonAction(ActionEvent event) {
        Main.getPrimaryStage().close();
    }

    public void cutOnAction(ActionEvent actionEvent) {
        textArea.cut();
    }

    public void copyOnAction(ActionEvent actionEvent) {
        textArea.copy();
    }

    public void pasteOnAction(ActionEvent actionEvent) {
        textArea.paste();
    }

    public void clearOnAction(ActionEvent actionEvent) throws IOException {
        if (!textArea.getText().equals("")) {
            AudioClip audioClip = new AudioClip(Paths.get(errorSound).toUri().toString());
            audioClip.play();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/FXMLFiles/ClearPage.fxml"));
            Parent root = loader.load();
            ClearPageController clearPageController = loader.getController();
            clearPageController.primaryTextArea = textArea;

            clearPageStage = new Stage();
            clearPageStage.setTitle("Clear Page");
            clearPageStage.setResizable(false);
            clearPageStage.initModality(Modality.APPLICATION_MODAL);
            clearPageStage.initStyle(StageStyle.UTILITY);
            clearPageStage.setAlwaysOnTop(true);
            clearPageStage.setScene(new Scene(root));
            clearPageStage.show();
        } else {
            AudioClip audioClip = new AudioClip(Paths.get("/Users/akwesimishael/Documents/Java Files/Intellij Projects/notepad-dark/src/Media/windows-10-error-sound.mp3").toUri().toString());
            audioClip.play();
        }
    }

    public void replaceOnAction(ActionEvent actionEvent) throws IOException {
        if (!textArea.getText().equals("")) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/FXMLFiles/ReplaceWindow.fxml"));
            Parent root = loader.load();
            ReplaceController replaceController = loader.getController();
            replaceController.primaryTextArea = textArea;

            replaceStage = new Stage();
            replaceStage.setScene(new Scene(root));
            replaceStage.setTitle("Replace");
            replaceStage.setResizable(false);
            replaceStage.initStyle(StageStyle.UTILITY);
            replaceStage.initModality(Modality.APPLICATION_MODAL);
            replaceStage.show();
        } else {
            AudioClip audioClip = new AudioClip(Paths.get(errorSound).toUri().toString());
            audioClip.play();
        }
    }

    public void undoOnAction(ActionEvent actionEvent) {
        textArea.undo();
    }

    public void redoOnAction(ActionEvent actionEvent) {
        textArea.redo();
    }

    public void wrapOnAction(ActionEvent actionEvent) {
        textArea.setWrapText(!textArea.isWrapText());
    }

    public void fiftyPercentZoom(ActionEvent actionEvent) {
        textArea.setStyle("-fx-font-size: 10px;");
        fiftyPercent.setSelected(true);
        hundredPercent.setSelected(false);
        hundredTwentyFivePercent.setSelected(false);
        hundredFiftyPercent.setSelected(false);
    }

    public void hundredPercentZoom(ActionEvent actionEvent) {
        textArea.setStyle("-fx-font-size: 14px;");
        fiftyPercent.setSelected(false);
        hundredPercent.setSelected(true);
        hundredTwentyFivePercent.setSelected(false);
        hundredFiftyPercent.setSelected(false);
    }

    public void hundredTwentyFiveZoom(ActionEvent actionEvent) {
        textArea.setStyle("-fx-font-size: 18px;");
        fiftyPercent.setSelected(false);
        hundredPercent.setSelected(false);
        hundredTwentyFivePercent.setSelected(true);
        hundredFiftyPercent.setSelected(false);
    }

    public void hundredFiftyZoom(ActionEvent actionEvent) {
        textArea.setStyle("-fx-font-size: 24px;");
        fiftyPercent.setSelected(false);
        hundredPercent.setSelected(false);
        hundredTwentyFivePercent.setSelected(false);
        hundredFiftyPercent.setSelected(true);
    }

    public void setStatusBarVisible(ActionEvent actionEvent) {
        statusBar.setVisible(!statusBar.isVisible());
    }

    public void fontBoxOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FXMLFiles/FontGUI.fxml"));
        Parent root = loader.load();
        FontController fc = loader.getController();
        fc.primaryTextArea = textArea;

        fontStage = new Stage();
        fontStage.setScene(new Scene(root));
        fontStage.setTitle("Font");
        fontStage.setResizable(false);
        fontStage.initStyle(StageStyle.UTILITY);
        fontStage.initModality(Modality.APPLICATION_MODAL);
        fontStage.show();
    }

    public void setDarkMode(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("ERROR");
        alert.setHeaderText("Error");
        alert.setContentText("Dark Mode Is Already Active.");
        alert.show();
    }

    public void setLightMode(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/FXMLFiles/LightGUI.fxml"));
        Scene scene = menuBar.getScene();
        root.translateYProperty().set(scene.getHeight());
        mainStack.getChildren().add(root);
        Timeline timeline = new Timeline();
        KeyValue keyValue = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), keyValue);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
        timeline.setOnFinished(event -> {
            mainStack.getChildren().remove(mainBorderPane);
        });
    }

    public void createStickyStage(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FXMLFiles/StickyGUI.fxml"));
        Parent root = loader.load();
        StickyController sc = loader.getController();
        sc.primaryTextArea = textArea;

        stickyStage = new Stage();
        stickyStage.setScene(new Scene(root));
        stickyStage.setTitle("Sticky Note");
        stickyStage.setResizable(false);
        stickyStage.initStyle(StageStyle.DECORATED);
        stickyStage.initModality(Modality.APPLICATION_MODAL);
        stickyStage.show();
    }

    public void openFile(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("/Users/akwesimishael/Desktop"));
        fileChooser.setTitle("Open Document");
        File file = fileChooser.showOpenDialog(null);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FXMLFiles/LoadFileLight.fxml"));
        Parent root = loader.load();
        LoadFileLightController lflc = loader.getController();

        fileLoadStage = new Stage();
        fileLoadStage.setScene(new Scene(root));


        try {
            Scanner readFile = new Scanner(file);
            if (file.isFile()) {
                while (readFile.hasNextLine())  {
                    String line = (readFile.nextLine() + "\n");
                    lflc.getTextArea().appendText(line);
                }
            }
            readFile.close();
        } catch (NullPointerException | FileNotFoundException ne) {
            System.out.println(ne);
        }

        fileLoadStage.setTitle(file.getName());
        fileLoadStage.show();
    }
}
