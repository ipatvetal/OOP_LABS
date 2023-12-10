import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationForm extends JFrame {
    private JTextField codeNameField;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private JComboBox<String> superpowerComboBox;
    private JLabel codeNameLabel;
    private JLabel genderLabel;
    private JLabel superpowerLabel;
    private JLabel previewCodeNameLabel;
    private JLabel previewGenderLabel;
    private JLabel previewSuperpowerLabel;

    public RegistrationForm() {
        // Инициализация компонентов формы
        codeNameField = new JTextField(20);
        maleRadioButton = new JRadioButton("Мужской");
        femaleRadioButton = new JRadioButton("Женский");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);

        superpowerComboBox = new JComboBox<>(new String[]{"Полет", "Невидимость", "Суперсила", "Телепортация", "Эластичность",
                "Телепатия", "Управление временем", "Лазерные глаза", "Гиперскорость", "Трансформация", "Электрошоки",
                "Защита от сил", "Телекинез", "Метаморфозы", "Создание порталов", "Гипноз", "Иммунитет к ядам", "Адаптация к среде",
                "Генетическая модификация"});

        codeNameLabel = new JLabel("Кодовое имя:");
        genderLabel = new JLabel("Пол:");
        superpowerLabel = new JLabel("Суперспособность:");

        previewCodeNameLabel = new JLabel();
        previewGenderLabel = new JLabel();
        previewSuperpowerLabel = new JLabel();

        // Размещение компонентов на форме
        JPanel leftPanel = new JPanel(new GridLayout(4, 2));
        leftPanel.add(codeNameLabel);
        leftPanel.add(codeNameField);
        leftPanel.add(genderLabel);
        leftPanel.add(createGenderPanel());
        leftPanel.add(superpowerLabel);
        leftPanel.add(superpowerComboBox);

        JPanel rightPanel = new JPanel(new GridLayout(3, 1));
        JLabel previewLabel = new JLabel("Кодовое Имя: ");
        rightPanel.add(previewLabel);
        rightPanel.add(previewCodeNameLabel);
        rightPanel.add(previewGenderLabel);
        rightPanel.add(previewSuperpowerLabel);

        // Добавление слушателей
        codeNameField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updatePreview();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updatePreview();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updatePreview();
            }

            private void updatePreview() {
                previewCodeNameLabel.setText(codeNameField.getText());
            }
        });

        maleRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                previewGenderLabel.setText("Мужской");
            }
        });

        femaleRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                previewGenderLabel.setText("Женский");
            }
        });

        superpowerComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                previewSuperpowerLabel.setText(superpowerComboBox.getSelectedItem().toString());
            }
        });

        // Создание главного контейнера
        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(leftPanel, BorderLayout.WEST);
        container.add(rightPanel, BorderLayout.EAST);

        // Настройка окна
        setTitle("Форма регистрации");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null); // Центрирование окна
        setVisible(true);
    }

    private JPanel createGenderPanel() {
        JPanel genderPanel = new JPanel();
        genderPanel.add(maleRadioButton);
        genderPanel.add(femaleRadioButton);
        return genderPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RegistrationForm();
            }
        });
    }
}
