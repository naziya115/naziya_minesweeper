package sweeper;

class Matrix {
    private Box [][] matrix;

    Matrix(Box df_box){
        matrix =  new Box[Ranges.getSize().x][Ranges.getSize().y];
        for(Coordinates c : Ranges.getAll_coordinates()){
            matrix[c.x][c.y] = df_box;
        }
    }

    Box getMatrix(Coordinates c){
        if(Ranges.inRange(c)) {
            return matrix[c.x][c.y];
        }
        return null;
    }
    void setMatrix(Coordinates c, Box box){
        if(Ranges.inRange(c)) {
            matrix[c.x][c.y] = box;
        }
    }
}
