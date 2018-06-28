import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

class GUI extends JPanel {
    private int boardSize;
    private GameEngine ge;
    private JButton buttons[][];

    GUI(GameEngine ge){
        this.ge = ge;
        this.boardSize = ge.getBoardSize();
        buttons = new JButton[boardSize][boardSize];
        setLayout(new GridLayout(boardSize,boardSize));
        initializebuttons();
    }

    private void initializebuttons(){
        for(int i = 0;i<boardSize;i++){
            for(int j = 0;j<boardSize;j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setBackground(new Color(113, 186, 152));
                buttons[i][j].setBorder(new LineBorder(new Color(93, 150, 122)));
                buttons[i][j].setText("");
                buttons[i][j].addActionListener(new buttonListener(ge,buttons));

                add(buttons[i][j]);
            }
        }
    }
    void display() {
        JFrame window = new JFrame("Stratego");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().add(new GUI(ge));
        window.setBounds(ge.getBoardSize()*70, ge.getBoardSize()*50, ge.getBoardSize()*70, ge.getBoardSize()*70);
        window.setVisible(true);
    }
}
