package Classes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {

    private static Stage pStage;
    private static Stage AboutNotepadStage = null;
    private static Parent rootNode;

    @Override
    public void init() throws Exception {
        System.out.println("Notepad Dark Started.");
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        setPrimaryStage(primaryStage);
        pStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/FXMLFiles/MainGUI.fxml"));
        primaryStage.setTitle("Notepad Dark");
        primaryStage.setScene(new Scene(root));
        setRootNode(root);
        rootNode = root;
        root.getStylesheets().add("/CSSFiles/main-gui.css");
        primaryStage.show();

        CreateAboutNotepadStage();
    }

    public static Stage getPrimaryStage() {
        return pStage;
    }

    private void setPrimaryStage(Stage pStage) {
        Main.pStage = pStage;
    }

    public static Parent getRootNode() {
        return rootNode;
    }

    private void setRootNode(Parent rootNode) {
        Main.rootNode = rootNode;
    }

    public static Stage getAboutNotepadStage()  {
        return AboutNotepadStage;
    }

    public void CreateAboutNotepadStage() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/FXMLFiles/AboutNPGUI.fxml"));
        AboutNotepadStage = new Stage();
        AboutNotepadStage.setTitle("About Notepad Dark");
        AboutNotepadStage.setAlwaysOnTop(true);
        AboutNotepadStage.setResizable(false);
        AboutNotepadStage.initStyle(StageStyle.UTILITY);
        AboutNotepadStage.initModality(Modality.APPLICATION_MODAL);
        AboutNotepadStage.setScene(new Scene(root));
        AboutNotepadStage.show();
    }

    @Override
    public void stop() throws Exception {
        System.out.println("Notepad Dark Ended.");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
