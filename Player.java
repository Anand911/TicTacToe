package TictacToe;
/**
 * Player
 */
public enum Player {
    USER("X"),AI("O"),NONE("-");
    private final String text;
    Player(String text)
    {
        this.text=text;
    }
    @Override
    public String toString(){
        return this.text;
    }
    
}