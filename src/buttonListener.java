import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class buttonListener extends JButton implements ActionListener {
    private GameEngine ge;
    private JButton[][] buttons;
    private ImageIcon firstPlayerIcon;
    private ImageIcon secondPlayerIcon;
    buttonListener(GameEngine ge,JButton[][] buttons){
        this.ge = ge;
        this.buttons = buttons;
        this.addActionListener(this);

        URL imgUrl = this.getClass().getResource("X.png");
        firstPlayerIcon = new ImageIcon(imgUrl);
        Image image = firstPlayerIcon.getImage();
        image = image.getScaledInstance(55,55,Image.SCALE_SMOOTH);
        firstPlayerIcon.setImage(image);

        URL imgUrl2 = this.getClass().getResource("O.png");
        secondPlayerIcon = new ImageIcon(imgUrl2);
        Image image2 = secondPlayerIcon.getImage();
        image2 = image2.getScaledInstance(55,55,Image.SCALE_SMOOTH);
        secondPlayerIcon.setImage(image2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked = (JButton)e.getSource();
        for(int i=0;i<ge.getBoardSize();i++){
            for(int j=0;j<ge.getBoardSize();j++){
                if(buttons[i][j].equals(buttonClicked)){
                    ge.gameLoop(i,j);
                    break;
                }
            }
        }

        if(ge.isFirstPlayerTurn()&&(buttonClicked.getIcon()==null||buttonClicked.getIcon()==null)){
            buttonClicked.setIcon(firstPlayerIcon);
        }else if (!ge.isFirstPlayerTurn()&&(buttonClicked.getIcon()==null||buttonClicked.getIcon()==null)){
            buttonClicked.setIcon(secondPlayerIcon);
        }

        if(!ge.checkEndOfGame())
        {
            if(ge.getFirstPlayerScore()>ge.getSecondPlayerScore()){
                JOptionPane.showMessageDialog(null, "The First Player Won ! (BLUE)");
            }else if (ge.getFirstPlayerScore()<ge.getSecondPlayerScore()){
                JOptionPane.showMessageDialog(null, "The Second Player Won ! (RED)");
            }else {
                JOptionPane.showMessageDialog(null, "Remis");
            }
        }else {
            if(ge.getRound()%2==0) {
                AlogorithmI bot  = new Algorithm(ge);
                Move move = bot.alphaBeta(6);
                buttons[move.getX()][move.getY()].doClick();
            }
        }
    }
}
