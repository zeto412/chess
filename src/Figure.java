import java.util.List;

public abstract class Figure {
    private boolean _isBlack;
    protected Position _position;

    public void move(Position newPosition){
        _position = newPosition;
    }

    protected abstract boolean checkMoveFigure(Position position);

    protected abstract List<Position> getPossibleMoves();

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




    public Position getPosotion() {
        return _position;
    }
}
