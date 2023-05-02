package GUIweb;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.*;
import javax.swing.border.Border;

public class BordwigWeb extends javax.swing.JPanel{
    /** Board object that the widget dispalys */
    String[][] field;
    int distx;
    int disty;
    int size = 0;

    public BordwigWeb(int size){
        this.field=new String[size][size];

    }

    @Override
    /** Draws the grid */
    protected void paintComponent(Graphics g) {
        // TODO Auto-generated method stub
        super.paintComponent(g);
        Dimension d = getSize();
        try {
            size = field.length;
        } catch (Exception e) {
            size = 0;
        }
        g.setColor(Color.lightGray);
        g.fillRect(0, 0, d.width, d.height);
        g.setColor(Color.gray);
        int linex = 0;
        distx = d.width/(size+1);
        disty = d.height/(size+1);
        for (int i=0; i<size; i++) {
            linex += d.width/(size+1);
            g.fillRect(linex-2, 0, 4, d.height);
        }
        int liney = 0;
        for (int i=0; i<size; i++) {
            liney += d.height/(size+1);
            g.fillRect(0, liney-2, d.width, 4);
        }
        for (int i = 0; i<size; i++){
            for (int j = 0; j<size; j++){
                if(field[i][j] == null){}
                else{
                if(field[i][j].charAt(0) == 'X'){
                    g.setColor(Color.RED);
                    g.fillOval(distx+(i*distx)-((d.height/(size+3))/2), disty+(j*disty)-((d.height/(size+3))/2), (d.height/(size+3)), (d.height/(size+3)));
                }
                if(field[i][j].charAt(0) == 'O'){
                    g.setColor(Color.BLUE);
                    g.fillOval(distx+(i*distx)-((d.height/(size+3))/2), disty+(j*disty)-((d.height/(size+3))/2), (d.height/(size+3)), (d.height/(size+3)));
                }
                if(field[i][j].charAt(0) == 'W'){
                    g.setColor(Color.yellow);
                    g.fillOval(distx+(i*distx)-((d.height/(size+3))/2), disty+(j*disty)-((d.height/(size+3))/2), (d.height/(size+3)), (d.height/(size+3)));
                }
                if(field[i][j].charAt(0) == 'L'){
                    g.setColor(Color.white);
                    g.fillOval(distx+(i*distx)-((d.height/(size+3))/2), disty+(j*disty)-((d.height/(size+3))/2), (d.height/(size+3)), (d.height/(size+3)));

                }
            }
            }
        }
    }
    public void removeL(){
        for (int i = 0; i<size; i++){
            for (int j = 0; j<size; j++){
                if(field[i][j] == null){}
                else{
                if(field[i][j].charAt(0) == 'L'){
        field[i][j] = String.valueOf(field[i][j].charAt(1));
                }
            }
        }
        }
    }
}

    class test{
        JFrame e = new JFrame();
        String [][] b = new String[33][33];
        JToolBar c = new JToolBar("wuba");

        public test(){
            b[5][5]="X";
            c.add(new JTextField("Well"));
            e.setLayout(new BorderLayout());
            e.add(c, BorderLayout.NORTH);
            e.add(new BordwigWeb(15), BorderLayout.CENTER);
            e.setVisible(true);
        }
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> new test()); 
    }
}

    
    

