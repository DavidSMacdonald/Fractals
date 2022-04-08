import java.awt.Graphics;
import java.awt.Color;
import java.awt.Container;
import javax.swing.*;

/**
 *  Square Rose, a recursive algorithm
 */

public class BoxFractal extends JPanel
{
    private int levels;
    public BoxFractal(int lev)
    {
        levels = lev;
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);  // Call JPanel's paintComponent method
        //   to paint the background
        int xCenter = getWidth() / 2;
        int yCenter = getHeight() / 2;


        int [] xcoord = {xCenter - 128, xCenter-128, xCenter + 128, xCenter + 128};
        int [] ycoord = {yCenter-128, yCenter + 128, yCenter + 128, yCenter - 128};

        g.setColor(Color.RED);
        drawAndSplit(g, xcoord, ycoord, levels);

    }

    public void drawAndSplit(Graphics g, int [] x, int [] y, int times)
    {
        if (times==1) {
            g.fillPolygon(x,y,4);
            return;
        }
        int newX1 = x[0]+(x[3]-x[0])/3;
        int newX2 = x[0]+(x[3]-x[0])*2/3;
        int newY1 = y[0]+(y[1]-y[0])/3;
        int newY2 = y[0]+(y[1]-y[0])*2/3;

        drawAndSplit(g,new int[] {x[0],x[0],newX1,newX1},new int[] {y[0],newY1,newY1,y[0]},times-1);
        drawAndSplit(g,new int[] {x[0],x[0],newX1,newX1},new int[] {newY2,y[1],y[1],newY2},times-1);
        drawAndSplit(g,new int[] {newX2,newX2,x[2],x[2]},new int[] {newY2,y[1],y[1],newY2},times-1);
        drawAndSplit(g,new int[] {newX2,newX2,x[2],x[2]},new int[] {y[0],newY1,newY1,y[0]},times-1);
        drawAndSplit(g,new int[] {newX1,newX1,newX2,newX2},new int[] {newY1,newY2,newY2,newY1},times-1);
    }
    public static void main(String[] args)
    {
        JFrame window = new JFrame("Fractals");
        window.setBounds(200, 200, 500, 500);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BoxFractal panel = new BoxFractal(5);
        panel.setBackground(Color.WHITE);
        Container c = window.getContentPane();
        c.add(panel);
        window.setVisible(true);
    }
}


