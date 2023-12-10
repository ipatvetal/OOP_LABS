import javax.swing.*;

public class AnimatedCirclesApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AnimatedCirclesFrame animatedCirclesFrame = new AnimatedCirclesFrame();
            animatedCirclesFrame.setVisible(true);
        });
    }
}
