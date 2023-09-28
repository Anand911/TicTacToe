package TictacToe;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
public class Game {
    private static Scanner sc=new Scanner(System.in);
    private Board newBoard;
    private int AIscore, USERscore;
    Boolean endGame;
    Game()
    {
        AIscore=0;
        USERscore=0;
        endGame=false;
    }
    void checkWinner()
    {
        Player winner=newBoard.GameOver();
        if(winner==Player.AI)
            AIscore++;
        if(winner==Player.USER)
            USERscore++;
    }
    void results()
    {
        System.out.println(String.format("Score:\n AI= %d \nUSER=%d", AIscore, USERscore));
    }
   
    void makeFirstInput(Boolean AI)
    {
        newBoard.resetMinimax();
        if(AI)
        {
            Random random=new Random();
            List<Cell> emptyCells=new ArrayList<>(newBoard.emptyCell());
            int random_index=random.nextInt(emptyCells.size());
            Cell randomMove=emptyCells.get(random_index);
            System.out.println("Computer is thinking.....random Moves="+randomMove);
            newBoard.move(Player.AI, randomMove);
            newBoard.displayBoard();
        }
        do {
            System.out.println("Which cell do you input [1-9]?");
            int move=sc.nextInt();
            newBoard.makeUserMove(move);
            checkWinner();
            if(newBoard.gameover)
                break;
            Cell bestMove=newBoard.findBestMove();
            System.out.println("Computer is thinking.....best Moves="+bestMove);
            newBoard.move(Player.AI, bestMove);
            newBoard.displayBoard();
            newBoard.resetMinimax();
            checkWinner();
        }while(!newBoard.gameover);
    }
    public static void main(String[] args) {
        Game game=new Game();
        char ch;
        do
        {
            game.newBoard=new Board();
            game.newBoard.displayBoard();
            System.out.println("How Should start the game(AI-0,USER-1)?");
            int start=sc.nextInt();
            switch (start) {
                case 0->game.makeFirstInput(true);
            
                case 1->game.makeFirstInput(false);
            }
            game.results();
            System.out.println("Do you want to play again? (y/n)");
            ch=sc.next().charAt(0);
        }while(ch=='y');
    }
}
