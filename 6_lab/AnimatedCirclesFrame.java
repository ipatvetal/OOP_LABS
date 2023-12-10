import javax.swing.*;
import java.awt.*;

public class AnimatedCirclesFrame extends JFrame {

    public AnimatedCirclesFrame() {
        setTitle("Animated Circles");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 2));
        getContentPane().setBackground(Color.BLACK);

        CirclePanel[] circlePanels = new CirclePanel[4];
        for (int i = 0; i < 4; i++) {
            circlePanels[i] = new CirclePanel();
            add(circlePanels[i]);   
            new Thread(circlePanels[i]).start();
        }
    }
}
