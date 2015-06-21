import java.util.ArrayList;

public class Field {
    public final static ArrayList<Figure> field = new ArrayList<Figure>(32);

    public Field(){
        boolean black = true;
        boolean white = false;
        field.add(new King(black,new Position(5,8)));
        field.add(new King(white,new Position(5,1)));
    }

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
