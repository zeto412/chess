package Figures.Action;

import Figures.Figure;
import Game.Field;
import Util.Position;

import java.util.LinkedList;
import java.util.List;

public class MapMoveImpl implements Move {
    @Override
    public List<Position> getPossibleMoves(Figure figureToCheck, List<Position> moveMap) {
        List<Position> possibleMoves = new LinkedList<>();
        Field field = Field.getInstance();

        for (int i = 0; i < moveMap.size(); ) {
            Position position = new Position(
                    (figureToCheck.getPosition().getX() + moveMap.get(i).getX()),
                    (figureToCheck.getPosition().getY() + moveMap.get(i).getY())
            );
            moveMap.set(i, position);
            i++;
        }
        for (int i = 0; i < moveMap.size(); ) {
            if (field.isOutOfBorder(moveMap.get(i))) {
                moveMap.remove(i);
                i = 0;
            } else {
                i++;
            }
        }

        for (Position mapPosition : moveMap) {

            if (field.isPositionBusy(mapPosition)) {
                final Figure figure = field.getFigureByPosition(mapPosition);
                if (figure != null || !figure.isDead()) {
                    if (!figureToCheck.isSameColor(figure)) {
                        possibleMoves.add(mapPosition);
                    }
                }
            } else {
                final Figure figure = field.getFigureByPosition(mapPosition);
                if (figure == null || figure.isDead()) {
                    possibleMoves.add(mapPosition);
                }
            }
        }
        return possibleMoves;
    }
}
