import java.util.ArrayList;
import java.util.Comparator;

class Algorithm implements AlogorithmI{
    private GameEngine ge;
    private int[][] board;

    Algorithm(GameEngine ge){
        this.ge = ge;
        board = ge.getBoard();
    }

    private int getNumberOfFreeMoves(){
        int numerOfFreeMoves=0;
        for(int i = 0;i<ge.getBoardSize();i++){
            for(int j = 0;j<ge.getBoardSize();j++){
                if(board[i][j]==0){
                    numerOfFreeMoves++;
                }
            }
        }
        return numerOfFreeMoves;
    }

     private ArrayList<Move> generateAvailableMoves(){
        ArrayList<Move> availableMoves = new ArrayList<>();
        for(int i = 0;i<ge.getBoardSize();i++){
            for(int j = 0;j<ge.getBoardSize();j++){
                if(board[i][j]==0){
                    availableMoves.add(new Move(i,j));
                }
            }
        }
        return availableMoves;
    }

    private int minMove(int numberOfFreeMoves, int depth, int score){

        if(depth==0||numberOfFreeMoves==0){
            return score;
        }

        int minEval = Integer.MAX_VALUE;
        ArrayList<Move> availableMoves = generateAvailableMoves();

        for(Move move : availableMoves){

            int pointsForMove = ge.addPlayerScore(move.getX(),move.getY());
            board[move.getX()][move.getY()] = 1;
            int points = maxMove(numberOfFreeMoves-1,depth-1,score - pointsForMove);
            if(points < minEval){
                minEval = points;
            }
            board[move.getX()][move.getY()] = 0;
        }
        return minEval;
    }

    private int maxMove(int numberOfFreeMoves, int depth, int score){

        if(depth==0||numberOfFreeMoves==0){
            return score;
        }

        int maxEval = Integer.MIN_VALUE;
        ArrayList<Move> availableMoves = generateAvailableMoves();

        for(Move move : availableMoves){

            int pointsForMove = ge.addPlayerScore(move.getX(),move.getY());
            board[move.getX()][move.getY()] = 1;
            int points = minMove(numberOfFreeMoves-1,depth-1,score + pointsForMove);
            if(points > maxEval){
                maxEval = points;
            }
            board[move.getX()][move.getY()] = 0;
        }
        return maxEval;
    }

    public Move minMax(int DEPTH){
        int maxEval = Integer.MIN_VALUE;
        int numberOfFreeMoves = getNumberOfFreeMoves();
        Move bestMove = new Move(-1,-1);
        ArrayList<Move> availableMoves = generateAvailableMoves();

        for(Move move : availableMoves){
            int pointsForMove = ge.addPlayerScore(move.getX(),move.getY());
            board[move.getX()][move.getY()] = 1;
            int points = minMove(numberOfFreeMoves-1,DEPTH-1,pointsForMove);
            if(points>maxEval){
                maxEval= points;
                bestMove = move;
            }
            board[move.getX()][move.getY()] =0;
        }
        return bestMove;
    }

    private int minMoveAlphaBeta(int numberOfFreeMoves, int depth, int score,int alpha, int beta){

        if(depth==0||numberOfFreeMoves==0){
            return score;
        }


        ArrayList<Move> availableMoves = generateAvailableMoves();

        for(Move move : availableMoves){

            int pointsForMove = ge.addPlayerScore(move.getX(),move.getY());
            board[move.getX()][move.getY()] = 1;
            int points = maxMoveAlphaBeta(numberOfFreeMoves-1,depth-1,score - pointsForMove, alpha, beta);
            if(points < beta){
                beta = points;
            }
            board[move.getX()][move.getY()] = 0;
            if(alpha>=beta){
                break;
            }
        }
        return beta;
    }

    private int maxMoveAlphaBeta(int numberOfFreeMoves, int depth, int score, int alpha, int beta){

        if(depth==0||numberOfFreeMoves==0){
            return score;
        }

        ArrayList<Move> availableMoves = generateAvailableMoves();

        for(Move move : availableMoves){

            int pointsForMove = ge.addPlayerScore(move.getX(),move.getY());
            board[move.getX()][move.getY()] = 1;
            int points = minMoveAlphaBeta(numberOfFreeMoves-1,depth-1,score + pointsForMove, alpha,beta);
            if(points > alpha){
                alpha = points;
            }
            board[move.getX()][move.getY()] = 0;
            if(alpha >=beta){
                break;
            }
        }
        return alpha;
    }

