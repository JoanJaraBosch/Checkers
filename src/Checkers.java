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
        checkerCount(board);
    }

    public void bloquedCount(String[][] board){
        int blocadesBlanques=0;
        int blocadesNegres=0;
        black.getPiece().setBloqiedPieces(0);
        white.getPiece().setBloqiedPieces(0);
        boolean dreta=false, esquerra=false;
        for(int i=0; i<8;i++){
            for(int j =0;j<8;j++){
                if(board[i][j].equals("B")){
                    if(i+1>7){
                       blocadesNegres++;
                    }else{
                            if (j + 1 > 7) {
                                dreta = true;
                            } else{
                                if (board[i + 1][j + 1].equals("B") || board[i + 1][j + 1].equals("W")) {
                                    if(i+2<=7) {
                                        if (j + 2 > 7) {
                                            dreta = true;
                                        } else {
                                            if (board[i + 2][j + 2].equals("B") || board[i + 2][j + 2].equals("W")) {
                                                dreta = true;
                                            }
                                        }
                                    }else {
                                        dreta=true;
                                    }
                                }
                            }

                            if (j - 1 < 0) {
                                esquerra = true;
                            } else if (j - 1 >= 0) {
                                if (board[i + 1][j - 1].equals("B") || board[i + 1][j - 1].equals("W")) {
                                    if(i+2<=7) {
                                        if (j - 2 < 0) {
                                            esquerra = true;
                                        } else {
                                            if (board[i + 2][j - 2].equals("B") || board[i + 2][j - 2].equals("W")) {
                                                esquerra = true;
                                            }
                                        }

                                    }else{
                                        esquerra=true;
                                    }
                                }
                            }

                        if(esquerra && dreta){
                           blocadesNegres++;
                        }
                        esquerra=false;
                        dreta=false;
                    }
                }else if(board[i][j].equals("W")){
                    if(i-1<0){
                        blocadesBlanques++;
                    }else{
                        if (j + 1 > 7) {
                            dreta = true;
                        } else{
                            if (board[i - 1][j + 1].equals("B") || board[i - 1][j + 1].equals("W")) {
                                if(i-2>=0) {
                                    if (j + 2 > 7) {
                                        dreta = true;
                                    } else {
                                        if (board[i - 2][j + 2].equals("B") || board[i - 2][j + 2].equals("W")) {
                                            dreta = true;
                                        }
                                    }
                                }else {
                                    dreta=true;
                                }
                            }
                        }

                        if (j - 1 < 0) {
                            esquerra = true;
                        } else if (j - 1 >= 0) {
                            if (board[i - 1][j - 1].equals("B") || board[i - 1][j - 1].equals("W")) {
                                if(i-2>0) {
                                    if (j - 2 < 0) {
                                        esquerra = true;
                                    } else {
                                        if (board[i - 2][j - 2].equals("B") || board[i - 2][j - 2].equals("W")) {
                                            esquerra = true;
                                        }
                                    }

                                }else{
                                    esquerra=true;
                                }
                            }
                        }

                        if(esquerra && dreta){
                            blocadesBlanques++;
                        }
                        esquerra=false;
                        dreta=false;
                    }
                }
            }
        }
        black.getPiece().setBloqiedPieces(blocadesNegres);
        white.getPiece().setBloqiedPieces(blocadesBlanques);
    }


    public void checkerCount(String[][] board){
        int damesBlanques=0;
        int damesNegres=0;
        black.getPiece().setQueenPieces(0);
        white.getPiece().setQueenPieces(0);
        for(int j=0; j<8;j++){
            if(board[0][j].equals("W")) damesBlanques++;
            if(board[7][j].equals("B")) damesNegres++;
            if(board[1][j].equals("W")) damesBlanques++;
            if(board[6][j].equals("B")) damesNegres++;
        }
        black.getPiece().setQueenPieces(damesNegres);
        white.getPiece().setQueenPieces(damesBlanques);
    }

    public boolean notEnd(){
        boolean retorn = true;

        if(white.getPiece().getNumberPieces()==0 || black.getPiece().getNumberPieces()==0 ||
                (black.getPiece().getNumberPieces()==black.getPiece().getBloqiedPieces() &&
                        white.getPiece().getNumberPieces()==white.getPiece().getBloqiedPieces())) retorn = false;

        return retorn;
    }

    public void winner(){
        if(white.getPiece().getQueenPieces()>black.getPiece().getQueenPieces()) System.out.println("Ha guanyat el jugador número 1 amb "+white.getPiece().getQueenPieces()+" peces reina");
        else if(white.getPiece().getQueenPieces()<black.getPiece().getQueenPieces()) System.out.println("Ha guanyat el jugador número 2 amb "+black.getPiece().getQueenPieces()+" peces reina");
        else System.out.println("Hi ha un empat");
    }
}
