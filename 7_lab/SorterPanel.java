import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class SorterPanel extends JPanel {
    private DefaultListModel<String> leftListModel;
    private DefaultListModel<String> rightListModel;
    private String[] maleNames;
    private String[] femaleNames;

    public SorterPanel() {
        setLayout(new GridLayout(1, 3));

        leftListModel = new DefaultListModel<>();
        rightListModel = new DefaultListModel<>();

        // Инициализация левого списка и перемешивание имен
        maleNames = new String[]{"Влад", "Андрей", "Иван", "Михаил", "Дмитрий", "Александр", "Сергей", "Никита", "Артем", "Максим", "Кирилл", "Егор", "Даниил", "Алексей", "Тимофей"};
        femaleNames = new String[]{"Маша", "Катя", "Анна", "Ольга", "Ирина", "Екатерина", "Татьяна", "София", "Виктория", "Алиса", "Елена", "Анастасия", "Полина", "Наталья", "Юлия"};

        NameUtils.shuffleNames(leftListModel, maleNames, femaleNames);

        JList<String> leftList = new JList<>(new DefaultListModel<>());
        JList<String> rightList = new JList<>(new DefaultListModel<>());

        leftList.setModel(leftListModel);
        rightList.setModel(rightListModel);

        add(new JScrollPane(leftList));
        add(createButtonPanel());
        add(new JScrollPane(rightList));

        JButton checkButton = new JButton("Проверить");
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkSorting();
            }
        });

        add(checkButton);
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new GridLayout(5, 1));

        JButton moveRightButton = new JButton(">");
        JButton moveLeftButton = new JButton("<");
        JButton moveAllRightButton = new JButton(">>");
        JButton moveAllLeftButton = new JButton("<<");

        moveRightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveSelectedNames(leftListModel, rightListModel);
            }
        });

        moveLeftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveSelectedNames(rightListModel, leftListModel);
            }
        });

        moveAllRightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveAllNames(leftListModel, rightListModel);
            }
        });

        moveAllLeftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveAllNames(rightListModel, leftListModel);
            }
        });

        buttonPanel.add(moveRightButton);
        buttonPanel.add(moveLeftButton);
        buttonPanel.add(moveAllRightButton);
        buttonPanel.add(moveAllLeftButton);

        return buttonPanel;
    }

    private void moveSelectedNames(DefaultListModel<String> source, DefaultListModel<String> destination) {
        List<String> selectedValues;
        JList<String> leftList = getLeftList();
        JList<String> rightList = getRightList();

        if (leftList.isSelectionEmpty()) {
            selectedValues = rightList.getSelectedValuesList();
        } else {
            selectedValues = leftList.getSelectedValuesList();
        }

        // Удаление из исходного списка
        for (String selectedValue : selectedValues) {
            source.removeElement(selectedValue);
        }

        // Проверка наличия в целевом списке и добавление только отсутствующих элементов
        for (String selectedValue : selectedValues) {
            if (!destination.contains(selectedValue)) {
                destination.addElement(selectedValue);
            }
        }
    }



    private void moveAllNames(DefaultListModel<String> source, DefaultListModel<String> destination) {
        while (!source.isEmpty()) {
            destination.addElement(source.remove(0));
        }
    }

    private void checkSorting() {
        boolean leftColumnIsSorted = true;
        boolean rightColumnIsSorted = true;

        // Проверяем, чтобы в левом списке были только мужские имена и нет женских
        if (!leftListModel.isEmpty()) {
            for (int i = 0; i < leftListModel.size(); i++) {
                String name = leftListModel.get(i);
                if (Arrays.asList(femaleNames).contains(name)) {
                    leftColumnIsSorted = false;
                    break;
                }
            }
        } else {
            leftColumnIsSorted = false;
        }

        // Проверяем, чтобы в правом списке были только мужские имена и нет женских
        if (!rightListModel.isEmpty()) {
            for (int i = 0; i < rightListModel.size(); i++) {
                String name = rightListModel.get(i);
                if (Arrays.asList(femaleNames).contains(name)) {
                    rightColumnIsSorted = false;
                    break;
                }
            }
        } else {
            rightColumnIsSorted = false;
        }

        // Выводим соответствующее сообщение
        if (leftColumnIsSorted || rightColumnIsSorted) {
            JOptionPane.showMessageDialog(this, "Имена отсортированы");
        } else {
            JOptionPane.showMessageDialog(this, "Имена еще не отсортированы");
        }
    }



    private JList<String> getLeftList() {
        return (JList<String>) ((JScrollPane) getComponent(0)).getViewport().getView();
    }

    private JList<String> getRightList() {
        return (JList<String>) ((JScrollPane) getComponent(2)).getViewport().getView();
    }
}
