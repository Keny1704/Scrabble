package Main;

public class Scrabble {

    public static void main(String[] args) {
        Scrabble juego = new Scrabble();
    }

    public Scrabble() {
        ScrabbleMain inst = new ScrabbleMain();
        inst.startNewGameButtonPressed();
        inst.challengeButtonPressed();
        inst.endTurnButtonPressed();
        inst.rackButton1Pressed();
        inst.rackButton2Pressed();
        inst.rackButton3Pressed();
        inst.rackButton4Pressed();
        inst.rackButton5Pressed();
        inst.rackButton6Pressed();
        inst.rackButton7Pressed();
        inst.spaceButtonPressed();
    }
}

