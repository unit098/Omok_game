
import java.util.Arrays;
/** stores the game board as a 2D array of strings handels win detection and highlighting winning row */
public class board {
    int size;
    String[][] field;
    
    public board(String[][] field){
        this.field = field;
        this.size = field.length;
        for (String[] i : field){
        Arrays.fill(i, "*");
        }
    }
    public board(int size){
        this(new String[size][size]);
    }
    /** returns the 2d array stored in the object */
    public String[][] getboard(){
        return field;
    }
    
    /** detects if a placed piece creates a winning row */
    public boolean windetect(int[] lastPlaced){
        int rowsize = 1;
        String[] cords = new String[5];
        int lookX = lastPlaced[0];
        int lookY = lastPlaced[1];
        cords[0] = lastPlaced[0]+" "+lastPlaced[1];
        String token = field[lastPlaced[0]][lastPlaced[1]];
        rowsize = checkud(lookX, lookY, cords, rowsize, 0, 1, token);
        if (rowsize==5){
            showwinn(cords);
            return true;
        }
        rowsize = checkud(lookX, lookY, cords, rowsize, 0, -1, token);
        if (rowsize==5){
            showwinn(cords);
            return true;
        }
        rowsize = 1;
        cords = new String[5];
        cords[0] = lastPlaced[0]+" "+lastPlaced[1];
        rowsize = checkud(lookX, lookY, cords, rowsize, 1, 1, token);
        if (rowsize==5){
            showwinn(cords);
            return true;
        }
        rowsize = checkud(lookX, lookY, cords, rowsize, 1, -1, token);
        if (rowsize==5){
            showwinn(cords);
            return true;
        }
        rowsize = 1;
        cords = new String[5];
        cords[0] = lastPlaced[0]+" "+lastPlaced[1];
        rowsize = checkdiag(lookX, lookY, cords, rowsize, 1, 1, token);
        if (rowsize==5){
            showwinn(cords);
            return true;
        }
        rowsize = checkdiag(lookX, lookY, cords, rowsize, -1, 1, token);
        if (rowsize==5){
            showwinn(cords);
            return true;
        }
        rowsize = 1;
        cords = new String[5];
        cords[0] = lastPlaced[0]+" "+lastPlaced[1];
        rowsize = checkdiag(lookX, lookY, cords, rowsize, 1, -1, token);
        if (rowsize==5){
            showwinn(cords);
            return true;
        }
        rowsize = checkdiag(lookX, lookY, cords, rowsize, -1, -1, token);
        if (rowsize==5){
            showwinn(cords);
            return true;
        }
        return false;
        

    }

    /** edits the values in the winning row to make the console print them highlighted */
    public void showwinn(String[] wincords){
        for(String i: wincords){
            System.out.println(i);
            String[] j=i.split(" ");
                int[] wincord = new int[]{Integer.parseInt(j[0]), Integer.parseInt(j[1])};
                field[wincord[0]][wincord[1]] = "W" + field[wincord[0]][wincord[1]];
        }
    }

    
   private int checkud(int lookX, int lookY, String[] cords, int rowsize, int axis, int dir, String token){
        int[] lookcords = new int[]{lookX, lookY};
        while(true){
            try {
                lookcords[axis] += dir;
                while(field[lookcords[0]][lookcords[1]]==token){
                    rowsize += 1;
                    cords[rowsize-1] = lookcords[0]+" "+lookcords[1];
                    if (rowsize == 5){
                        return 5;
                    }
                    lookcords[axis] += dir;
                }
                return rowsize;
                
            } catch (Exception e) {
                return rowsize;
            }
        }
    }
    private int checkdiag(int lookX, int lookY, String[] cords, int rowsize, int dir, int mod, String token){
        int[] lookcords = new int[]{lookX, lookY};
        while(true){
            try {
                lookcords[0] += 1*dir;
                lookcords[1] += 1*dir*mod;
                while(field[lookcords[0]][lookcords[1]]==token){
                    rowsize += 1;
                    cords[rowsize-1] = lookcords[0]+" "+lookcords[1];
                    if (rowsize == 5){
                        return 5;
                    }
                    lookcords[0] += 1*dir;
                    lookcords[1] += 1*dir*mod;
                }
                return rowsize;
                
            } catch (Exception e) {
                return rowsize;
            }
        }
    }
}


