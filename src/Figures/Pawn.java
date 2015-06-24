package Figures;

import Figures.Action.MapMoveImpl;
import Game.Launcher;
import Util.Position;

import java.util.List;

public class Pawn extends Figure {
    private boolean wasMove = false;
    private Position _leftMove;
    private Position _rightMove;
    private Position _doubleMove;
    {
        _leftMove = new Position(-1,1);
        _rightMove = new Position(1,1);
        _doubleMove = new Position(0,2);
        _moveMap.add(new Position(0, 1));
        _moveMap.add(_doubleMove);
        _moveMap.add(_leftMove);
        _moveMap.add(_rightMove);

        possibleMovesGetter = new MapMoveImpl();
    }

    public Pawn(boolean _black, Position _position) {
        super(_black, _position);
        if (_black){
            for (Position position : _moveMap) {
                position.setY((short) (position.getY()*-1));
            }
        }
    }

    @Override
    public void move(Position newPosition) {
        super.move(newPosition);
        wasMove = true;
    }

    @Override
    protected List<Position> getPossibleMoves() {
        List<Position> unchekedPositions = super.getPossibleMoves();
        Position leftPosition = new Position(
                this.getPosition().getX()+_leftMove.getX(),
                this.getPosition().getY()+_leftMove.getY()
        );
        Position rightPosition = new Position(
                this.getPosition().getX()+_rightMove.getX(),
                this.getPosition().getY()+_rightMove.getY()
        );
        Position doublePosition = new Position(
                this.getPosition().getX()+_doubleMove.getX(),
                this.getPosition().getY()+_doubleMove.getY()
        );

        for (Position position : unchekedPositions) {
            if(position == leftPosition || position == rightPosition){
                if (!Launcher.field.isPositionBusy(position)) {
                    unchekedPositions.remove(position);
                }
            }
            if (position == doublePosition){
                if (wasMove){
                    unchekedPositions.remove(position);
                }
            }
        }
        return unchekedPositions;
    }

}
