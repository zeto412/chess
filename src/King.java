import java.util.LinkedList;
import java.util.List;

public class King extends Figure {
    private static List<Position> _moveMap = new LinkedList<>();

    static{
        _moveMap.add(new Position(1, -1));
        _moveMap.add(new Position(0, 1));
        _moveMap.add(new Position(1, 1));
        _moveMap.add(new Position(1, 0));
        _moveMap.add(new Position(1, -1));
        _moveMap.add(new Position(0, -1));
        _moveMap.add(new Position(-1, -1));
        _moveMap.add(new Position(-1, 0));
    }

    @Override
    protected boolean checkMoveFigure(Position position) {
        for (Position checkPosition : getPossibleMoves()){
            if (checkPosition==position) {
                return true;
            }
        }

        return false;
    }

    @Override
    protected List<Position> getPossibleMoves() {
        List<Position> possibleMoves = new LinkedList<>();
        for (Position position : _moveMap){
            possibleMoves.add(new Position(
                    (_position.getX() + position.getX()),
                    (_position.getY() + position.getY())
            ));
        }
        return possibleMoves;
    }

}
