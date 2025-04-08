import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class FloodFill extends JPanel {
    private BufferedImage image;
    private int width, height;
    private Pilha<Point> pilha;
    private Fila<Point> fila;
    private Timer timer;
    private int targetColor, newColor;
    private static final int PIXELS_PER_STEP = 10;
    private boolean usandoPilha;

    public FloodFill(BufferedImage image) {
        this.image = image;
        this.width = image.getWidth();
        this.height = image.getHeight();
    }

    public void floodFillComPilha(int startX, int startY, int newColorRGB) {
        this.usandoPilha = true;
        this.pilha = new Pilha<>();
        this.targetColor = image.getRGB(startX, startY);
        this.newColor = newColorRGB;

        if (targetColor != newColor) {
            pilha.push(new Point(startX, startY));
        }

        iniciarAnimacao();
    }

    public void floodFillComFila(int startX, int startY, int newColorRGB) {
        this.usandoPilha = false;
        this.fila = new Fila<>();
        this.targetColor = image.getRGB(startX, startY);
        this.newColor = newColorRGB;

        if (targetColor != newColor) {
            fila.enqueue(new Point(startX, startY));
        }

        iniciarAnimacao();
    }

    private void iniciarAnimacao() {
        JFrame frame = new JFrame("Flood Fill Visualization");
        frame.add(this);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        timer = new Timer(5, e -> processNextSteps());
        timer.start();
    }

    private void processNextSteps() {
        int steps = 0;

        while (steps < PIXELS_PER_STEP) {
            Point p = null;

            if (usandoPilha) {
                if (pilha.isEmpty()) {
                    encerrar();
                    return;
                }
                p = pilha.pop();
            } else {
                if (fila.isEmpty()) {
                    encerrar();
                    return;
                }
                p = fila.dequeue();
            }

            int x = p.x;
            int y = p.y;

            if (x < 0 || x >= width || y < 0 || y >= height) continue;
            if (image.getRGB(x, y) != targetColor) continue;

            image.setRGB(x, y, newColor);
            steps++;

            Point[] vizinhos = {
                    new Point(x + 1, y),
                    new Point(x - 1, y),
                    new Point(x, y + 1),
                    new Point(x, y - 1)
            };

            for (Point viz : vizinhos) {
                if (usandoPilha) {
                    pilha.push(viz);
                } else {
                    fila.enqueue(viz);
                }
            }
        }

        repaint();
    }

    private void encerrar() {
        timer.stop();
        System.out.println("Flood Fill concluído!");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }

    public BufferedImage getImage() {
        return image;
    }
}
