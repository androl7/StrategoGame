public interface AlogorithmI {
    Move minMax(int DEPTH);
    Move alphaBeta(int DEPTH);
    Move SortAlphaBeta(int DEPTH);
    Move AlphaBetaDoubleEnemyPoints(int DEPTH);
    Move AlphaBetaWithoutEnemyPoints(int DEPTH);
}
