import com.sun.deploy.security.ValidationState;

import java.util.LinkedList;
import java.util.List;

public class Checkers {
    private Player black , white;
    private int opcio;
    private static int LEVEL_MAX=6;
    private String[][] board;

    public Checkers(int opcio, String[][] board){
        black= new Player("Black");
        white = new Player("White");
        this.opcio=opcio;
        this.board=board.clone();
    }

    public void setBlack(Player black) {
        this.black = black;
    }

    public void setWhite(Player white) {
        this.white = white;
    }

    public String[][] getBoard() {
        return board;
    }

    public void setBoard(String[][] board) {
        this.board = board;
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
        pieceCount(board, white, black);
    }

    public void bloquedCount(String[][] board, Player num1, Player num2){
        int blocadesBlanques=0;
        int blocadesNegres=0;
        num2.getPiece().setBloqiedPieces(0);
        num1.getPiece().setBloqiedPieces(0);
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
        num2.getPiece().setBloqiedPieces(blocadesNegres);
        num1.getPiece().setBloqiedPieces(blocadesBlanques);
    }


    public void checkerCount(String[][] board, Player num1, Player num2){
        int damesBlanques=0;
        int damesNegres=0;
        num2.getPiece().setQueenPieces(0);
        num1.getPiece().setQueenPieces(0);
        for(int j=0; j<8;j++){
            if(board[0][j].equals("W")) damesBlanques++;
            if(board[7][j].equals("B")) damesNegres++;
        }

        if(damesBlanques==damesNegres){
            for(int j = 0;j<8;j++){
                if(board[1][j].equals("W")) damesBlanques++;
                if(board[6][j].equals("B")) damesNegres++;
            }
        }

        if(damesBlanques==damesNegres){
            for(int j = 0;j<8;j++){
                if(board[2][j].equals("W")) damesBlanques++;
                if(board[5][j].equals("B")) damesNegres++;
            }
        }
        num2.getPiece().setQueenPieces(damesNegres);
        num1.getPiece().setQueenPieces(damesBlanques);
    }

    public void pieceCount(String[][] board, Player num1, Player num2){
        String jugador1, jugador2;
        if(num1.getType().equals("Black")){
            jugador1="B";
            jugador2 = "W";
        }else{
            jugador1="W";
            jugador2 = "B";
        }
        num1.getPiece().setNumberPieces(0);
        num2.getPiece().setNumberPieces(0);
        for(int i = 0; i<8;i++){
            for(int j = 0; j<8;j++){
                if(board[i][j].equals(jugador1)){
                    num1.getPiece().setNumberPieces(num1.getPiece().getNumberPieces()+1);
                }else{
                    if(board[i][j].equals(jugador2)){
                        num2.getPiece().setNumberPieces(num2.getPiece().getNumberPieces()+1);
                    }
                }
            }
        }
        if(num1.getType().equals("Black")){
           bloquedCount(board,num2,num1);
           checkerCount(board,num2,num1);
        }else{
            bloquedCount(board,num1,num2);
            checkerCount(board,num1,num2);
        }
    }

    public boolean notEnd(Player num1, Player num2){
        boolean retorn = true;

        if(num1.getPiece().getNumberPieces()==0 || num2.getPiece().getNumberPieces()==0 ||
                (num2.getPiece().getNumberPieces()==num2.getPiece().getBloqiedPieces() &&
                        num1.getPiece().getNumberPieces()==num1.getPiece().getBloqiedPieces())) retorn = false;

        return retorn;
    }

    public void winner(Player num1, Player num2){
        if(num1.getPiece().getQueenPieces()>num2.getPiece().getQueenPieces()) System.out.println("Ha guanyat el jugador número 1 amb "+white.getPiece().getQueenPieces()+" peces reina");
        else if(num1.getPiece().getQueenPieces()<num2.getPiece().getQueenPieces()) System.out.println("Ha guanyat el jugador número 2 amb "+black.getPiece().getQueenPieces()+" peces reina");
        else System.out.println("Hi ha un empat");
    }

    public int heuristica1(Player num1, Player num2){
        if(num2.isTurn())return num1.getPiece().getBloqiedPieces()*10 - num1.getPiece().getQueenPieces()*10;
        else return num2.getPiece().getBloqiedPieces()*10 - num2.getPiece().getQueenPieces()*10;
    }

    public int heuristica2(Player num1, Player num2){
        if(num2.isTurn())return num2.getPiece().getNumberPieces()-num1.getPiece().getNumberPieces();
        else return num1.getPiece().getNumberPieces()-num2.getPiece().getNumberPieces();
    }

    public int heuristica3(String[][] board,Player num1, Player num2){
       int i=0, j=0;
       int cantoB=0, cantoN=0;
       for(i=0;i<8;i++){
           for(j=0;j<8;j++){
               if(board[i][j].equals("B") && (j==0 || j==7 || i==7)){
                   cantoN++;
               }

               if(board[i][j].equals("W") && (j==0 || j==7 || i==0)){
                   cantoB++;
               }
           }
       }
       if(num2.isTurn()) return cantoN-cantoB;
       else return cantoB-cantoN;
    }

