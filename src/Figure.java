import java.util.LinkedList;
import java.util.List;

public abstract class Figure {
    private boolean _black;
    protected Position _position;
    private boolean _dead;
    protected List<Position> _moveMap = new LinkedList<>();

    public Figure(boolean _black, Position _position) {
        this._black = _black;
        this._position = _position;
        this._dead = false;
    }

    public void move(Position newPosition){
        _position = newPosition;
    }

    protected boolean checkMoveFigure(Position position) {
        for (Position checkPosition : getPossibleMoves()){
            if (checkPosition==position) {
                return true;
            }
        }

        return false;
    }

    protected List<Position> getPossibleMoves() {
        List<Position> possibleMoves = new LinkedList<>();
        for (Position position : _moveMap){
            if (Game._field.isPositionBusy(position)){
                if (isSameColor(Game._field.getFigureByPosition(position))) {
                    possibleMoves.add(new Position(
                            (_position.getX() + position.getX()),
                            (_position.getY() + position.getY())
                    ));
                }
            }
        }
        return possibleMoves;
    }

    public boolean checkMove(Position position){
        boolean result = false;
        if (!isOutOfBorder(position)){
           result = checkMoveFigure(position);
        }
        return result;
    }

    private boolean isOutOfBorder(Position position) {
        return ((1 <= position.getX()) && (position.getX() >= Game.WIDTH)
                && (1 <= position.getY()) && (position.getY()>=Game.HEIGHT));
    }




    public Position getPosition() {
        return _position;
    }

    public boolean isBlack() {
        return _black;
    }

    public boolean isSameColor(Figure figure){
        return this.isBlack() == figure.isBlack();
    }

}
