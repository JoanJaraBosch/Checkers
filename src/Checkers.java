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
            if(auxX!=-1){
                if(auxY==-2){
                    board[posX-1][posY-1]="?";
                }else{
                    board[posX-1][posY+1]="?";
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
                    board[posX+1][posY-1]="?";
                }else{
                    board[posX+1][posY+1]="?";
                }
                black.getPiece().setNumberPieces(black.getPiece().getNumberPieces()-1);
            }
        }

        //contar blocades blanques i negres
        bloquedCount(board);
    }

    public void bloquedCount(String[][] board){
        black.getPiece().setBloqiedPieces(0);
        white.getPiece().setBloqiedPieces(0);
        boolean dreta=false, esquerra=false;
        for(int i=0; i<8;i++){
            for(int j =0;j<8;j++){
                if(board[i][j].equals("B")){
                    if(i+1>7){
                        black.getPiece().setNumberPieces(black.getPiece().getNumberPieces()-1);
                        black.getPiece().setQueenPieces(black.getPiece().getQueenPieces()+1);
                    }else{
                        if(i+2<=7) {
                            if (j + 1 > 7) {
                                dreta = true;
                            } else if (j + 1 <= 7) {
                                if (board[i + 1][j + 1].equals("B") || board[i + 1][j + 1].equals("W")) {
                                    if (j + 2 > 7) {
                                        dreta = true;
                                    } else {
                                        if (board[i + 2][j + 2].equals("B") || board[i + 2][j + 2].equals("W")) {
                                            dreta = true;
                                        }
                                    }
                                }
                            }

                            if (j - 1 < 0) {
                                esquerra = true;
                            } else if (j - 1 >= 0) {
                                if (board[i + 1][j - 1].equals("B") || board[i + 1][j - 1].equals("W")) {
                                    if (j - 2 < 0) {
                                        esquerra = true;
                                    } else {
                                        if (board[i + 2][j - 2].equals("B") || board[i + 2][j - 2].equals("W")) {
                                            esquerra = true;
                                        }
                                    }
                                }
                            }
                        }else{
                            black.getPiece().setBloqiedPieces(black.getPiece().getBloqiedPieces()+1);
                        }

                        if(esquerra && dreta){
                            black.getPiece().setBloqiedPieces(black.getPiece().getBloqiedPieces()+1);
                        }
                        esquerra=false;
                        dreta=false;
                    }
                }else if(board[i][j].equals("W")){
                    if(i-1<0){
                        white.getPiece().setNumberPieces(white.getPiece().getNumberPieces()-1);
                        white.getPiece().setQueenPieces(white.getPiece().getQueenPieces()+1);
                    }else{
                        if(i-2>=0) {
                            if (j + 1 > 7) {
                                dreta = true;
                            } else if (j + 1 <= 7) {
                                if (board[i - 1][j + 1].equals("B") || board[i - 1][j + 1].equals("W")) {
                                    if (j + 2 > 7) {
                                        dreta = true;
                                    } else {
                                        if (board[i - 2][j + 2].equals("B") || board[i - 2][j + 2].equals("W")) {
                                            dreta = true;
                                        }
                                    }
                                }
                            }

                            if (j - 1 < 0) {
                                esquerra = true;
                            } else if (j - 1 >= 0) {
                                if (board[i - 1][j - 1].equals("B") || board[i - 1][j - 1].equals("W")) {
                                    if (j - 2 < 0) {
                                        esquerra = true;
                                    } else {
                                        if (board[i - 2][j - 2].equals("B") || board[i - 2][j - 2].equals("W")) {
                                            esquerra = true;
                                        }
                                    }
                                }
                            }
                        }else{
                            white.getPiece().setBloqiedPieces(white.getPiece().getBloqiedPieces()+1);
                        }

                        if(esquerra && dreta){
                            white.getPiece().setBloqiedPieces(white.getPiece().getBloqiedPieces()+1);
                        }
                        esquerra=false;
                        dreta=false;
                    }
                }
            }
        }
    }

    public boolean notEnd(){
        boolean retorn = true;

        if(white.getPiece().getNumberPieces()==0 || black.getPiece().getNumberPieces()==0 ||
                (black.getPiece().getNumberPieces()==black.getPiece().getBloqiedPieces() &&
                        white.getPiece().getNumberPieces()==white.getPiece().getBloqiedPieces())) retorn = false;

        return retorn;
    }
}
