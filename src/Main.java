public class Main {
    Player sabrina = new Player("sabrina");
    Player other = new Player("other person");
    Game newGame = new Game(sabrina, other);
    newGame.playGame();
}