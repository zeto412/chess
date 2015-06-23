import java.util.List;

public interface Move {

    List<Position> getPossibleMoves(Figure figureToCheck, List<Position> moveMap);

}
