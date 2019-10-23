import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ZbysiuPanel extends JPanel implements MouseListener {
    private ZbysiuForm padre;
    private byte simulation;
    // ----- SIM 0 ----- //
    private ZbysiuLabel lblQuestion;
    private ZbysiuButton btnClassic, btnCustom;
    // ----- SIM 1 ----- //
    private ZbysiuTab tab;
    private ZbysiuButton btnReturno;
    // ----- SIM 2 ----- //
    private ZbysiuLabel lblWidth, lblHeight;
    private ZbysiuButton btnWidthIncrease, btnWidthDecrease, btnHeightIncrease, btnHeightDecrease;
    private ZbysiuButton btnOK;
    private int width, height;

    public ZbysiuPanel(ZbysiuForm papa){
        padre = papa;
        simulation = 0;

        // ----- SIM 0 ----- //
        padre.setSize(350,150);
        padre.setTitle("Wybierz macierz");
        lblQuestion = new ZbysiuLabel(25, 20,"Wybierz macierz:");
        btnClassic = new ZbysiuButton(25,40,125,50,"Klasyczna 10x10");
        btnCustom = new ZbysiuButton(175,40,125,50,"Inne wymiary");
        // ----- SIM 1 ----- //
        tab = new ZbysiuTab(140,25);
        // ----- SIM 2 ----- //
        btnWidthDecrease = new ZbysiuButton(10,10,20,20,"<");
        lblWidth = new ZbysiuLabel(40,10,"kolumny: 10");
        btnWidthIncrease = new ZbysiuButton(150,10,20,20,">");
        btnHeightDecrease = new ZbysiuButton(10,30,20,20,"<");
        lblHeight = new ZbysiuLabel(40,30,"wiersze: 10");
        btnHeightIncrease = new ZbysiuButton(150,30,20,20,">");
        btnOK = new ZbysiuButton(10,60,160,30,"OK");

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
        System.out.print("KlikuKllik. ");
        switch(simulation){
            case 0:
                if (btnClassic.intercepts(p)){
                    simulation = 1;
                    tab.setBasicMatrix(new BasicMatrix());
                    int W = 10*tab.getCellWidth()+35, H = 10*tab.getCellHeight()+85;
                    padre.setSize(W,H);
                    padre.setTitle("Klasyczna macierz 10x10");
                    btnReturno = new ZbysiuButton(W/2-30,H-75,60,30,"OK");
                    this.repaint();
                    System.out.print("Klik w klasyczny ");
                }
                if (btnCustom.intercepts(p)){
                    simulation = 2;
                    width = 10;
                    height = 10;
                    lblWidth.update("kolumny: 10");
                    lblHeight.update("wiersze: 10");
                    padre.setSize(200,150);
                    this.repaint();
                    System.out.print("Klik w inny ");
                }
                break;
            case 1:
                if (btnReturno.intercepts(p)){
                    simulation = 0;
                    padre.setSize(350,150);
                    padre.setTitle("Wybierz macierz");
                    this.repaint();
                    System.out.print("Klik w powrotny ");
                }
                break;
            case 2:
                if (btnWidthIncrease.intercepts(p)){
                    width++;
                    lblWidth.update("kolumny: "+width);
                    this.repaint();
                }
                if (btnWidthDecrease.intercepts(p)){
                    width--;
                    lblWidth.update("kolumny: "+width);
                    this.repaint();
                }
                if (btnHeightIncrease.intercepts(p)){
                    height++;
                    lblHeight.update("wiersze: "+height);
                    this.repaint();
                }
                if (btnHeightDecrease.intercepts(p)){
                    height--;
                    lblHeight.update("wiersze: "+height);
                    this.repaint();
                }
                if (btnOK.intercepts(p)){
                    simulation = 1;
                    tab.setBasicMatrix(new BasicMatrix(height,width));
                    int W = width*tab.getCellWidth()+35, H = height*tab.getCellHeight()+85;
                    padre.setSize(W,H);
                    padre.setTitle("Macierz "+width+"x"+height);
                    btnReturno = new ZbysiuButton(W/2-30,H-75,60,30,"OK");
                    this.repaint();
                    System.out.print("Klik w OK ");
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
