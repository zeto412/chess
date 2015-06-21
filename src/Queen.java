import java.util.List;

public class Queen extends Figure {
    {
        _moveMap.add(new Position(1, -1));
        _moveMap.add(new Position(0, 1));
        _moveMap.add(new Position(1, 1));
        _moveMap.add(new Position(1, 0));
        _moveMap.add(new Position(1, -1));
        _moveMap.add(new Position(0, -1));
        _moveMap.add(new Position(-1, -1));
        _moveMap.add(new Position(-1, 0));
    }

    public Queen(boolean _black, Position _position) {
        super(_black, _position);
    }

    @Override
    protected List<Position> getPossibleMoves() {
        List<Position> unchekedMoves = super.getPossibleMoves();
        Next:
        for (Position position : unchekedMoves) {
            final short dx = (short) (Math.max(position.getX(), this.getPosition().getX()) + Math.min(position.getX(), this.getPosition().getX()));
            final short dy = (short) (Math.max(position.getY(), this.getPosition().getY()) + Math.min(position.getY(), this.getPosition().getY()));
            for (short x = (short) (this.getPosition().getX() + dx); x < Game.WIDTH; x += dx) {
                for (short y = (short) (this.getPosition().getY() + dy); y < Game.HEIGHT; y += dy) {
                    Position currentCheckPosition = new Position(x, y);
                    Figure checkFigure = Game._field.getFigureByPosition(currentCheckPosition);
                    if (checkFigure == null) {
                        unchekedMoves.add(currentCheckPosition);
                    } else if (!checkFigure.isSameColor(this)) {
                        unchekedMoves.add(currentCheckPosition);
                        continue Next;
                    } else {
                        continue Next;
                    }
                }
            }
        }

        return unchekedMoves;

    }
}
