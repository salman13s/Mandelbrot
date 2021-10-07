package oop;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.*;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.image.PixelWriter;
import javafx.scene.layout.Pane;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;

public class Controller {

    public AnchorPane anchorPane;
    public ColorPicker colorPicker;
    public Pane canvasPane;
    public TextField minReal;
    public TextField minImaginary;
    public TextField maxReal;
    public TextField maxImaginary;
    public ResizableCanvas canvas;
    public TextField iterations;
    public MenuItem closeButton;
    public ColorPicker backgroundPicker;

    Model model1 = new Model();

    /**
     * intialisiert die hoehe und die Tiefe vom Canvas(graph)
     */
    public void initialize() {
        canvas.widthProperty().bind(canvasPane.widthProperty());
        canvas.heightProperty().bind(canvasPane.heightProperty());
    }

    /**
     * speichert den File als bild datei
     * @param actionEvent
     */
    public void saveClicked(ActionEvent actionEvent) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image File", "*.png", "*.jpg"));
        File file = fileChooser.showSaveDialog(anchorPane.getScene().getWindow());
        if (file == null) {
            return;
        }

        try {
            WritableImage wi = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
            canvas.snapshot(null, wi);
            ImageIO.write(SwingFXUtils.fromFXImage(wi, null), "png", file);

        } catch (IOException ioex) {
            ioex.printStackTrace();
        }
    }

    /**
     * run methode und eine Eingabe methode
     * @param actionEvent
     */
    public void runClicked(ActionEvent actionEvent) {

        try {


            HashSet<Pixel> pixels;
            Complex min = new Complex(Double.parseDouble(minReal.getText()), Double.parseDouble(minImaginary.getText()));
            Complex max = new Complex(Double.parseDouble(maxReal.getText()), Double.parseDouble(maxImaginary.getText()));
            pixels = model1.paintAndCalculateMandelBrot(min, max, canvas.getWidth(), canvas.getHeight(), Integer.valueOf(iterations.getText()));
            PixelWriter pixelWriter = canvas.getGraphicsContext2D().getPixelWriter();

            for (Pixel pixel : pixels) {
                int cPRed = (int)(255*colorPicker.getValue().getRed());
                int cPGreen = (int)(255*colorPicker.getValue().getGreen());
                int cPBlue = (int)(255*colorPicker.getValue().getBlue());
                int bPRed = (int)(255*backgroundPicker.getValue().getBlue());
                int bPGreen = (int)(255*backgroundPicker.getValue().getGreen());
                int bPBlue = (int)(255*backgroundPicker.getValue().getBlue());

                int newRed = (int)(bPRed + pixel.getQuotient()*(cPRed-bPRed));
                int newGreen = (int)(bPGreen + pixel.getQuotient()*(cPGreen-bPGreen));
                int newBlue = (int)(bPBlue + pixel.getQuotient()*(cPBlue-bPBlue));

                javafx.scene.paint.Color c;
                c = Color.rgb(newRed, newGreen, newBlue);
                pixelWriter.setColor(pixel.getX(), pixel.getY(), c);
            }
        } catch (NumberFormatException e){
            Alert alter = new Alert(Alert.AlertType.ERROR, "", ButtonType.CLOSE);
            Toolkit.getDefaultToolkit().beep();
            alter.setContentText("geben Sie bitte eine valide Zahl ein");
            alter.showAndWait();
            return;
        }
    }

    /**
     * schliesst das Programm
     * @param actionEvent
     */
    public void closeClicked(ActionEvent actionEvent) {

        ((Stage) (anchorPane.getScene().getWindow())).close();
    }

    /**
     * setzt das canvas auf weiss
     * @param actionEvent
     */
    public void newClicked(ActionEvent actionEvent) {

        canvas.getGraphicsContext2D().setFill(Color.WHITE);
        canvas.getGraphicsContext2D().fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
}
