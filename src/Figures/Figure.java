package Figures;

import Core.Exceptions.KingCantMoveException;
import Figures.Action.Move;
import Game.Launcher;
import Util.Position;

import java.util.LinkedList;
import java.util.List;

public abstract class Figure {
    private boolean _black;
    protected Position _position;
    private boolean _dead;

    public boolean isDead() {
        return _dead;
    }

    public void setDead() {
        _dead = true;
    }

    protected List<Position> _moveMap = new LinkedList<>();

    protected Move possibleMovesGetter;

    public Figure(boolean _black, Position _position) {
        this._black = _black;
        this._position = _position;
        this._dead = false;
    }

    public void move(Position newPosition) {
        _position = newPosition;
    }

    protected boolean checkMoveFigure(Position position) throws KingCantMoveException {
        List<Position> possibleMoves = getPossibleMoves();
        if (this instanceof King && possibleMoves.isEmpty()) {
            throw new KingCantMoveException();
        }
        for (Position checkPosition : possibleMoves) {
            if (checkPosition == position) {
                return true;
            }
        }

        return false;
    }

    protected List<Position> getPossibleMoves() {
        return possibleMovesGetter.getPossibleMoves(this, _moveMap);
    }

    public boolean canMove(Position position) throws KingCantMoveException {
        boolean result = false;
        if (!isOutOfBorder(position)) {
            result = checkMoveFigure(position);
        }
        return result;
    }


    private boolean isOutOfBorder(Position position) {
        return ((1 <= position.getX()) && (position.getX() >= Launcher.WIDTH)
                && (1 <= position.getY()) && (position.getY() >= Launcher.HEIGHT));
    }

    public Position getPosition() {
        return _position;
    }

    public boolean isBlack() {
        return _black;
    }

    public boolean isSameColor(Figure figure) {
        return this.isBlack() == figure.isBlack();
    }
}
