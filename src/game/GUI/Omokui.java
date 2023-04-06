package game.GUI;
import javax.swing.*;
import java.awt.event.*;
import game.board;
import game.mplayer;
import game.omokGame;
import java.awt.*;

public class Omokui{
    boolean activegame = false;
    omokGame game;
    int currplauer;
    JMenuBar menubar = new JMenuBar();
    JMenu settings_bar = new JMenu("Settings");
    JMenu gamemode = new JMenu("Gamemode");
    ButtonGroup butg = new ButtonGroup();
    ButtonGroup butg2 = new ButtonGroup();
    JRadioButton b1 = new JRadioButton("Human");
    JRadioButton b2 = new JRadioButton("Machine");
    JButton b3 = new JButton("Start");
    JLabel l3 = new JLabel(" ");
    JRadioButton mb1 = new JRadioButton("Human");
    JRadioButton mb2 = new JRadioButton("Machine");
    JButton menitem = new JButton("Play");
    JMenuItem menitem2 = new JMenuItem("Play");
    Bordwidg boardpanel = new Bordwidg();
    JFrame panel = new JFrame("Omok");
    public Omokui(){
    panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    var panell = new JPanel();
    var panel2 = new JPanel();
    var panel3 = new JPanel();
    var panel4 = new JPanel();
    var panel5 = new JPanel();
    var panel6 = new JPanel();
    var panel7 = new JPanel();
    menitem2.setMnemonic('P');
    menitem2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK));
    JToolBar toolbar = new JToolBar("ToolBar");
    toolbar.setFloatable(true);
    gamemode.add(mb1);
    gamemode.add(mb2);
    settings_bar.add(gamemode);
    settings_bar.add(menitem2);
    menubar.add(settings_bar);
    toolbar.add(menitem);
    toolbar.setFloatable(false);
    panel.add(toolbar, BorderLayout.PAGE_START);
    panel.setMinimumSize(new Dimension(1000, 1000));
    panell.setLayout(new BorderLayout());
    panel2.setLayout(new BorderLayout());
    panel6.setLayout(new BorderLayout());
    panel7.setLayout(new BorderLayout());
    panel5.setLayout(new FlowLayout());
    panel3.setLayout(new FlowLayout());
    butg.add(b1);
    butg.add(b2);
    butg2.add(mb1);
    butg2.add(mb2);
    panel3.add(new JLabel("select mode: "));
    panel3.add(b1);
    panel3.add(b2);
    panel7.add(toolbar, BorderLayout.NORTH);
    panel7.add(panel3);
    panel4.add(b3);
    panel5.add(l3);
    panel6.add(boardpanel);
    panel6.add(panel5, BorderLayout.NORTH);
    panel2.add(panel7, BorderLayout.NORTH);
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
            if (activegame){
                int option = JOptionPane.showConfirmDialog(null, "Starting a new game will discard current game, are you sure?", "New Game?", JOptionPane.YES_NO_OPTION);
                if(option == 0){
                    gamestart();
                }
            }
            else{
                if(b1.isSelected()||b2.isSelected()){
                    gamestart();
                }
                else{l3.setText("No mode selected");}
            }
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
    boardpanel.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if(activegame){
            super.mouseClicked(e);
            int x = e.getX();
            int y = e.getY();
            System.out.println(x + " "+ y + " "+ boardpanel.distx+ " "+ boardpanel.disty+" "+x%boardpanel.distx+" "+ y%boardpanel.disty);
            if(((x%boardpanel.distx)-2 <= 10 || (x%boardpanel.distx)+2 >= boardpanel.distx-10) && ((y%boardpanel.disty)-2 <= 10 || (y%boardpanel.disty)+2 >= boardpanel.disty-10)){
                int[] cords = new int[] {Math.round((float)x/boardpanel.distx)-1, Math.round((float)y/boardpanel.disty)-1};
                if(boardpanel.field.getboard()[cords[0]][cords[1]]== "*"){
                game.players[currplauer].place(cords);
                if (boardpanel.field.windetect(cords)){
                    activegame=false;
                    l3.setText("player " + (currplauer+1)+ " has won!!!!!!!!");

                } else{
                System.out.println(boardpanel.field.blockneed[0]+" "+boardpanel.field.blockneed[1]);
                currplauer=game.getplayer();
                if(game.players[currplauer] instanceof mplayer){
                    cords=game.players[currplauer].place();
                    if (boardpanel.field.windetect(cords)){
                        activegame=false;
                        l3.setText("player " + (currplauer+1)+ " has won!!!!!!!!");
                    }
                    currplauer=game.getplayer();
                }
                l3.setText("It is player " + (currplauer+1)+ "'s turn");
                }
                }
            }
            boardpanel.update(boardpanel.getGraphics());
        }
        }
    });
    b1.addActionListener(b1act);
    mb1.addActionListener(b1act);
    b2.addActionListener(b2act);
    mb2.addActionListener(b2act);
    b3.addActionListener(playact);
    menitem.addActionListener(playact);
    menitem2.addActionListener(playact);
}

private void gamestart(){
    if (b1.isSelected()){
        game = new omokGame(2, 15);
        boardpanel.field = game.gamespace();
        activegame = true;
        boardpanel.update(boardpanel.getGraphics());
        currplauer = game.getplayer();
        l3.setText("It is player " + (currplauer+1)+ "'s turn");          
        
        
    }
    else if (b2.isSelected()){
        game = new omokGame(1, 15);
        boardpanel.field = game.gamespace();
        activegame = true;
        boardpanel.update(boardpanel.getGraphics());
        currplauer = game.getplayer();
        if(game.players[currplauer] instanceof mplayer){
            game.players[currplauer].place();
            currplauer=game.getplayer();
        }
        boardpanel.update(boardpanel.getGraphics());
        l3.setText("It is player " + (currplauer+1)+ "'s turn"); 
    } 
    boardpanel.update(boardpanel.getGraphics());
}

        
    
    public static void main(String[] args) {
       new Omokui();

    }
}