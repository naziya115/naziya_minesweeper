package sweeper;

class Flag {
    private Matrix matrix;
    private int totalclosed;

    void init(){
        matrix = new Matrix(Box.CLOSED);
        totalclosed = Ranges.getSize().x * Ranges.getSize().y;
    }
    Box getCoordinates(Coordinates c){
        return matrix.getMatrix(c);
    }

    public void setOpened(Coordinates c) {
        matrix.setMatrix(c, Box.OPENED);
        totalclosed--;
    }

    public void setFlag(Coordinates c) {
        matrix.setMatrix(c, Box.FLAGED);
    }

    public void setClosed(Coordinates c) {
        matrix.setMatrix(c, Box.CLOSED);
    }

    public void toggleFlag(Coordinates c) {
        switch (matrix.getMatrix(c)) {
            case FLAGED -> {
                setClosed(c);
                break;
            }
            case CLOSED -> {
                setFlag(c);
                break;
            }
        }
    }

    int getNumberOfClosed() {
        return totalclosed;
    }

    void setBombed(Coordinates c) {
        matrix.setMatrix(c, Box.BOMBED);
    }

    int getCountofFlaged(Coordinates c){
        int count = 0;
        for(Coordinates around : Ranges.getCoordinatesAround(c)){
            if(matrix.getMatrix(around) == Box.FLAGED){
                count++;
            }
        }
        return count;
    }

}
