package TictacToe;
public class Cell {
    private int i;
    private int j;
    Cell(int i,int j)
    {
        setI(i);
        setJ(j);
    }
    public int getI() {
        return i;
    }
    public int getJ() {
        return j;
    }
    public void setI(int i) {
        this.i = i;
    }
    public void setJ(int j) {
        this.j = j;
    }
    @Override
    public String toString()
    {
        return "[%d,%d]".formatted(i,j);
    }
}
