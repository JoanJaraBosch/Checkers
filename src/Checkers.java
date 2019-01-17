public class Checkers {
    private Player black , white;

    public Checkers(){
        black= new Player("Black");
        white = new Player("White");
    }

    public Player getBlack() {
        return black;
    }

    public Player getWhite() {
        return white;
    }

    public void moveChecker(int xBefore, int yBefore, int x, int y, String[][] board){
            //si es pot fer el moviment posem true actualitzem tauler i actualitzem posicions
        updateBoard(black,white,xBefore,yBefore,x,y,board);
        if(white.getPiece().getBloqiedPieces()!=white.getPiece().getNumberPieces() && black.isTurn()){
            white.setTurn(true);
            black.setTurn(false);
        }else if(black.getPiece().getBloqiedPieces()!=black.getPiece().getNumberPieces() && white.isTurn()){
            white.setTurn(false);
            black.setTurn(true);
        }
    }

    private void updateBoard(Player black, Player white, int posXBefore, int posYBefore, int posX, int posY,String[][] board) {
        board[posXBefore][posYBefore]="?";
        if(black.isTurn()) {
            board[posX][posY]="B";
            int auxX = posXBefore-posX;
            int auxY = posYBefore-posY;
            if(auxX!=1){
                if(auxY==-2){
                    board[posX-1][posY+1]="?";
                }else{
                    board[posX-1][posY-1]="?";
                }
                white.getPiece().setNumberPieces(white.getPiece().getNumberPieces()-1);
            }
        }
        else{
            board[posX][posY]="W";
            int auxX = posXBefore-posX;
            int auxY = posYBefore-posY;
            if(auxX!=1){
                if(auxY==-2){
                    board[posX-1][posY+1]="?";
                }else{
                    board[posX-1][posY-1]="?";
                }
                black.getPiece().setNumberPieces(black.getPiece().getNumberPieces()-1);
            }
        }

        //contar blocades blanques i negres
    }
}
