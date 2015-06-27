package Figures;

import Core.Exceptions.KingCantMoveException;
import Figures.Action.MapMoveImpl;
import Game.Field;
import Util.Position;

import java.util.List;

public class King extends Figure {


    {
        _moveMap.add(new Position(1, -1));
        _moveMap.add(new Position(0, 1));
        _moveMap.add(new Position(1, 1));
        _moveMap.add(new Position(1, 0));
        _moveMap.add(new Position(1, -1));
        _moveMap.add(new Position(0, -1));
        _moveMap.add(new Position(-1, -1));
        _moveMap.add(new Position(-1, 0));

        possibleMovesGetter = new MapMoveImpl();
    }

    public King(boolean _black, Position _position) {
        super(_black, _position);
    }

    @Override
    protected List<Position> getPossibleMoves() {
        List<Position> positionsToCheck = super.getPossibleMoves();
        Field field = Field.getInstance();
        for (Position kingPositionToMove : positionsToCheck) {
            for (Figure figure : field.getFigures()) {
                if (!this.isSameColor(figure)) {
                    for (Position enemyPosition : figure.getPossibleMoves()) {
                        if (enemyPosition == kingPositionToMove) {
                            positionsToCheck.remove(kingPositionToMove);
                        }
                    }
                }
            }
        }

        return positionsToCheck;
    }
}
