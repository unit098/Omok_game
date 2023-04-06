package game.GUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.*;
import javax.swing.border.Border;

import game.*;

public class Bordwidg extends javax.swing.JPanel{
    /** Board object that the widget dispalys */
    board field;
    int distx;
    int disty;

    public Bordwidg(board field) {
        this.field = field;
    }
    public Bordwidg() {
        this(new board(0));
    }

    @Override
    protected void paintComponent(Graphics g) {
        // TODO Auto-generated method stub
        super.paintComponent(g);
        Dimension d = getSize();
        int size = 0;
        try {
            size = field.getboard()[0].length;
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
                if(field.getboard()[i][j].charAt(0) == 'X'){
                    g.setColor(Color.RED);
                    g.fillOval(distx+(i*distx)-((d.width/(size))/2), disty+(j*disty)-((d.height/(size))/2), (d.width/(size)), (d.height/(size)));
                }
                if(field.getboard()[i][j].charAt(0) == 'O'){
                    g.setColor(Color.BLUE);
                    g.fillOval(distx+(i*distx)-((d.width/(size))/2), disty+(j*disty)-((d.height/(size))/2), (d.width/(size)), (d.height/(size)));
                }
                if(field.getboard()[i][j].charAt(0) == 'W'){
                    g.setColor(Color.yellow);
                    g.fillOval(distx+(i*distx)-((d.width/(size))/2), disty+(j*disty)-((d.height/(size))/2), (d.width/(size)), (d.height/(size)));
                }
            }
        }
    }
}
    class test{
        JFrame e = new JFrame();
        board b = new board(20);
        JToolBar c = new JToolBar("wuba");

        public test(){
            b.getboard()[5][5]="X";
            c.add(new JTextField("Well"));
            e.setLayout(new BorderLayout());
            e.add(c, BorderLayout.NORTH);
            e.add(new Bordwidg(), BorderLayout.CENTER);
            e.setVisible(true);
        }
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> new test()); 
    }
}

    
    

