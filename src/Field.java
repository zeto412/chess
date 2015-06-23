import java.util.ArrayList;

public class Field {

    private static class Holder {
        static final Field instance = new Field();
    }

    public static Field getInstance() {
        return Holder.instance;
    }

    private Field() {
        boolean black = true;
        boolean white = false;
        field.add(new King(black, new Position(5, 8)));
        field.add(new King(white, new Position(5, 1)));
        field.add(new Queen(black, new Position(4, 8)));
        field.add(new Queen(white, new Position(4, 1)));
        field.add(new Knight(black, new Position(2, 8)));
        field.add(new Knight(black, new Position(7, 8)));
        field.add(new Knight(white, new Position(2, 1)));
        field.add(new Knight(white, new Position(7, 1)));
        field.add(new Rook(black, new Position(1, 8)));
        field.add(new Rook(black, new Position(8, 8)));
        field.add(new Rook(white, new Position(1, 1)));
        field.add(new Rook(white, new Position(8, 1)));
        field.add(new Bishop(black, new Position(3, 8)));
        field.add(new Bishop(black, new Position(6, 8)));
        field.add(new Bishop(white, new Position(3, 1)));
        field.add(new Bishop(white, new Position(6, 1)));
        for (int i = 1; i <= Game.WIDTH; i++) {
            field.add(new Pawn(black, new Position(7, i)));
        }
        for (int i = 1; i <= Game.WIDTH; i++) {
            field.add(new Pawn(white, new Position(2, i)));
        }
    }

    public final static ArrayList<Figure> field = new ArrayList<Figure>(32);

    public boolean isPositionBusy(Position position){
        return (getFigureByPosition(position) != null);
    }

    public boolean isFigureBlack(Position position){
        return getFigureByPosition(position).isBlack();
    }

    public Figure getFigureByPosition(Position position) {
        for(Figure figure : field) {
            if(figure.getPosition() == position) {
                return figure;
            }
        }
        return null;
    }

}
