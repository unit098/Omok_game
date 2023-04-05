package game.GUI;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.*;
import java.awt.event.*;

import game.board;

import java.awt.*;

public class Omokui{
    JMenuBar menubar = new JMenuBar();
    JMenu settings_bar = new JMenu("Settings");
    JMenu gamemode = new JMenu("Gamemode");
    public Omokui(){
    var panel = new JFrame("monkey");
    panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    var panell = new JPanel();
    var panel2 = new JPanel();
    var panel3 = new JPanel();
    var panel4 = new JPanel();
    var panel5 = new JPanel();
    var panel6 = new JPanel();
    var butg = new ButtonGroup();
    var butg2 = new ButtonGroup();
    var b1 = new JRadioButton("Human");
    var b2 = new JRadioButton("Machine");
    var b3 = new JButton("Start");
    var l3 = new JLabel(" ");
    var mb1 = new JRadioButton("Human");
    var mb2 = new JRadioButton("Machine");
    var menitem = new JMenuItem("Play");
    var menitem2 = new JMenuItem("Play");
    menitem2.setMnemonic('P');
    menitem2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK));
    JTool
    
    gamemode.add(mb1);
    gamemode.add(mb2);
    settings_bar.add(gamemode);
    settings_bar.add(menitem2);
    menubar.add(settings_bar);
    menubar.add(menitem);
    panel.setMinimumSize(new Dimension(1000, 1000));
    panell.setLayout(new BorderLayout());
    panel2.setLayout(new BorderLayout());
    panel6.setLayout(new BorderLayout());
    panel5.setLayout(new FlowLayout());
    panel3.setLayout(new FlowLayout());
    butg.add(b1);
    butg.add(b2);
    butg2.add(mb1);
    butg2.add(mb2);
    panel3.add(new JLabel("select mode: "));
    panel3.add(b1);
    panel3.add(b2);
    panel4.add(b3);
    panel5.add(l3);
    board board = new board(30);
    board.getboard()[4][4] = "X";
    panel6.add(new Bordwidg(board));
    panel6.add(panel5, BorderLayout.NORTH);
    panel2.add(panel3, BorderLayout.NORTH);
    panel2.add(panel4);
    panell.add(panel2, BorderLayout.NORTH);
    panell.add(panel6);
    panel.setContentPane(panell);
    panel.pack();
    panel.setJMenuBar(menubar);
    ImageIcon oicon = new ImageIcon("funnymomemnydfsjhfdjasdjl;.png");
    Image image = oicon.getImage();
    Image outimage = image.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
    Icon icon = new ImageIcon(outimage);
    menitem.setIcon(icon);
    menitem2.setIcon(icon);
    menitem.setToolTipText("Uhh it plays? bozo");
    panel.setVisible(true);


    ActionListener playact = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            if (b1.isSelected()){l3.setText("Vs. Human selected");}else if (b2.isSelected()){l3.setText("Vs. Machine selected");} else{l3.setText("No mode selected");}
        }
        
    };

    ActionListener b1act = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            b1.setSelected(true);
            mb1.setSelected(true);
        }
        
    };
    ActionListener b2act = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            b2.setSelected(true);
            mb2.setSelected(true);
        }
        
    };
    b1.addActionListener(b1act);
    mb1.addActionListener(b1act);
    b2.addActionListener(b2act);
    mb2.addActionListener(b2act);
    b3.addActionListener(playact);
    menitem.addActionListener(playact);
    menitem2.addActionListener(playact);

        
    }
    public static void main(String[] args) {
       new Omokui();

    }
}


class Counterm {
int val;

public int getval(){
    return val;
}
public void inc(){
    val++;
}
public void dinc(){
    val--;
}
public void reset(){
    val = 0;
}


}