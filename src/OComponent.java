import java.awt.*;

public class OComponent {
    protected int iX, iY;

    public void draw(Graphics g){
        g.drawLine(iX-5,iY,iX+5,iY);
        g.drawLine(iX,iY-5,iX,iY+5);
    }
}
