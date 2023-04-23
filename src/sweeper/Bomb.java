package sweeper;

import org.w3c.dom.ranges.Range;

class Bomb {
    private Matrix matrix;
    private int totalBombs;

    Bomb(int totalBombs){
        this.totalBombs = totalBombs;
        fixBombsCount();
    }

    void init(){
        matrix = new Matrix(Box.ZERO);
        for(int i = 0; i < totalBombs; i++) {
            placeBomb();
        }
    }
    Box getCoordinates(Coordinates c){
        return matrix.getMatrix(c);
    }
    private void fixBombsCount(){
        int max = Ranges.getSize().x * Ranges.getSize().y / 2;
        if(totalBombs > max){
            totalBombs = max;
        }
    }
    private void placeBomb(){
        while(true){
            Coordinates c = Ranges.getRandomCoordinates();
            if(Box.BOMB == matrix.getMatrix(c))
                continue;
            matrix.setMatrix(c, Box.BOMB);
            numIncrementalAroundBomb(c);
            break;
        }
    }
    private void numIncrementalAroundBomb(Coordinates c){
        for(Coordinates around : Ranges.getCoordinatesAround(c)){
            if(Box.BOMB != matrix.getMatrix(around)) {
                matrix.setMatrix(around, matrix.getMatrix(around).getnextNumber());
            }
        }
    }

    public int getTotalNumberofBombs() {
        return totalBombs;
    }
}
