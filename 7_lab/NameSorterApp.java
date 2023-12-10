import javax.swing.*;

public class NameSorterApp extends JFrame {
    private SorterPanel sorterPanel;

    public NameSorterApp() {
        setTitle("Сортировка имен");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        sorterPanel = new SorterPanel();
        add(sorterPanel);

        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NameSorterApp());
    }
}
