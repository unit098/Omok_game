import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class conoleUI {
    InputStream in;
    PrintStream out;
    omokGame game;

    public conoleUI(InputStream in, PrintStream out) {
        this.in = in;
        this.out = out;
    }
    public conoleUI(){
        this(System.in, System.out);
    }
    public void run(){
        this.intro();
    }
    private void intro(){
        out.println("Welcome to my Omok game");
        gameselect();
    }
    private void gameselect() {
        out.println("Please chose a game type you would like to play");
        out.println("1: Vs. the Ai");
        out.println("2: Vs. a friend");
        Scanner k = new Scanner(in);
        String g = k.next();
        if (g.equals("1")){
            drawBoard(new board(sizeAsk(k)));
            out.println("ok");
        }
        else if (g.equals("2")){
            sizeAsk(k);
            out.println("wow");
        }
        else{
            out.println("not a valid choice");
            gameselect();
        }
        k.close();
    }
    
    private void drawBoard(board board) {
        out.println("the board is");
        char[][] field = board.field;
        for( int i = 0; i<field.length; i++){
            out.print("[  ");
            for( int j = 0; j<field.length; j++){
                out.print(field[i][j]+"  ");
            }
            out.println("]");
        }
    }


    private int sizeAsk(Scanner k){
        String g = k.next();
        int j = 0;
        try{j =Integer.parseInt(g);}
        catch(Exception e){
            out.println("input must be a whole number");
            return sizeAsk(k);
        }
        
        if (j<15){
            out.println("size must be greater than 14");
            return sizeAsk(k);
        }
        else {
            return j;
        }
        
    }
    
}