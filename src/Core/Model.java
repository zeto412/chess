package Core;

import Figures.Figure;
import Game.Field;
import Util.Position;

import java.util.Map;

public class Model {

    public enum Message {
        GAMESTART_MESSAGE;

        private static String get(Message messageName) {
            switch (messageName) {
                case GAMESTART_MESSAGE:
                    return "Game started";

                default:
                    return null;
            }
        }
    }

    static private Map<Character, Short> wordToNumber;

    private Field field;

    private static class Holder {
        static final Model instance = new Model();

    }

    public static Model getInstance() {
        return Holder.instance;
    }

    private Model() {

    }

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

    void init(final short width, final short height) {
        field = Field.getInstance(width, height);

    }

    String getMessage(Message message) {
        return Message.get(message);
    }

    void move(short startPositionX, short startPositionY,
              short endPositionX, short endPositionY) {
        Position start = new Position(startPositionX, startPositionY);
        Position end = new Position(endPositionX, endPositionY);

        final Figure currentFigure = field.getFigureByPosition(start);

        if (currentFigure == null) {
            return;
        }

        if (!currentFigure.canMove(start)) {
            return;
        }

        currentFigure.move(end);

        final Figure figureByPositionOnEnd = field.getFigureByPosition(end);
        if (figureByPositionOnEnd != null) {
            figureByPositionOnEnd.setDead();
        }

    }

    public Position parseStringWithPosition(String string) {
        String splitter = " ";
        if (!string.contains(splitter)) {
            return null;
        }

        String[] strings = string.split(splitter);

        if (strings[0].length() != 1 && strings[1].length() != 1) {
            return null;
        }

        Character character = strings[0].charAt(0);
        Short secondNum = Short.parseShort(strings[1]);
        Short firstNum = wordToNumber.get(character);

        return new Position(firstNum, secondNum);
    }



}
