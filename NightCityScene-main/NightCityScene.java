import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class NightCityScene extends JPanel implements ActionListener {
    int moonX = 0;
    int carX = 0;
    int cloudX = 600;
    Timer timer;
    boolean[] starVisible = new boolean[50];
    Random rand = new Random();

    public NightCityScene() {
        timer = new Timer(100, this);
        for (int i = 0; i < starVisible.length; i++) {
            starVisible[i] = rand.nextBoolean();
        }
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Night background
        g.setColor(new Color(10, 10, 30));
        g.fillRect(0, 0, getWidth(), getHeight());

        // Moon
        g.setColor(Color.LIGHT_GRAY);
        g.fillOval(moonX, 50, 60, 60);

        // Stars
        g.setColor(Color.WHITE);
        for (int i = 0; i < starVisible.length; i++) {
            if (starVisible[i]) {
                int x = rand.nextInt(getWidth());
                int y = rand.nextInt(150);
                g.fillOval(x, y, 2, 2);
            }
        }

        // Cloud
        g.setColor(Color.GRAY);
        g.fillOval(cloudX, 80, 80, 30);
        g.fillOval(cloudX + 20, 60, 60, 40);

        // Buildings with windows
        for (int i = 0; i < getWidth(); i += 80) {
            int buildingHeight = 100 + rand.nextInt(100);
            int buildingY = getHeight() - buildingHeight - 100;
            g.setColor(Color.DARK_GRAY);
            g.fillRect(i, buildingY, 60, buildingHeight);

            // Windows
            for (int wy = buildingY + 10; wy < getHeight() - 100; wy += 20) {
                for (int wx = i + 5; wx < i + 60 - 10; wx += 15) {
                    boolean lightOn = rand.nextBoolean();
                    g.setColor(lightOn ? Color.YELLOW : Color.BLACK);
                    g.fillRect(wx, wy, 10, 10);
                }
            }
        }

        // Ground
        g.setColor(Color.BLACK);
        g.fillRect(0, getHeight() - 100, getWidth(), 100);

        // Road
        g.setColor(Color.GRAY);
        g.fillRect(0, getHeight() - 60, getWidth(), 40);

        // Street Lamps
        for (int i = 50; i < getWidth(); i += 150) {
            // Pole
            g.setColor(Color.DARK_GRAY);
            g.fillRect(i, getHeight() - 140, 5, 40);

            // Light
            g.setColor(new Color(255, 255, 100, 180)); // semi-transparent yellow
            g.fillOval(i - 10, getHeight() - 150, 25, 25);
        }

        // Car
        g.setColor(Color.RED);
        g.fillRect(carX, getHeight() - 90, 80, 30);
        g.setColor(Color.BLACK);
        g.fillOval(carX + 10, getHeight() - 65, 15, 15);
        g.fillOval(carX + 55, getHeight() - 65, 15, 15);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        moonX += 1;
        if (moonX > getWidth()) moonX = -60;

        carX += 5;
        if (carX > getWidth()) carX = -80;

        cloudX -= 2;
        if (cloudX < -100) cloudX = getWidth();

        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Night City Scene");
        NightCityScene panel = new NightCityScene();
        frame.add(panel);
        frame.setSize(800, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null); // Center the window
    }
}