package game.GUI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.*;
import game.*;

public class Bordwidg extends javax.swing.JPanel{
    /** Board object that the widget dispalys */
    private board field;

    public Bordwidg(board field) {
        this.field = field;
    }

    @Override
    protected void paintComponent(Graphics g) {
        // TODO Auto-generated method stub
        super.paintComponent(g);
        Dimension d = getSize();
        int size = field.getboard()[0].length+1;
        g.setColor(Color.lightGray);
        g.fillRect(0, 0, d.width, d.height);
        g.setColor(Color.gray);
        int linex = 0;
        for (int i=0; i<size; i++) {
            linex += d.width/size+1;
            g.fillRect(linex-2, 0, 4, d.height);
        }
        int liney = 0;
        for (int i=0; i<size; i++) {
            liney += d.height/size+1;
            g.fillRect(0, liney-2, d.width, 4);
        }
    }
}
    class test{
        JFrame e = new JFrame();
        board b = new board(20);

        public test(){
            e.add(new Bordwidg(b));
            e.setVisible(true);
        }
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> new test()); 
    }
}

    
    

