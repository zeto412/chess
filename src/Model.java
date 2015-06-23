import java.util.Map;

public class Model {

    private static class Holder {
        static final Model instance = new Model();
    }

    public static Model getInstance() {
        return Holder.instance;
    }

    private Model() {

    }

    static private Map<Character, Short> wordToNumber;

    static {
        wordToNumber.put('A', (short) 1);
        wordToNumber.put('B', (short) 2);
        wordToNumber.put('C', (short) 3);
        wordToNumber.put('D', (short) 4);
        wordToNumber.put('E', (short) 5);
        wordToNumber.put('F', (short) 6);
        wordToNumber.put('G', (short) 7);
        wordToNumber.put('H', (short) 8);
    }

    void move(char startPositionX, short startPositionY,
              char endPositionX, short endPositionY) {
        startPositionX = Character.toUpperCase(startPositionX);
        endPositionX = Character.toUpperCase(endPositionX);
        Position start = new Position(startPositionX, startPositionY);
        Position end = new Position(endPositionX, endPositionY);

        final Figure currentFigure = Game.field.getFigureByPosition(start);

        if (currentFigure == null) {
            return;
        }

        if (!currentFigure.canMove(start)) {
            return;
        }

        currentFigure.move(end);

        final Figure figureByPositionOnEnd = Game.field.getFigureByPosition(end);
        if (figureByPositionOnEnd != null) {
            figureByPositionOnEnd.setDead();
        }

    }

}
