import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class OPanel extends JPanel implements MouseListener {
    private final OForm padre;
    private byte simulation;
    // ----- SIM 0 ----- //
    private final OLabel lblQuestion;
    private final OButton btnClassic, btnCustom;
    // ----- SIM 1 ----- //
    private final OTab tab;
    private OButton btnReturno;
    // ----- SIM 2 ----- //
    private final OLabel lblWidth, lblHeight;
    private final OButton btnWidthIncrease, btnWidthDecrease, btnHeightIncrease, btnHeightDecrease;
    private final OButton btnOK;
    private int width, height;

    public OPanel(OForm papa){
        padre = papa;
        simulation = 0;

        // ----- SIM 0 ----- //
        padre.setSize(350,150);
        padre.setTitle("Choose matrix");
        lblQuestion = new OLabel(25, 20,"Choose matrix:");
        btnClassic = new OButton(25,40,125,50,"Classic 10x10");
        btnCustom = new OButton(175,40,125,50,"Other dimensions");
        // ----- SIM 1 ----- //
        tab = new OTab(60,25);
        // ----- SIM 2 ----- //
        btnWidthDecrease = new OButton(10,10,20,20,"<");
        lblWidth = new OLabel(40,10,"columns: 10");
        btnWidthIncrease = new OButton(150,10,20,20,">");
        btnHeightDecrease = new OButton(10,30,20,20,"<");
        lblHeight = new OLabel(40,30,"rows: 10");
        btnHeightIncrease = new OButton(150,30,20,20,">");
        btnOK = new OButton(10,60,160,30,"OK");

        setFocusable(true);
        addMouseListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());

        switch(simulation){
            case 0:
                lblQuestion.draw(g);
                btnCustom.draw(g);
                btnClassic.draw(g);
                break;
            case 1:
                tab.draw(g);
                btnReturno.draw(g);
                break;
            case 2:
                btnWidthDecrease.draw(g);
                btnWidthIncrease.draw(g);
                btnHeightDecrease.draw(g);
                btnHeightIncrease.draw(g);
                lblWidth.draw(g);
                lblHeight.draw(g);
                btnOK.draw(g);
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point p = new Point(e.getX(),e.getY());
        System.out.print("Click:  ");
        switch(simulation){
            case 0:
                if (btnClassic.intercepts(p)){
                    simulation = 1;
                    tab.setBasicMatrix(new BasicMatrix());
                    int W = 10*tab.getCellWidth()+35, H = 10*tab.getCellHeight()+85;
                    padre.setSize(W,H);
                    padre.setTitle("Classic 10x10 matrix");
                    btnReturno = new OButton(W/2-30,H-75,60,30,"OK");
                    this.repaint();
                    System.out.print("\"Classic\" button pressed ");
                }
                if (btnCustom.intercepts(p)){
                    simulation = 2;
                    width = 10;
                    height = 10;
                    lblWidth.update("columns: 10");
                    lblHeight.update("rows: 10");
                    padre.setSize(200,150);
                    this.repaint();
                    System.out.print("\"Other\" button clicked ");
                }
                break;
            case 1:
                if (btnReturno.intercepts(p)){
                    simulation = 0;
                    padre.setSize(350,150);
                    padre.setTitle("Choose matrix");
                    this.repaint();
                    System.out.print("\"Back\" button pressed ");
                }
                break;
            case 2:
                if (btnWidthIncrease.intercepts(p)){
                    width++;
                    lblWidth.update("columns: "+width);
                    this.repaint();
                    System.out.print("Column added: "+width);
                }
                if (btnWidthDecrease.intercepts(p)){
                    width--;
                    lblWidth.update("columns: "+width);
                    this.repaint();
                    System.out.print("Column removed: "+width);
                }
                if (btnHeightIncrease.intercepts(p)){
                    height++;
                    lblHeight.update("row: "+height);
                    this.repaint();
                    System.out.print("Row added: "+height);
                }
                if (btnHeightDecrease.intercepts(p)){
                    height--;
                    lblHeight.update("row: "+height);
                    this.repaint();
                    System.out.print("Row removed: "+height);
                }
                if (btnOK.intercepts(p)){
                    simulation = 1;
                    tab.setBasicMatrix(new BasicMatrix(height,width));
                    int W = width*tab.getCellWidth()+35, H = height*tab.getCellHeight()+85;
                    padre.setSize(W,H);
                    padre.setTitle("Matrix "+width+"x"+height);
                    btnReturno = new OButton(W/2-30,H-75,60,30,"OK");
                    this.repaint();
                    System.out.print("OK button clicked.");
                }
                break;
        }
        System.out.println();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
