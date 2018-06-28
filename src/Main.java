public class Main {
    public static void main(String[] args) {
        GameEngine ge = new GameEngine(6);
        GUI gui = new GUI(ge);
        gui.display();

        /*long startTime = System.currentTimeMillis();
        BotsBattle botsBattle = new BotsBattle(6);
        botsBattle.start();
        long endTime = System.currentTimeMillis();
        System.out.println("\nCzas: "+(endTime - startTime)+" ms");*/
    }
}
