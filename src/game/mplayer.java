package game;
/** commands for machine player*/
public class mplayer extends player {

    public mplayer (board field, String token){
        super(field, token);
    }
    
    /** basic stratagey places down rows as much as able */
    public int[] place(){
        int i = 0;
        int j = 0;
        while(field.getboard()[i][j] != "*"){
            if(i != field.size-1){
                i+=1;
            } else {
                j += 1;
                i = 0;
            }
        }
        int[] out = new int[]{i, j};
        return super.place(out);
    }
}