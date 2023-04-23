package sweeper;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Ranges {
    private static Coordinates size;
    private static final Random rand = new Random();
    private static ArrayList<Coordinates> all_coordinates;

    static void setSize(Coordinates _size){
        size = _size;
        all_coordinates = new ArrayList<>();
        for(int i = 0; i < size.y; i++){
            for(int j = 0; j < size.x; j++){
                all_coordinates.add(new Coordinates(i, j));
            }
        }
    }

    public static Coordinates getSize() {
        return size;
    }

    public static ArrayList<Coordinates> getAll_coordinates(){
        return all_coordinates;
    }

    static boolean inRange(Coordinates c){
        return c.x >= 0 && c.x < size.x &&
                c.y >= 0 && c.y < size.y;
    }
    static Coordinates getRandomCoordinates(){
        return new Coordinates(rand.nextInt(size.x), rand.nextInt(size.y));
    }
    static ArrayList<Coordinates> getCoordinatesAround(Coordinates c){
        Coordinates around;
        ArrayList<Coordinates> arrayList = new ArrayList<Coordinates>();
        for(int x = c.x - 1; x<= c.x + 1; x++){
            for(int y = c.y - 1; y<= c.y + 1; y++){
                if(inRange(around = new Coordinates(x, y))){
                    if(!around.equals(c)){
                        arrayList.add(around);
                    }
                }
            }
        }
        return arrayList;
    }
}
