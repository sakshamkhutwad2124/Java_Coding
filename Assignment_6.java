
import java.awt.*;
import javax.swing.*;

public class Assignment_6 extends JPanel {

    static class Point {

        int x, y;

        Point(int xx, int yy) {
            x = xx;
            y = yy;
        }
    }

    void drawDDALine(Graphics g, Point p1, Point p2, String style) {
        int dx = p2.x - p1.x;
        int dy = p2.y - p1.y;

        int steps = Math.max(Math.abs(dx), Math.abs(dy));

        float xInc = dx / (float) steps;
        float yInc = dy / (float) steps;

        float x = p1.x;
        float y = p1.y;

        for (int i = 0; i <= steps; i++) {
            if (style.equals("dotted")) {
                if (i % 4 == 0) {
                    g.drawLine(Math.round(x), Math.round(y), Math.round(x), Math.round(y));
                }
            } else if (style.equals("thick")) {
                g.fillRect(Math.round(x), Math.round(y), 3, 3);
            }
            x += xInc;
            y += yInc;
        }
    }

    void drawBresenhamLine(Graphics g, Point p1, Point p2, String style) {
        int x = p1.x, y = p1.y;
        int dx = Math.abs(p2.x - p1.x);
        int dy = Math.abs(p2.y - p1.y);
        int sx = p1.x < p2.x ? 1 : -1;
        int sy = p1.y < p2.y ? 1 : -1;
        int err = dx - dy;

        int steps = Math.max(dx, dy), count = 0;

        while (true) {
            if (style.equals("solid")) {
                g.drawLine(x, y, x, y);
            } else if (style.equals("dashed")) {
                if ((count / 5) % 2 == 0) {
                    g.drawLine(x, y, x, y);
                }
            }
            if (x == p2.x && y == p2.y) {
                break;
            }
            int e2 = 2 * err;
            if (e2 > -dy) {
                err -= dy;
                x += sx;
            }
            if (e2 < dx) {
                err += dx;
                y += sy;
            }
            count++;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Point r1[] = {new Point(50, 50), new Point(350, 50), new Point(350, 200), new Point(50, 200)}; // Outer rectangle
        Point r2[] = {new Point(100, 100), new Point(300, 100), new Point(300, 150), new Point(100, 150)}; // Inner rectangle

        Point d1 = new Point((r1[0].x + r1[1].x) / 2, r1[0].y);
        Point d2 = new Point(r1[1].x, (r1[1].y + r1[2].y) / 2);
        Point d3 = new Point((r1[2].x + r1[3].x) / 2, r1[2].y);
        Point d4 = new Point(r1[0].x, (r1[0].y + r1[3].y) / 2);

        for (int i = 0; i < 4; i++) {
            drawDDALine(g, r1[i], r1[(i + 1) % 4], "dotted");
        }

        for (int i = 0; i < 4; i++) {
            drawDDALine(g, r2[i], r2[(i + 1) % 4], "thick");
        }

        drawBresenhamLine(g, d1, d2, "dashed");
        drawBresenhamLine(g, d2, d3, "solid");
        drawBresenhamLine(g, d3, d4, "dashed");
        drawBresenhamLine(g, d4, d1, "solid");
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("Pattern Drawing");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new Assignment_6());
        f.setSize(420, 280);
        f.setVisible(true);
    }
}
