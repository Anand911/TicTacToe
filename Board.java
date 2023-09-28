package TictacToe;
import java.util.List;
import java.util.ArrayList;
import java.lang.Math;
public class Board {
    private static int boradSize=3;
    private Player[][] board;
    private List<Cell> cells= new ArrayList<>();
    int currScore,alpha = Integer.MIN_VALUE,beta=Integer.MAX_VALUE;
    boolean gameover;
    List<Cell> bestMove;
    public void resetMinimax() {
        currScore =Integer.MAX_VALUE;
        this.bestMove=new ArrayList<>();
    }
    Board()
    {
        this.alpha = Integer.MIN_VALUE;
        this.beta = Integer.MAX_VALUE;
        Player none=Player.NONE;
        gameover=false;
        this.board=new Player[boradSize][boradSize];
        for (int i = 0; i < boradSize; i++) {
            for (int j = 0; j <boradSize; j++) {
                Cell newCell=new Cell(i,j);
                cells.add(newCell);
                board[i][j]=none;
            }
        //System.out.println(cells);
        }
    }
    void displayBoard()
    {
        for (int i = 0; i < boradSize; i++) {
            for (int j = 0; j <boradSize; j++) {
                System.out.print(board[i][j]+" ");
            }
            System.out.println("");
        }
    }
    List<Cell> emptyCell()
    {
        List<Cell> emptyCells=new ArrayList<>();
        for (Cell cell : cells) {
            if(board[cell.getI()][cell.getJ()]==Player.NONE)
                emptyCells.add(cell);

        }
        return emptyCells;
    }
    void move(Player player,Cell move)
    {
        board[move.getI()][move.getJ()]=player;
    }
    void undoMove(Cell move)
    {
        board[move.getI()][move.getJ()]=Player.NONE;
    }
    void makeUserMove(int move)
    {
        move(Player.USER,cells.get(move-1));
        displayBoard();
    }
    Boolean isWinning(Player player)
    {
        boolean diagonal1=true, diagonal2=true;
        for (int i = 0; i < boradSize; i++) {
            if (board[i][i] != player) {
                diagonal1 = false;
            }

            if (board[i][boradSize - 1 - i] != player) {
                diagonal2 = false;
            }

            for (int j = 0; j < boradSize; j++) {
                
                if(board[i][0]==player && board[i][1]==player && board[i][2]==player)
                    return true;
                else if(board[0][i]==player && board[1][i]==player && board[2][i]==player)
                    return true;
                
            }
        }
        if(diagonal1 || diagonal2)
            return true;
        return false;
    }
    Player GameOver()
    {
        List<Cell> emptyCells=emptyCell();
        if(isWinning(Player.USER)) //MAX
            {
                System.out.println("Congrats You Win!");
                gameover=true;
                return Player.USER;
            }
        else if(isWinning(Player.AI))  //MIN
            {
                System.out.println("Haha You can't Beat AI!!");
                gameover=true;
                return Player.AI;
            }
        else if(emptyCells.size()==0)
            {
                System.out.println("Game ends in a Draw!");
                gameover=true;
                return Player.NONE;
            }
        return null;

    }
    
    int MiniMax(Player player, int depth) {
        List<Cell> emptyCells = emptyCell();

        if (isWinning(Player.USER)) // MAX
            return -1;

        if (isWinning(Player.AI)) // MIN
            return 1;
        if (emptyCells.size() == 0)
            return 0;


        if (player == Player.AI) {
            int bestScore = Integer.MIN_VALUE;
            for (Cell point : emptyCells) {
                move(player, point);
                int currScore = MiniMax(Player.USER, depth + 1);
                undoMove(point);
                bestScore = Math.max(bestScore, currScore);
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (Cell point : emptyCells) {
                move(player, point);
                int currScore = MiniMax(Player.AI, depth + 1);
                undoMove(point);
                bestScore = Math.min(bestScore, currScore);
            }
            return bestScore;
        }
    }
    public Cell findBestMove() {
        int bestScore = Integer.MIN_VALUE;
        Cell bestMove = null;

        for (Cell point : emptyCell()) {
            move(Player.AI, point);
            int currScore = MiniMax(Player.USER, 0);
            undoMove(point);

            if (currScore > bestScore) {
                bestScore = currScore;
                bestMove = point;
            }
        }

        return bestMove;
    }
}
