import java.awt.*;

public class OLabel extends OComponent {
    private String text;
    public OLabel(int x, int y, String text){
        iX = x;
        iY = y;
        this.text = text;
    }

    public void update(String text) { this.text = text; }

    @Override
    public void draw(Graphics g){
        g.setColor(Color.LIGHT_GRAY);
        g.drawString(text,iX+5,iY+13);
    }
}
