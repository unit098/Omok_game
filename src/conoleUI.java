import java.io.InputStream;
import java.io.PrintStream;

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
    }
    
}