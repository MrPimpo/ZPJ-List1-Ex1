import java.awt.*;

public class ZbysiuTab extends ZbysiuComponent {
    private int cW, cH;
    private BasicMatrix basicMatrix;

    public ZbysiuTab(int cellWidth, int cellHeight){
        iX=5; iY=5;
        cW = cellWidth;
        cH = cellHeight;
    }

    public int getCellWidth() { return (cW); }
    public int getCellHeight() { return (cH); }

    public void setBasicMatrix(BasicMatrix bm){ basicMatrix = bm; }

    @Override
    public void draw(Graphics g){
        basicMatrix.printBasicMatrix(g,this);
    }
}
