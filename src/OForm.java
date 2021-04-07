import javax.swing.*;

public class OForm extends JFrame {
    public OForm(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);

        OPanel panel = new OPanel(this);
        add(panel);
    }

}
