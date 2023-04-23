package sweeper;

import javax.swing.plaf.ToolBarUI;

public class Coordinates {
    public int x;
    public int y;

    public Coordinates(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Coordinates)
            return ((Coordinates) object).x == x && ((Coordinates) object).y == y;
        return super.equals(object);
    }
}
