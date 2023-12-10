import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class CirclePanel extends JPanel implements Runnable {
    private static final int CIRCLE_SIZE = 100;
    private static final Color CIRCLE_COLOR = Color.BLACK;

    private int circleSize;
    private int direction;
    private int speed;

    public CirclePanel() {
        circleSize = 0;
        direction = 1;
        speed = new Random().nextInt(10) + 1;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(CIRCLE_COLOR);
        int x = (getWidth() - circleSize) / 2;
        int y = (getHeight() - circleSize) / 2;
        g.fillOval(x, y, circleSize, circleSize);
    }

    @Override
    public void run() {
        while (true) {
            updateCircleSize();
            repaint();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateCircleSize() {
        circleSize += direction * speed;

        if (circleSize <= 0 || circleSize >= CIRCLE_SIZE) {
            direction *= -1;
        }
    }
}
