import java.util.Arrays;

public class board {
    int size;
    char[][] field;
    
    public board(char[][] field){
        this.field = field;
        size = field.length;
        for (char[] i : field){
        Arrays.fill(i, '*');
        }
    }
    public board(int size){
        this(new char[size][size]);
    }
}
