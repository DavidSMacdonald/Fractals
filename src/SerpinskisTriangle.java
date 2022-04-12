import java.awt.Graphics;
import java.awt.Color;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *  Square Rose, a recursive algorithm
 */

public class SerpinskisTriangle extends JPanel
{
    private int levels;
    public SerpinskisTriangle(int lev)
    {
        levels = lev;
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);  // Call JPanel's paintComponent method
        //   to paint the background
        int xCenter = getWidth() / 2;
        int yCenter = getHeight() / 2;


        int [] xcoord = {xCenter, xCenter-256, xCenter + 256};
        int [] ycoord = {yCenter-(int)(128*Math.sqrt(3)), yCenter+(int)(128*Math.sqrt(3)), yCenter+(int)(128*Math.sqrt(3))};

        g.setColor(Color.RED);
        drawAndSplit(g, xcoord, ycoord, levels);

    }

    public int [] midpoints(int [] x)
    {
        int [] m = new int [3];

        m[0] = (x[0] + x[1])/2;
        m[1] = (x[1] + x[2])/2;
        m[2] = (x[2] + x[0])/2;

        return m;
    }

    public void drawAndSplit(Graphics g, int [] x, int [] y, int times)
    {
        if (times==1) {
            g.fillPolygon(x,y,3);
            return;
        }
        drawAndSplit(g,new int[] {x[0],midpoints(x)[0],midpoints(x)[2]},new int[] {y[0],midpoints(y)[0],midpoints(y)[2]},times-1);
        drawAndSplit(g,new int[] {midpoints(x)[0],x[1],midpoints(x)[1]},new int[] {midpoints(y)[0],y[1],midpoints(y)[1]},times-1);
        drawAndSplit(g,new int[] {midpoints(x)[2],midpoints(x)[1],x[2]},new int[] {midpoints(y)[2],midpoints(y)[1],y[2]},times-1);
//        drawAndSplit(g,new int[] {x[0],midpoints(x)[1],x[1]},new int[] {y[0],midpoints(y)[1],y[1]},times-1);
//        drawAndSplit(g,new int[] {x[1],midpoints(x)[2],x[0]},new int[] {y[1],midpoints(y)[2],y[0]},times-1);
//        drawAndSplit(g,new int[] {x[2],midpoints(x)[0],x[1]},new int[] {y[0],midpoints(y)[0],y[1]},times-1);

    }
    public static void main(String[] args)
    {
        JFrame window = new JFrame("Fractals");
        window.setBounds(200, 200, 500, 500);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SerpinskisTriangle panel = new SerpinskisTriangle(5);
        panel.setBackground(Color.WHITE);
        Container c = window.getContentPane();
        c.add(panel);
        window.setVisible(true);
    }
}