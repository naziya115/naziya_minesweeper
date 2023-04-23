package sweeper;

public class Game {
    private Bomb bomb;
    private Flag flag;
    private GameState gameState;

    public GameState getGameState() {
        return gameState;
    }

    public void init(){
        bomb.init();
        flag.init();
        gameState = GameState.PLAYED;
    }

    public Game(int columns, int rows, int totalBombs){
        Ranges.setSize(new Coordinates(columns, rows));
        bomb = new Bomb(totalBombs);
        flag = new Flag();
    }

    public Box getBox(Coordinates c){
        if(flag.getCoordinates(c) == Box.OPENED)
            return bomb.getCoordinates(c);
        else
            return flag.getCoordinates(c);
    }
    public void pressLeftButton(Coordinates c){
        if(gameOver())
            return;
       Open(c);
       checkWinner();
    }

    private void Open(Coordinates c){
        switch (flag.getCoordinates(c)){
            case OPENED: setOpenedtoClosedAroundNum(c); return;
            case FLAGED: return;
            case CLOSED:
                switch (bomb.getCoordinates(c)){
                    case ZERO: OpenAround(c); return;
                    case BOMB: OpenBomb(c); return;
                    default: flag.setOpened(c); return;
                }
        }
    }

    void setOpenedtoClosedAroundNum(Coordinates c){
        if(bomb.getCoordinates(c) != Box.BOMB){
            if(flag.getCountofFlaged(c) == bomb.getCoordinates(c).getNumber())
                for(Coordinates around : Ranges.getCoordinatesAround(c))
                    if(flag.getCoordinates(around) == Box.CLOSED)
                        Open(around);
        }
    }
    private void OpenBomb(Coordinates c) {
        gameState = GameState.BOMBED;
        flag.setBombed(c);
    }

    private void checkWinner(){
        if(gameState == GameState.PLAYED){
            if(flag.getNumberOfClosed() == bomb.getTotalNumberofBombs())
                gameState = GameState.WINNER;
        }
    }

    private void OpenAround(Coordinates c) {
        flag.setOpened(c);
        for(Coordinates around : Ranges.getCoordinatesAround(c)){
            Open(around);
        }

    }

    public void pressRightButton(Coordinates c) {
        if(gameOver()) return;
        flag.toggleFlag(c);
    }

    private boolean gameOver() {
        if(gameState == GameState.PLAYED)
            return false;
        init();
        return true;
    }
}
