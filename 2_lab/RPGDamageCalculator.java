import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class RPGDamageCalculator extends Application {

    private String labelStyle = "-fx-text-fill: white; -fx-font-weight: bold; -fx-stroke: black; -fx-stroke-width: 1;";
    private String valueLabelStyle = "-fx-text-fill: white; -fx-font-weight: bold; -fx-stroke: black; -fx-stroke-width: 1;";
    private String boldLabelStyle = "-fx-text-fill: white; -fx-font-weight: bold;";
    private String outlinedLabelStyle = "-fx-text-fill: white; -fx-stroke: black; -fx-stroke-width: 1; -fx-font-weight: bold;";
    private String enemyHPLabelStyle = "-fx-text-fill: white; -fx-font-weight: bold; -fx-stroke: black; -fx-stroke-width: 1;";
    private String resultLabelStyle = "-fx-text-fill: white; -fx-font-weight: bold; -fx-stroke: black; -fx-stroke-width: 1;";
    private String pointsLabelStyle = "-fx-text-fill: white; -fx-font-weight: bold; -fx-stroke: black; -fx-stroke-width: 1;";

    public ComboBox<String> playerClassComboBox;
    private int enemyHP = 500;
    private Label enemyHPLabel;
    private int playerPoints = 10;
    public Stat playerStrength = new Stat(1);
    public Stat playerAgility = new Stat(1);
    public Stat playerStamina = new Stat(1);
    public Stat playerIntellect = new Stat(1);
    public Stat playerSpirit = new Stat(1);

    private int[] enemyStats = {1, 1, 1, 1, 1}; // Характеристики врага

    private Label[] enemyStatsLabels;
    private Label resultLabel;
    private GridPane grid; // Добавляем переменную grid

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Калькулятор урона в RPG");
        primaryStage.setResizable(false);
        grid = new GridPane(); // Инициализируем grid
        grid.setStyle("-fx-background-image: url('/resources/background.png'); -fx-background-size: cover;");
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(50, 25, 25, 25));

        // Создаем ColumnConstraints для каждого столбца
        ColumnConstraints col1 = new ColumnConstraints();
        ColumnConstraints col2 = new ColumnConstraints();
        ColumnConstraints col3 = new ColumnConstraints();

        // Устанавливаем ширину каждого столбца
        col1.setPercentWidth(40);
        col2.setPercentWidth(10);
        col3.setPercentWidth(40);

        // Добавляем ColumnConstraints в GridPane
        grid.getColumnConstraints().addAll(col1, col2, col3);

        playerClassComboBox = new ComboBox<>();
        playerClassComboBox.getItems().addAll("Маг", "Рыцарь", "Плут");
        playerClassComboBox.setPromptText("Выберите класс игрока");

        ComboBox<String> enemyClassComboBox = new ComboBox<>();
        enemyClassComboBox.getItems().addAll("Маг", "Рыцарь", "Плут");
        enemyClassComboBox.setPromptText("Выберите класс врага");

        Label playerPointsLabel = new Label("Доступные очки: " + playerPoints);
        playerPointsLabel.setStyle(pointsLabelStyle);

        resultLabel = new Label("");
        resultLabel.setStyle(resultLabelStyle);

        addStatControls(grid, "Сила", 0, 2, playerPointsLabel, playerStrength);
        addStatControls(grid, "Ловкость", 0, 3, playerPointsLabel, playerAgility);
        addStatControls(grid, "Выносливость", 0, 4, playerPointsLabel, playerStamina);
        addStatControls(grid, "Интеллект", 0, 5, playerPointsLabel, playerIntellect);
        addStatControls(grid, "Дух", 0, 6, playerPointsLabel, playerSpirit);

        enemyStatsLabels = createEnemyStatsLabels(grid, 2);
        enemyHPLabel = new Label("HP: " + enemyHP); // Создаем метку для отображения HP врага
        enemyHPLabel.setStyle(enemyHPLabelStyle);

        Button calculateButton = new Button("Рассчитать урон");

        calculateButton.setOnAction(e -> {
            String playerClass = playerClassComboBox.getValue();
            String enemyClass = enemyClassComboBox.getValue();
            int damage = calculateDamage(playerClass);
            enemyHP -= damage;

            // Обновляем метку HP врага
            enemyHPLabel.setText("HP: " + Math.max(0, enemyHP));

            if (enemyHP <= 0) {
                resultLabel.setText("Враг убит!");
                enemyHP = 10 * enemyStats[2]; // Восстанавливаем HP врага в зависимости от выносливости
                enemyHPLabel.setText("HP: " + enemyHP);
            } else {
                resultLabel.setText("Вы нанесли врагу " + damage + " урона");
            }
        });

        enemyClassComboBox.setOnAction(e -> {
            distributeEnemyStats(enemyClassComboBox.getValue());
        });

        grid.add(playerClassComboBox, 0, 0);
        grid.add(enemyClassComboBox, 2, 0);
        grid.add(playerPointsLabel, 0, 1);

        grid.add(calculateButton, 0, 7);
        grid.add(resultLabel, 0, 8);

        Scene scene = new Scene(grid, 620, 400);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void addStatControls(GridPane grid, String label, int col, int row, Label pointsLabel, Stat stat) {
        Label statLabel = new Label(label);
        statLabel.setStyle(boldLabelStyle);
        Label valueLabel = new Label(Integer.toString(stat.getValue()));
        valueLabel.setAlignment(Pos.CENTER);
        valueLabel.setStyle(outlinedLabelStyle);
        Button decreaseButton = new Button("-");
        Button increaseButton = new Button("+");

        valueLabel.setAlignment(Pos.CENTER);

        HBox hbox = new HBox(5);
        hbox.setAlignment(Pos.CENTER_LEFT);

        decreaseButton.setPadding(new Insets(2, 5, 2, 5));
        increaseButton.setPadding(new Insets(2, 5, 2, 5));

        hbox.getChildren().addAll(decreaseButton, valueLabel, increaseButton);

        decreaseButton.setOnAction(e -> {
            int value = Integer.parseInt(valueLabel.getText());
            if (value > 1 && playerPoints < 10) {
                playerPoints++;
                value--;
                valueLabel.setText(Integer.toString(value));
                updatePointsLabel(pointsLabel);
                stat.value = Integer.parseInt(valueLabel.getText());
            }
        });

        increaseButton.setOnAction(e -> {
            int value = Integer.parseInt(valueLabel.getText());
            if (playerPoints > 0) {
                playerPoints--;
                value++;
                valueLabel.setText(Integer.toString(value));
                updatePointsLabel(pointsLabel);
                stat.value = Integer.parseInt(valueLabel.getText());
            }
        });

        grid.add(statLabel, col, row);
        grid.add(hbox, col + 1, row);
    }

    private Label[] createEnemyStatsLabels(GridPane grid, int col) {
        Label[] labels = new Label[5];
        for (int i = 0; i < labels.length; i++) {
            labels[i] = new Label("");
            labels[i].setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-stroke: black; -fx-stroke-width: 1;");
            grid.add(labels[i], col, i + 2);
        }
        return labels;
    }

    private boolean enemyHPLabelAdded = false;

    private void distributeEnemyStats(String enemyClass) {
        enemyStats[0] = 5;
        enemyStats[1] = 5;
        enemyStats[2] = 5;
        enemyStats[3] = 5;
        enemyStats[4] = 5;

        switch (enemyClass) {
            case "Маг":
                enemyStats[3] = 12;
                enemyStats[4] = 12;
                enemyStats[2] = 2;
                enemyStats[0] = 2;
                break;
            case "Рыцарь":
                enemyStats[0] = 10;
                enemyStats[2] = 10;
                break;
            case "Плут":
                enemyStats[0] = 8;
                enemyStats[1] = 12;
                break;
        }

        enemyHP = 100 * enemyStats[2];
        updateEnemyStatsUI(enemyStats);
        enemyHPLabel.setText("HP: " + enemyHP);

        if (!enemyHPLabelAdded) {
            grid.add(enemyHPLabel, 2, 1);
            enemyHPLabelAdded = true;
        }
    }

    private void updateEnemyStatsUI(int[] enemyStats) {
        for (int i = 0; i < enemyStatsLabels.length; i++) {
            enemyStatsLabels[i].setText(getStatLabel(i, enemyStats[i]));
        }
    }

    private String getStatLabel(int index, int value) {
        switch (index) {
            case 0:
                return "Сила: " + value;
            case 1:
                return "Ловкость: " + value;
            case 2:
                return "Выносливость: " + value;
            case 3:
                return "Интеллект: " + value;
            case 4:
                return "Дух: " + value;
            default:
                return "";
        }
    }

    private int calculateDamage(String playerClass) {
        int damage = 0;

        if ("Рыцарь".equals(playerClass)) {
            damage = playerStrength.value * (int) (1.1 * playerAgility.value) * 10;
        } else if ("Плут".equals(playerClass)) {
            damage = playerStrength.value * (int) (2 * playerAgility.value) * 10;
        } else if ("Маг".equals(playerClass)) {
            damage = playerIntellect.value * (int) (1.3 * playerSpirit.value) * 10;
        }

        return damage;
    }

    private void updatePointsLabel(Label pointsLabel) {
        if (pointsLabel != null) {
            pointsLabel.setText("Доступные очки: " + playerPoints);
        }
    }
}
