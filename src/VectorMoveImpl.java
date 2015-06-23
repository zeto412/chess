import java.util.List;

public class VectorMoveImpl implements Move {

    private Move possibleMovesByMap;

    {
        possibleMovesByMap = new MapMoveImpl();
    }

    @Override
    public List<Position> getPossibleMoves(Figure figureToCheck, List<Position> moveMap) {
        List<Position> unchekedMoves = possibleMovesByMap.getPossibleMoves(figureToCheck, moveMap);
        Next:
        for (Position position : unchekedMoves) {
            final short dx = (short) (Math.max(position.getX(), figureToCheck.getPosition().getX()) + Math.min(position.getX(), figureToCheck.getPosition().getX()));
            final short dy = (short) (Math.max(position.getY(), figureToCheck.getPosition().getY()) + Math.min(position.getY(), figureToCheck.getPosition().getY()));
            for (short x = (short) (figureToCheck.getPosition().getX() + dx); x < Game.WIDTH; x += dx) {
                for (short y = (short) (figureToCheck.getPosition().getY() + dy); y < Game.HEIGHT; y += dy) {
                    Position currentCheckPosition = new Position(x, y);
                    Figure checkFigure = Game.field.getFigureByPosition(currentCheckPosition);
                    if (checkFigure == null || (checkFigure.isDead())) {
                        unchekedMoves.add(currentCheckPosition);
                    } else if (!checkFigure.isSameColor(figureToCheck)) {
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