import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogicalOperationCalculator extends JFrame {

    private JTextField operandField1;
    private JTextField operandField2;
    private JComboBox<String> operationComboBox;
    private JLabel resultLabel;

    public LogicalOperationCalculator() {
        super("Logical Operation Calculator");

        operandField1 = new JTextField(5);
        operandField2 = new JTextField(5);

        String[] operations = {"AND", "OR", "XOR", "NAND"};
        operationComboBox = new JComboBox<>(operations);

        JButton calculateButton = new JButton("=");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performOperation();
            }
        });

        resultLabel = new JLabel("Result: ");

        JPanel panel = new JPanel(new FlowLayout());
        panel.add(operandField1);
        panel.add(operationComboBox);
        panel.add(operandField2);
        panel.add(calculateButton);
        panel.add(resultLabel);

        add(panel);

        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void performOperation() {
        try {
            int operand1 = Integer.parseInt(operandField1.getText());
            int operand2 = Integer.parseInt(operandField2.getText());

            // Проверка, что введены только 0 или 1
            if (isValidBinaryInput(operand1) && isValidBinaryInput(operand2)) {
                String selectedOperation = (String) operationComboBox.getSelectedItem();

                BinaryOperation binaryOperation = getBinaryOperation(selectedOperation);
                if (binaryOperation != null) {
                    int result = binaryOperation.performOperation(operand1, operand2);
                    resultLabel.setText("Result: " + result);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Для логических бинарных операции требуется ввести 1 или 0!", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Невалидное значение!", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean isValidBinaryInput(int value) {
        return value == 0 || value == 1;
    }
    private BinaryOperation getBinaryOperation(String operation) {
        switch (operation) {
            case "AND":
                return new AndOperation();
            case "OR":
                return new OrOperation();
            case "XOR":
                return new XorOperation();
            case "NAND":
                return new NandOperation();
            default:
                return null;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LogicalOperationCalculator();
            }
        });
    }
}
