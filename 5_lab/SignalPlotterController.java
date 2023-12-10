import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

public class SignalPlotterController {

    private LineChart<Number, Number> chart;

    public SignalPlotterController() {
        chart = new LineChart<>(new NumberAxis(), new NumberAxis());
        chart.setTitle("Построенный сигнал:");
    }

    public LineChart<Number, Number> getChart() {
        return chart;
    }

    public void initializeChart(File selectedFile) throws Exception {
        if (!selectedFile.getName().toLowerCase().endsWith(".txt")) {
            throw new Exception("Некорректный формат файла");
        }

        BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
        String line;
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        int time = 0;

        // Проверка на пустой файл
        if ((line = reader.readLine()) == null) {
            reader.close();
            throw new Exception("Файл пустой");
        }

        do {
            try {
                double signalValue = Double.parseDouble(line);
                series.getData().add(new XYChart.Data<>(time, signalValue));
                time += 1;
            } catch (NumberFormatException e) {
                reader.close();
                throw new Exception("Некорректный формат значения в файле");
            }
        } while ((line = reader.readLine()) != null);

        reader.close();
        chart.getData().add(series);
    }
}
