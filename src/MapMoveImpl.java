import java.util.LinkedList;
import java.util.List;

public class MapMoveImpl implements Move {
    @Override
    public List<Position> getPossibleMoves(Figure figureToCheck, List<Position> moveMap) {
        List<Position> possibleMoves = new LinkedList<>();
        for (Position position : moveMap) {
            if (Game.field.isPositionBusy(position)) {
                final Figure figure = Game.field.getFigureByPosition(position);
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