    public Node minimax(String[][] board, int nivell, Player jugador, Player maquina) throws CloneNotSupportedException {

        Node retorn = new Node(0,null,null, null) ;
        if(nivell%2==0){
            maquina.setTurn(true);
            jugador.setTurn(false);
        }else{
            maquina.setTurn(false);
            jugador.setTurn(true);
        }
        pieceCount(board, jugador, maquina);
        List<String[][]> boards;
        int standar_Value=0;
        String[][] standar_board = new String[0][];

        if(notEnd(jugador,maquina)) {
            if(LEVEL_MAX==nivell){
                int valor;
                if(opcio==1){
                    valor=heuristica1(jugador,maquina);
                }else if(opcio==2){
                    valor=heuristica2(jugador,maquina);
                }else{
                    valor=heuristica3(board,jugador,maquina);
                }
                return new Node(valor,null,maquina,jugador);
            }else{
                /*
                Falta generar pasos...
                 */
                if(nivell%2==0){
                    standar_Value=Integer.MIN_VALUE;
                }else{
                    standar_Value=Integer.MAX_VALUE;
                }
                boards=newBoard(jugador,maquina, board);

                int i=0;
                while(i<boards.size()){
                    standar_board=boards.get(i);
                    try {
                        retorn=minimax(standar_board.clone(),nivell+1,jugador.clone(),maquina.clone());
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }

                    if(nivell%2==0){
                        if(retorn.getHeuristica()>standar_Value){
                            standar_Value=retorn.getHeuristica();
                            retorn.setTaulell(standar_board.clone());
                        }

                    }
                    else{
                        if(retorn.getHeuristica()<standar_Value){
                            standar_Value=retorn.getHeuristica();
                            retorn.setTaulell(standar_board.clone());
                        }
                    }
                    i++;
                }

                if(boards.size()==0){
                    retorn.setHeuristica(heuristica1(jugador,maquina));
                    retorn.setTaulell(null);
                    retorn.setJugador(jugador);
                    retorn.setMaquina(maquina);
                    return retorn;
                }
                retorn.setTaulell(standar_board);
                retorn.setHeuristica(standar_Value);
                return retorn;
            }
        }else{
            if(jugador.getPiece().getQueenPieces()>maquina.getPiece().getQueenPieces()){
                return new Node(Integer.MIN_VALUE,board, maquina,jugador);
            }else{
                return new Node(Integer.MAX_VALUE,board,maquina,jugador);
            }
        }
    }

    public List<String[][]> newBoard(Player player, Player player2, String[][] board) throws CloneNotSupportedException {
        List<String[][]> retorna = new LinkedList<>();
        int i, j, found=0, movement;
        String[][] aux;
        checkerCount(board,player,player2);
        Player auxiliar, auxiliar2;
        if(player.isTurn()) {
            auxiliar = player.clone();
            auxiliar2 = player2.clone();
        }
        else {
            auxiliar = player2.clone();
            auxiliar2= player.clone();
        }
        i=0;
        String type, otherType;
        if(auxiliar.getType().equals("Black")) {
            type="B";
            otherType="W";
            movement=1;
        }
        else {
            type="W";
            otherType="B";
            movement=-1;
        }

        while(found<auxiliar.getPiece().getNumberPieces() && i<8){
            j=0;
            while(found<auxiliar.getPiece().getNumberPieces() && j<8){
                if(type.equals(board[i][j])){
                    int novaX = i+movement;
                    int novaY = j-1, novaY2 =j+1;
                    if(novaX>7 || novaX<0) novaX=-1;
                    else{
                        if(novaY>=0){
                            if(board[novaX][novaY].equals(type) || board[novaX][novaY].equals(otherType)){
                                novaX=novaX+movement;
                                novaY--;
                                if(novaX>7 || novaX<0) novaX=-1;
                                else{
                                    if (novaY > 0) {
                                        if (!board[novaX][novaY].equals(type) && !board[novaX][novaY].equals(otherType)) {
                                            aux=board.clone();
                                            if(type.equals("B"))updateBoard(auxiliar,auxiliar2,i,j,novaX,novaY,aux);
                                            else updateBoard(auxiliar2,auxiliar,i,j,novaX,novaY,aux);
                                            retorna.add(aux.clone());
                                        }
                                    }
                                }
                            }else{
                                aux=board.clone();
                                if(type.equals("B"))updateBoard(auxiliar,auxiliar2,i,j,novaX,novaY,aux);
                                else updateBoard(auxiliar2,auxiliar,i,j,novaX,novaY,aux);
                                retorna.add(aux.clone());
                            }
                        }
                        novaX=i+movement;
                        if(novaX>7 || novaX<0) novaX=-1;
                        else {
                            if (novaY2 < 8) {
                                if (board[novaX][novaY2].equals(type) || board[novaX][novaY2].equals(otherType)) {
                                    novaX = novaX + movement;
                                    novaY2++;
                                    if (novaX > 7 || novaX < 0) novaX = -1;
                                    else {
                                        if (novaY2 < 8) {
                                            if (!board[novaX][novaY2].equals(type) && !board[novaX][novaY2].equals(otherType)) {
                                                aux = board.clone();
                                                if (type.equals("B"))
                                                    updateBoard(auxiliar, auxiliar2, i, j, novaX, novaY2, aux);
                                                else updateBoard(auxiliar2, auxiliar, i, j, novaX, novaY2, aux);
                                                retorna.add(aux.clone());
                                            }
                                        }
                                    }
                                } else {
                                    aux = board.clone();
                                    if (type.equals("B")) updateBoard(auxiliar, auxiliar2, i, j, novaX, novaY2, aux);
                                    else updateBoard(auxiliar2, auxiliar, i, j, novaX, novaY2, aux);
                                    retorna.add(aux.clone());
                                }
                            }
                        }
                    }
                }
                j++;
            }
            i++;
        }
        return retorna;
    }
}
