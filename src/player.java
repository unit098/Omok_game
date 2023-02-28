import java.lang.reflect.Array;

public abstract class player {
    protected board field;
    protected String token;

    public player(board field, String token){
        this.field = field;
        this.token = token;
    }

    public int[] place(int[] places){
        field.field[places[0]][places[1]] = token;
        return places;
    }

    public int[] place(){
        return new int[]{0, 0};
    }

    
}
