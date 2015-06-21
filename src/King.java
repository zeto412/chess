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
    }

    public King(boolean _black, Position _position) {
        super(_black, _position);
    }

}