    public Move alphaBeta(int DEPTH){
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;

        int numberOfFreeMoves = getNumberOfFreeMoves();
        Move bestMove = new Move(-1,-1);
        ArrayList<Move> availableMoves = generateAvailableMoves();

        for(Move move : availableMoves){
            int pointsForMove = ge.addPlayerScore(move.getX(),move.getY());
            board[move.getX()][move.getY()] = 1;
            int points = minMoveAlphaBeta(numberOfFreeMoves-1,DEPTH-1,pointsForMove,alpha,beta);
            if(points>alpha){
                alpha= points;
                bestMove = move;
            }
            board[move.getX()][move.getY()] =0;
        }
        return bestMove;
    }

    private int minMoveSortAlphaBeta(int numberOfFreeMoves, int depth, int score,int alpha, int beta){

        if(depth==0||numberOfFreeMoves==0){
            return score;
        }

        ArrayList<Move> availableMoves = generateAvailableMoves();
        sortMovesArrayMin(availableMoves);

        for(Move move : availableMoves){

            board[move.getX()][move.getY()] = 1;
            int points = maxMoveSortAlphaBeta(numberOfFreeMoves-1,depth-1,score - move.getPoints(), alpha, beta);
            if(points < beta){
                beta = points;
            }
            board[move.getX()][move.getY()] = 0;
            if(alpha>=beta){
                break;
            }
        }
        return beta;
    }

    private int maxMoveSortAlphaBeta(int numberOfFreeMoves, int depth, int score, int alpha, int beta){

        if(depth==0||numberOfFreeMoves==0){
            return score;
        }

        ArrayList<Move> availableMoves = generateAvailableMoves();
        sortMovesArrayMax(availableMoves);

        for(Move move : availableMoves){

            board[move.getX()][move.getY()] = 1;
            int points = minMoveSortAlphaBeta(numberOfFreeMoves-1,depth-1,score + move.getPoints(), alpha,beta);
            if(points > alpha){
                alpha = points;
            }
            board[move.getX()][move.getY()] = 0;
            if(alpha >=beta){
                break;
            }
        }
        return alpha;
    }


    public Move SortAlphaBeta(int DEPTH){
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;

        int numberOfFreeMoves = getNumberOfFreeMoves();
        Move bestMove = new Move(-1,-1);
        ArrayList<Move> availableMoves = generateAvailableMoves();

        //sort Moves
        sortMovesArrayMax(availableMoves);

        for(Move move : availableMoves){
            board[move.getX()][move.getY()] = 1;
            int points = minMoveSortAlphaBeta(numberOfFreeMoves-1,DEPTH-1,move.getPoints(),alpha,beta);
            if(points>alpha){
                alpha= points;
                bestMove = move;
            }
            board[move.getX()][move.getY()] =0;
        }
        return bestMove;
    }


    private int minMoveAlphaBetaDoubleEnemyPoints(int numberOfFreeMoves, int depth, int score,int alpha, int beta){

        if(depth==0||numberOfFreeMoves==0){
            return score;
        }

        ArrayList<Move> availableMoves = generateAvailableMoves();

        for(Move move : availableMoves){
            int pointsForMove = ge.addPlayerScore(move.getX(),move.getY());
            board[move.getX()][move.getY()] = 1;
            int points = maxMoveAlphaBetaDoubleEnemyPoints(numberOfFreeMoves-1,depth-1,score - (pointsForMove*2), alpha, beta);
            if(points < beta){
                beta = points;
            }
            board[move.getX()][move.getY()] = 0;
            if(alpha>=beta){
                break;
            }
        }
        return beta;
    }

