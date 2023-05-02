package GUIweb;
import javax.swing.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import netscape.javascript.JSObject;

import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.awt.*;

/** GUI for omok game */
public class OmokuiWeb{
    WebConn web = new WebConn(this);
    boolean activegame = false;
    String url;
    int currplauer;
    int size = -11;
    String pid;
    JMenuBar menubar = new JMenuBar();
    JMenu gamemode = new JMenu("Strategy");
    ButtonGroup butg = new ButtonGroup();
    ButtonGroup butg2 = new ButtonGroup();
    JTextField b1 = new JTextField("http://omok.atwebpages.com");
    JComboBox<String> b2 = new JComboBox<String>();
    JButton b3 = new JButton("Start");
    JButton b4 = new JButton("Connect");
    JLabel l3 = new JLabel(" ");
    JButton menitem = new JButton("Play");
    JMenuItem menitem2 = new JMenuItem("Play");
    BordwigWeb boardpanel = new BordwigWeb(0);
    JFrame panel = new JFrame("Omok");

    public OmokuiWeb(){
    b1.setColumns(18);
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
    panel3.add(new JLabel("Connection Info: "));
    panel3.add(b1);
    panel3.add(b2);
    panel3.add(b4);
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
    menubar.add(gamemode);
    menubar.add(menitem2);
    panel.setJMenuBar(menubar);
    ImageIcon oicon = new ImageIcon("funnymomemnydfsjhfdjasdjl;.png");
    Image image = oicon.getImage();
    Image outimage = image.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
    Icon icon = new ImageIcon(outimage);
    menitem.setIcon(icon);
    menitem2.setIcon(icon);
    menitem.setToolTipText("Starts a game");
    panel.setVisible(true);



    ActionListener connact = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        if (!activegame){
        ButtonGroup sbg = new ButtonGroup();    
        b2.removeAllItems();
        gamemode.removeAll();
        b2.update(b2.getGraphics());
            url = b1.getText();
            JSONObject info = web.info(url);
            size = (int) info.get("size");
            for(var i: (JSONArray) (info.get("strategies"))){
                    String ci = (String)i;
                    JRadioButton ri = new JRadioButton(ci);
                    sbg.add(ri);
                    gamemode.add(ri);
                    b2.addItem(ci);
                    ActionListener gsact = new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            ri.setSelected(true);
                            b2.setSelectedItem(ci);
                        };
                    };
                    ItemListener gsactcb = new ItemListener() {
                        public void itemStateChanged(ItemEvent e) {
                            if(b2.getSelectedItem()==ci){
                                ri.setSelected(true);
                            }
                        };
                    };
                    b2.addItemListener(gsactcb);
                    ri.addActionListener(gsact);
                }
    }
}
    };
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
                    gamestart();
            }
        }
    };
    boardpanel.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if(activegame){
            super.mouseClicked(e);
            int x = e.getX();
            int y = e.getY();
                if(((x%boardpanel.distx)-2 <= 10 || (x%boardpanel.distx)+2 >= boardpanel.distx-10) && ((y%boardpanel.disty)-2 <= 10 || (y%boardpanel.disty)+2 >= boardpanel.disty-10)){
                int[] cords = new int[] {Math.round((float)x/boardpanel.distx)-1, Math.round((float)y/boardpanel.disty)-1};
                JSONObject info = web.play(url, pid, cords);
                        if ((boolean) info.get("response")){
                            boardpanel.removeL();
                            JSONObject playmove = (JSONObject)info.get("ack_move");
                            boardpanel.field[(int)playmove.get("x")][(int)playmove.get("y")]="O";
                            if((Boolean)playmove.get("isWin")){
                                JSONArray wincords = (JSONArray)playmove.get("row");
                                for(int i =0; i<wincords.length(); i+=2){
                                    boardpanel.field[(int)wincords.get(i)][(int)wincords.get(i+1)]="W";
                                }
                                l3.setText("You have won !!!?!?!??!?!");
                                activegame = false;
                            }
                            else if((Boolean)playmove.get("isDraw")){
                                l3.setText("Game is draw");
                                activegame = false;

                            } else{
                                JSONObject commove = (JSONObject)info.get("move");
                                boardpanel.field[(int)commove.get("x")][(int)commove.get("y")]="LX";
                            if((Boolean)commove.get("isWin")){
                                JSONArray wincords = (JSONArray)commove.get("row");
                                for(int i =0; i<wincords.length(); i+=2){
                                    boardpanel.field[(int)wincords.get(i)][(int)wincords.get(i+1)]="W";
                                }
                                l3.setText("AI have won !!!?!?!??!?!");
                                activegame = false;
                            }
                            else if((Boolean)commove.get("isDraw")){
                                l3.setText("Game is draw");
                                activegame = false;

                            }

                            }

                        }
                    }
                    
                }
            boardpanel.update(boardpanel.getGraphics());
        }
    });
    b3.addActionListener(playact);
    menitem.addActionListener(playact);
    menitem2.addActionListener(playact);
    b4.addActionListener(connact);
}

/** Runs to start a new game */
private void gamestart(){
        if(url==null){
        }
        else{
        l3.setText("");
        activegame = true;
        JSONObject info = web.Game(url);
        pid = (String) info.get("pid");
    boardpanel.field=new String[size][size];
    boardpanel.update(boardpanel.getGraphics());
}
    }
    

        
    
    public static void main(String[] args) {
       new OmokuiWeb();

    }
}