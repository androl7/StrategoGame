class BotsBattle {

    private AlogorithmI bot1;
    private AlogorithmI bot2;
    private GameEngine ge;


    BotsBattle(int boardSize){
        ge = new GameEngine(boardSize);
        bot1 = new Algorithm(ge);
        bot2 = new Algorithm(ge);
    }

    void start(){
        while(ge.checkEndOfGame()) {
            Move bot1Move = bot1.SortAlphaBeta(4);
            ge.gameLoop(bot1Move.getX(),bot1Move.getY());
            if(ge.checkEndOfGame()) {
                Move bot2Move = bot2.SortAlphaBeta(4);
                ge.gameLoop(bot2Move.getX(), bot2Move.getY());
            }
        }
    }



}