    private int maxMoveAlphaBetaDoubleEnemyPoints(int numberOfFreeMoves, int depth, int score, int alpha, int beta){

        if(depth==0||numberOfFreeMoves==0){
            return score;
        }

        ArrayList<Move> availableMoves = generateAvailableMoves();

        for(Move move : availableMoves){
            int pointsForMove = ge.addPlayerScore(move.getX(),move.getY());
            board[move.getX()][move.getY()] = 1;
            int points = minMoveAlphaBetaDoubleEnemyPoints(numberOfFreeMoves-1,depth-1,score + pointsForMove, alpha,beta);
            if(points > alpha){
                alpha = points;
            }
            board[move.getX()][move.getY()] = 0;
            if(alpha >=beta){
                break;
            }
        }
        return alpha;
    }

    public Move AlphaBetaDoubleEnemyPoints(int DEPTH){
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;

        int numberOfFreeMoves = getNumberOfFreeMoves();
        Move bestMove = new Move(-1,-1);
        ArrayList<Move> availableMoves = generateAvailableMoves();


        for(Move move : availableMoves){
            int pointsForMove = ge.addPlayerScore(move.getX(),move.getY());
            board[move.getX()][move.getY()] = 1;
            int points = minMoveAlphaBetaDoubleEnemyPoints(numberOfFreeMoves-1,DEPTH-1,pointsForMove,alpha,beta);
            if(points>alpha){
                alpha= points;
                bestMove = move;
            }
            board[move.getX()][move.getY()] =0;
        }
        return bestMove;
    }

    private int minMoveAlphaBetaWithoutEnemyPoints(int numberOfFreeMoves, int depth, int score,int alpha, int beta){

        if(depth==0||numberOfFreeMoves==0){
            return score;
        }

        ArrayList<Move> availableMoves = generateAvailableMoves();

        for(Move move : availableMoves){
            board[move.getX()][move.getY()] = 1;
            int points = maxMoveAlphaBetaWithoutEnemyPoints(numberOfFreeMoves-1,depth-1,score, alpha, beta);
            if(points < beta){
                beta = points;
            }
            board[move.getX()][move.getY()] = 0;
            if(alpha>=beta){
                break;
            }
        }
        return beta;
    }

    private int maxMoveAlphaBetaWithoutEnemyPoints(int numberOfFreeMoves, int depth, int score, int alpha, int beta){

        if(depth==0||numberOfFreeMoves==0){
            return score;
        }

        ArrayList<Move> availableMoves = generateAvailableMoves();

        for(Move move : availableMoves){
            int pointsForMove = ge.addPlayerScore(move.getX(),move.getY());
            board[move.getX()][move.getY()] = 1;
            int points = minMoveAlphaBetaWithoutEnemyPoints(numberOfFreeMoves-1,depth-1,score + pointsForMove, alpha,beta);
            if(points > alpha){
                alpha = points;
            }
            board[move.getX()][move.getY()] = 0;
            if(alpha >=beta){
                break;
            }
        }
        return alpha;
    }

    public Move AlphaBetaWithoutEnemyPoints(int DEPTH){
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;

        int numberOfFreeMoves = getNumberOfFreeMoves();
        Move bestMove = new Move(-1,-1);
        ArrayList<Move> availableMoves = generateAvailableMoves();


        for(Move move : availableMoves){
            int pointsForMove = ge.addPlayerScore(move.getX(),move.getY());
            board[move.getX()][move.getY()] = 1;
            int points = minMoveAlphaBetaWithoutEnemyPoints(numberOfFreeMoves-1,DEPTH-1,pointsForMove,alpha,beta);
            if(points>alpha){
                alpha= points;
                bestMove = move;
            }
            board[move.getX()][move.getY()] =0;
        }
        return bestMove;
    }

    private void sortMovesArrayMax(ArrayList<Move> moves){
        for(Move move:moves){
            move.setPoints(ge.addPlayerScore(move.getX(),move.getY()));
        }
        moves.sort((Move x,Move y)->Integer.compare(y.getPoints(),x.getPoints()));

    }

    private void sortMovesArrayMin(ArrayList<Move> moves){
        for(Move move:moves){
            move.setPoints(ge.addPlayerScore(move.getX(),move.getY()));
        }
        moves.sort(Comparator.comparingInt(Move::getPoints));

    }
}

