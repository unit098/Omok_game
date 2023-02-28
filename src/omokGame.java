import java.lang.reflect.Array;
import java.util.Random;

public class omokGame {
    int size; // board size
    int type; // records type of game 1: Vs. Ai, 2: Vs. another\
    int currentplayer;
    Random ran = new Random();
    board gamespace;
    player[] players = new player[2];

    public omokGame(int type, int size){
        this.type = type;
        this.gamespace = new board(size);
        this.currentplayer = ran.nextInt(2);
        if (type == 1){
            players[0] = new hplayer(gamespace, "O");
            players[1] = new mplayer(gamespace, "X");
        }
        if (type == 2){
            players[0] = new hplayer(gamespace, "O");
            players[1] = new hplayer(gamespace, "X");
        }
    }
    public omokGame(){
        this(0, 15);
    }

    public int getplayer(){
        if (currentplayer == 0){
            currentplayer = 1;
        }
        else {
            currentplayer = 0;
        }
        return currentplayer;
    }


}
