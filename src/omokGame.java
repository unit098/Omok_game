public class omokGame {
    int size; // board size
    int type; // records type of game 0: Vs. Ai, 1: Vs. another
    //TODO impliment board and add
    //TODO impliment player interface player and add

    public omokGame(int type, int size){
        this.type = type;
        this.size = size;
    }
    public omokGame(){
        this(0, 15);
    }


}
