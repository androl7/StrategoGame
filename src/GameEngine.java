public class GameEngine {

    private boolean firstPlayerTurn;
    private int round;
    private int boardSize;
    private int firstPlayerScore;
    private int secondPlayerScore;
    private int[][] board;

    GameEngine(int boardSize){
        this.boardSize = boardSize;
        board = new int[boardSize][boardSize];
        this.round = 1;
    }

    void gameLoop(int x, int y){
            firstPlayerTurn = round % 2 == 1;


            //add score
            if (firstPlayerTurn) {

                if(board[x][y]==0){
                    board[x][y] = 1;
                }else {
                    return;
                }
               firstPlayerScore = firstPlayerScore+addPlayerScore(x,y);
               round++;
            } else {
                if(board[x][y]==0){
                    board[x][y] = 1;
                }else {
                    return;
                }
                secondPlayerScore = secondPlayerScore+addPlayerScore(x,y);
                round++;
            }
            printBoard();
            System.out.println("RES:\n1. "+firstPlayerScore+"\n2. "+secondPlayerScore);
        }
   // }

    int addPlayerScore(int row, int col){
        int score=0;
        boolean getScore=false;
        int pointsToAdd=1; // 1 bo liczmy pole na którym stoi pionek

        //check up to down
        for(int i=0;i<boardSize;i++){
            if(i!=row)pointsToAdd++;
            if(board[i][col]==0){
                getScore = false;
                break;
            }else if(board[i][col]==1){
                getScore=true;
            }
        }
        if(getScore) score=score+pointsToAdd;
        getScore=false;
        pointsToAdd=1;

        //check left to right
        for(int j=0;j<boardSize;j++){
            if(j!=col)pointsToAdd++;
            if(board[row][j]==0){
                getScore = false;
                break;
            }else if(board[row][j]==1){
                getScore=true;
            }
        }
        if(getScore) score=score+pointsToAdd;
        getScore=false;
        pointsToAdd =1;

        //check diagonal left-down to right-top
        //right-top
        for(int i=row-1, j=col+1;i>=0&&j<boardSize;i--,j++){
            pointsToAdd++;
            if(board[i][j]==0){
                getScore = false;
                break;
            }else if(board[i][j]==1){
                getScore = true;
            }
        }

        //left-down
        if(getScore||pointsToAdd==1) {
            for (int i = row + 1, j = col - 1; i < boardSize && j >= 0; i++, j--) {
                pointsToAdd++;
                if (board[i][j] == 0) {
                    getScore = false;
                    break;
                } else if (board[i][j] == 1) {
                    getScore = true;
                }
            }
        }

        if(getScore) score=score+pointsToAdd;
        pointsToAdd=1;
        getScore=false;


        //check diagonal left-up to right-down
        // left-up
        for(int i=row-1, j=col-1;i>=0&&j>=0;i--,j--){
            pointsToAdd++;
            if(board[i][j]==0){
                getScore = false;
                break;
            }else if(board[i][j]==1){
                getScore = true;
            }
        }

        //right-down
        if(getScore||pointsToAdd==1) {
            for (int i = row + 1, j = col + 1; i < boardSize && j < boardSize; i++, j++) {
                pointsToAdd++;
                if (board[i][j] == 0) {
                    getScore = false;
                    break;
                } else if (board[i][j] == 1) {
                    getScore = true;
                }
            }
        }

        if(getScore) score=score+pointsToAdd;

        return score;
    }

     boolean checkEndOfGame(){
        for(int i=0;i<boardSize;i++){
            for(int j=0;j<boardSize;j++){
                if(board[i][j]==0) {
                    return true;
                }
            }
        }
        return false;
    }


    private void printBoard(){
        for (int[] aBoard : board) {
            for (int anABoard : aBoard) {
                System.out.print(anABoard + " ");
            }
            System.out.println();
        }
    }


    public int getBoardSize() {
        return boardSize;
    }

    public boolean isFirstPlayerTurn() {
        return firstPlayerTurn;
    }

    public int getFirstPlayerScore() {
        return firstPlayerScore;
    }

    public int getSecondPlayerScore() {
        return secondPlayerScore;
    }

    public int getRound() {
        return round;
    }

    public int[][] getBoard() {
        return board;
    }
}
