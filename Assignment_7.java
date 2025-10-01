
import java.awt.*;
import java.util.Scanner;
import javax.swing.*;

public class Assignment_7 extends JPanel {

    private int choice;
    private int style;

    public Assignment_7(int choice, int style) {
        this.choice = choice;
        this.style = style;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int xc = 250, yc = 250, r = 100;
        g.setColor(Color.BLUE);

        switch (choice) {
            case 1:
                drawCircleDDA(g, xc, yc, r);
                break;
            case 2:
                drawCircleBresenham(g, xc, yc, r);
                break;
            case 3:
                drawCircleMidpoint(g, xc, yc, r);
                break;
        }
    }

    private void plot(Graphics g, int x, int y, int xc, int yc, int k) {
        if (style == 1) {
            g.fillRect(xc + x, yc + y, 2, 2);
            g.fillRect(xc - x, yc + y, 2, 2);
            g.fillRect(xc + x, yc - y, 2, 2);
            g.fillRect(xc - x, yc - y, 2, 2);
            g.fillRect(xc + y, yc + x, 2, 2);
            g.fillRect(xc - y, yc + x, 2, 2);
            g.fillRect(xc + y, yc - x, 2, 2);
            g.fillRect(xc - y, yc - x, 2, 2);
        } else if (style == 2) {
            if (k % 5 == 0) {
                g.fillRect(xc + x, yc + y, 2, 2);
                g.fillRect(xc - x, yc + y, 2, 2);
                g.fillRect(xc + x, yc - y, 2, 2);
                g.fillRect(xc - x, yc - y, 2, 2);
                g.fillRect(xc + y, yc + x, 2, 2);
                g.fillRect(xc - y, yc + x, 2, 2);
                g.fillRect(xc + y, yc - x, 2, 2);
                g.fillRect(xc - y, yc - x, 2, 2);
            }
        } else if (style == 3) {
            if (k % 10 < 6) {
                g.fillRect(xc + x, yc + y, 2, 2);
                g.fillRect(xc - x, yc + y, 2, 2);
                g.fillRect(xc + x, yc - y, 2, 2);
                g.fillRect(xc - x, yc - y, 2, 2);
                g.fillRect(xc + y, yc + x, 2, 2);
                g.fillRect(xc - y, yc + x, 2, 2);
                g.fillRect(xc + y, yc - x, 2, 2);
                g.fillRect(xc - y, yc - x, 2, 2);
            }
        }
    }

    private void drawCircleDDA(Graphics g, int xc, int yc, int r) {
        int k = 0;
        for (double theta = 0; theta <= 360; theta += 0.5) {
            int x = (int) Math.round(r * Math.cos(Math.toRadians(theta)));
            int y = (int) Math.round(r * Math.sin(Math.toRadians(theta)));
            plot(g, x, y, xc, yc, k++);
        }
    }

    private void drawCircleBresenham(Graphics g, int xc, int yc, int r) {
        int x = 0, y = r;
        int d = 3 - 2 * r;
        int k = 0;

        while (x <= y) {
            plot(g, x, y, xc, yc, k++);
            if (d < 0) {
                d += 4 * x + 6;
            } else {
                d += 4 * (x - y) + 10;
                y--;
            }
            x++;
        }
    }

    private void drawCircleMidpoint(Graphics g, int xc, int yc, int r) {
        int x = 0, y = r;
        int d = 1 - r;
        int k = 0;

        while (x <= y) {
            plot(g, x, y, xc, yc, k++);
            if (d < 0) {
                d += 2 * x + 3;
            } else {
                d += 2 * (x - y) + 5;
                y--;
            }
            x++;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("===== Circle Drawing Menu =====");
        System.out.println("1. DDA Algorithm");
        System.out.println("2. Bresenham Algorithm");
        System.out.println("3. Midpoint Algorithm");
        System.out.print("Enter your choice (1-3): ");
        int algoChoice = sc.nextInt();

        System.out.println("\nSelect Circle Style:");
        System.out.println("1. Solid");
        System.out.println("2. Dotted");
        System.out.println("3. Dashed");
        System.out.print("Enter your choice (1-3): ");
        int styleChoice = sc.nextInt();

        sc.close();

        JFrame frame = new JFrame("Circle Drawing Algorithms");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Assignment_7 panel = new Assignment_7(algoChoice, styleChoice);
        frame.add(panel);

        frame.setVisible(true);
    }
}
