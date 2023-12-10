    import javafx.application.Application;
    import javafx.application.Platform;
    import javafx.scene.Scene;
    import javafx.scene.chart.LineChart;
    import javafx.scene.chart.NumberAxis;
    import javafx.scene.chart.XYChart;
    import javafx.stage.FileChooser;
    import javafx.stage.Stage;

    import java.io.BufferedReader;
    import java.io.File;
    import java.io.FileReader;

    public class SignalPlotterUI extends Application {

        @Override
        public void start(Stage primaryStage) {
            primaryStage.setTitle("Signal Plotter");

            File selectedFile = showFileChooser(primaryStage);

            if (selectedFile != null) {
                try {
                    SignalPlotterController controller = new SignalPlotterController();
                    controller.initializeChart(selectedFile);

                    Scene scene = new Scene(controller.getChart(), 800, 600);
                    primaryStage.setScene(scene);

                    primaryStage.show();
                } catch (Exception e) {
                    handleException(e);
                }
            } else {
                Platform.exit();
            }
        }

        private File showFileChooser(Stage primaryStage) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
            return fileChooser.showOpenDialog(primaryStage);
        }

        private void handleException(Exception e) {
            showErrorDialog("Произошла ошибка: " + e.getMessage());
            Platform.exit();
        }

        private void showErrorDialog(String errorMessage) {
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText(errorMessage);
            alert.showAndWait();
        }
    }
