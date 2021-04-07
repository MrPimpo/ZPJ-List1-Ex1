import java.awt.*;
import java.util.Random;

public class BasicMatrix {
    private final double[][] matrix;

    public BasicMatrix(){
        matrix = generate(10,10,10);
    }

    public BasicMatrix(int n, int m){
        matrix = generate(n,m,20);
    }

    private double[][] generate(int columns, int rows, int range){
        double[][] _matrix = new double[columns][rows];
        Random radek = new Random();
        for (int x=0; x<columns; x++)
            for (int y=0; y<rows; y++){
                _matrix[x][y] = -range + ( (range*2) * radek.nextDouble());
            }
        return (_matrix);
    }

    // matrix printing in command line
    public void printBasicMatrix(){
        int max=0;
        for (double[] doubles : matrix) {
            for (int x = 0; x < matrix[0].length; x++) {
                java.text.DecimalFormat df = new java.text.DecimalFormat("0.00");
                String s = df.format(doubles[x]) + "";
                if (s.length()>max)
                    max=s.length();
            }
        }
        for (double[] doubles : matrix) {
            for (int x = 0; x < matrix[0].length; x++) {
                java.text.DecimalFormat df = new java.text.DecimalFormat("0.00");
                StringBuilder s = new StringBuilder(df.format(doubles[x]) + "");
                for (int i=0; i<(max-s.length()); i++)
                    s.insert(0, " ");
                System.out.print("| " + s + " |");
            }
            System.out.println();
        }
    }

    // matrix printing within an OTab
    public void printBasicMatrix (Graphics g, OTab t){
        printBasicMatrix();
        g.setColor(Color.LIGHT_GRAY);
        g.drawRect(t.iX,t.iY,t.iX-5+matrix[0].length*t.getCellWidth(),t.iY-5+matrix.length*t.getCellHeight());
        for (int x = 1; x < matrix[0].length; x++)
            g.drawLine(t.iX+x*t.getCellWidth(),t.iY,t.iX+x*t.getCellWidth(),t.iY+matrix.length*t.getCellHeight());

        for (int y=0; y<matrix.length; y++) {
            String s;
            g.drawLine(t.iX,t.iY+y*t.getCellHeight(),t.iX+matrix[0].length*t.getCellWidth(),t.iY+y*t.getCellHeight());
            for (int x = 0; x < matrix[y].length; x++){
                java.text.DecimalFormat df=new java.text.DecimalFormat("0.00");
                s=df.format(matrix[y][x])+"";
                //s=matrix[y][x]+"";
                if (!s.startsWith("-"))
                    s = " "+s;
                g.drawString(s,t.iX+5+x*t.getCellWidth(),(int)(t.iY+(y+(double)2/3)*t.getCellHeight()));
            }
        }
    }
}
