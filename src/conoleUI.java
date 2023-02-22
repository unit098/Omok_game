import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class conoleUI {
    InputStream in;
    PrintStream out;

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
    public void intro(){
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
            out.println("ok");
        }
        if (g.equals("2")){
            out.println("wow");
        }
        else{
            out.println("not a valid choice");
            gameselect();
        }
        k.close();
        
        
    }
    
    
}