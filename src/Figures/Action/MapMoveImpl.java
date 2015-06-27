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
        for (Position position : moveMap) {
            if (field.isPositionBusy(position)) {
                final Figure figure = field.getFigureByPosition(position);
                if (!figure.isDead()) {
                    if (figureToCheck.isSameColor(figure)) {
                        possibleMoves.add(new Position(
                                (figureToCheck.getPosition().getX() + position.getX()),
                                (figureToCheck.getPosition().getY() + position.getY())
                        ));
                    }
                }
            }
        }
        return possibleMoves;
    }
}
