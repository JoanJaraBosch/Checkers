import com.sun.deploy.security.ValidationState;

import java.util.LinkedList;
import java.util.List;

public class Checkers {
    private Player black , white;
    private static int LEVEL_MAX=5;
    private String[][] board;

    public Checkers(String[][] board){
        black= new Player("Black");
        white = new Player("White");
        this.board=clone(board);
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
        changeTurn(white,black);
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
                                if (!board[i + 1][j + 1].equals("B") ) {
                                    if(board[i + 1][j + 1].equals("W")) {
                                        if (i + 2 <= 7) {
                                            if (j + 2 > 7) {
                                                dreta = true;
                                            } else {
                                                if (board[i + 2][j + 2].equals("B") || board[i + 2][j + 2].equals("W")) {
                                                    dreta = true;
                                                }
                                            }
                                        } else {
                                            dreta = true;
                                        }
                                    }
                                }else{
                                    dreta=true;
                                }
                            }

                            if (j - 1 < 0) {
                                esquerra = true;
                            } else if (j - 1 >= 0) {
                                if (!board[i + 1][j - 1].equals("B")  ) {
                                    if(board[i + 1][j - 1].equals("W")) {
                                        if (i + 2 <= 7) {
                                            if (j - 2 < 0) {
                                                esquerra = true;
                                            } else {
                                                if (board[i + 2][j - 2].equals("B") || board[i + 2][j - 2].equals("W")) {
                                                    esquerra = true;
                                                }
                                            }

                                        } else {
                                            esquerra = true;
                                        }
                                    }
                                }else{
                                    esquerra=true;
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
                            if (!board[i - 1][j + 1].equals("W")) {
                                if(board[i - 1][j + 1].equals("B") ) {
                                    if (i - 2 >= 0) {
                                        if (j + 2 > 7) {
                                            dreta = true;
                                        } else {
                                            if (board[i - 2][j + 2].equals("B") || board[i - 2][j + 2].equals("W")) {
                                                dreta = true;
                                            }
                                        }
                                    } else {
                                        dreta = true;
                                    }
                                }
                            }else{
                                dreta=true;
                            }
                        }

                        if (j - 1 < 0) {
                            esquerra = true;
                        } else if (j - 1 >= 0) {
                            if ( !board[i - 1][j - 1].equals("W")) {
                                if(board[i - 1][j - 1].equals("B")) {
                                    if (i - 2 > 0) {
                                        if (j - 2 < 0) {
                                            esquerra = true;
                                        } else {
                                            if (board[i - 2][j - 2].equals("B") || board[i - 2][j - 2].equals("W")) {
                                                esquerra = true;
                                            }
                                        }

                                    } else {
                                        esquerra = true;
                                    }
                                }
                            }else{
                                esquerra=true;
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
        else if(num1.getPiece().getNumberPieces()>num2.getPiece().getNumberPieces())System.out.println("Ha guanyat el jugador número 1 ");
        else if(num1.getPiece().getNumberPieces()<num2.getPiece().getNumberPieces())System.out.println("Ha guanyat el jugador número 2 ");
        else System.out.println("Hi ha un empat");
    }

    public int heuristica1(Player num1, Player num2){
        if(num2.isTurn())return (num2.getPiece().getNumberPieces() - num1.getPiece().getNumberPieces())*10;
        else return (-num2.getPiece().getNumberPieces() + num1.getPiece().getNumberPieces())*10;
    }

    public int heuristica2(Player num1, Player num2){
        if(num2.isTurn())return (num2.getPiece().getBloqiedPieces()-num1.getPiece().getNumberPieces())*10;
        else return (-num2.getPiece().getBloqiedPieces()+num1.getPiece().getNumberPieces())*10;
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
       if(num1.getType().equals("Black")) return (cantoN-cantoB)*10;
       else return (-cantoN+cantoB)*10;
    }

    public Node minimax(String[][] board, int nivell, Player jugador, Player maquina, int opcio) throws CloneNotSupportedException {
        Node retorn = new Node(0,null,null,null);
        String [][] aux, taulell_return=null;
        int mes_infinit= Integer.MAX_VALUE, menys_infinit= Integer.MIN_VALUE;
        int heuristic_value=0, i;
        List<String[][]> taulells;

        pieceCount(board,jugador,maquina);

        if(notEnd(jugador,maquina)){
            if(nivell==LEVEL_MAX){
                int val;
                if(opcio==1){
                    val=heuristica1(jugador,maquina);
                }else if(opcio==2){
                    val=heuristica2(jugador,maquina);
                }else{
                    val=heuristica3(board,jugador,maquina);
                }
                retorn.setHeuristica(val);
                retorn.setTaulell(null);
                return retorn;
            }else{
                if(nivell%2==0) {
                    heuristic_value=menys_infinit;
                }
                else {
                    heuristic_value=mes_infinit;
                }
                taulells=newBoard(jugador,maquina,board);

                i=0;
                while(i < taulells.size()){
                    aux=taulells.get(i);
                    try {
                        retorn=minimax(clone(aux),nivell+1,jugador,maquina, opcio);
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }

                    if(nivell%2==0){
                        if((retorn.getHeuristica())>=heuristic_value){
                            heuristic_value=retorn.getHeuristica();
                            taulell_return=clone(aux);
                        }

                    }
                    else{
                        if(retorn.getHeuristica()<=heuristic_value) {
                            heuristic_value = retorn.getHeuristica();
                            taulell_return = clone(aux);
                        }
                    }
                    i++;
                }

                if(taulells.size()==0){
                    int val;
                    if(opcio==1){
                        val=heuristica1(jugador,maquina);
                    }else if(opcio==2){
                        val=heuristica2(jugador,maquina);
                    }else{
                        val=heuristica3(board,jugador,maquina);
                    }
                    retorn.setHeuristica(val);
                    retorn.setTaulell(null);
                    return retorn;
                }
                retorn.setHeuristica(heuristic_value);
                retorn.setTaulell(taulell_return);
                return retorn;
            }
        }else{
            retorn.setMaquina(maquina);
            retorn.setJugador(jugador);
            retorn.setTaulell(null);
            if(jugador.getPiece().getQueenPieces()>maquina.getPiece().getQueenPieces()) retorn.setHeuristica(menys_infinit);
            else if(jugador.getPiece().getQueenPieces()<maquina.getPiece().getQueenPieces()) retorn.setHeuristica(mes_infinit);
            else if(jugador.getPiece().getNumberPieces()>maquina.getPiece().getNumberPieces())retorn.setHeuristica(menys_infinit);
            else if(jugador.getPiece().getNumberPieces()<maquina.getPiece().getNumberPieces())retorn.setHeuristica(mes_infinit);
            else retorn.setHeuristica(mes_infinit);
        }

        return retorn;
    }

    public List<String[][]> newBoard(Player player, Player player2, String[][] board) throws CloneNotSupportedException {

        String[][] taula = clone(board);
        List<String[][]> taulells = new LinkedList<>();
        Player num1 = player.clone();
        Player num2 = player2.clone();
        String type, otherType;
        int i , j;


        if(num1.isTurn()) {
            if (num1.getType().equals("Black")) {
                pieceCount(taula, num2, num1);
                type = "B";
                otherType = "W";
            } else {
                pieceCount(taula, num1, num2);
                type = "W";
                otherType = "B";
            }
        }else {
            if (num2.getType().equals("Black")) {
                pieceCount(taula, num1, num2);
                type = "B";
                otherType = "W";
            } else {
                pieceCount(taula, num2, num1);
                type = "W";
                otherType = "B";
            }
        }

        i=0;
        while (i<8) {
            j=0;
            while(j<8){
                if(taula[i][j].equals(type)){
                    int moviment;
                    if(type.equals("B")){
                        moviment=1;
                    }else{
                        moviment =-1;
                    }

                    int auxY=j+1;
                    int auxY2=j-1;
                    int auxX=i+moviment;
                    //dreta
                    if(auxX>=0 && auxX<=7){
                        if(auxY<=7){
                            if(!taula[auxX][auxY].equals(type)){
                                if(taula[auxX][auxY].equals(otherType)){
                                    auxX+=moviment;
                                    auxY+=1;
                                    if(auxX>=0 && auxX<=7){
                                        if(auxY<=7){
                                            if(!taula[auxX][auxY].equals(otherType) && !taula[auxX][auxY].equals(type)){
                                                String[][] aux = clone(taula);
                                                if(num1.getType().equals("Black")) {
                                                    updateBoard(num1,num2,i,j,auxX,auxY,aux);
                                                }else{
                                                    updateBoard(num2,num1,i,j,auxX,auxY,aux);
                                                }
                                                taulells.add(aux);
                                            }
                                        }
                                    }
                                }else{
                                    String[][] aux = clone(taula);
                                    if(num1.getType().equals("Black")) {
                                        updateBoard(num1,num2,i,j,auxX,auxY,aux);
                                    }else{
                                        updateBoard(num2,num1,i,j,auxX,auxY,aux);
                                    }
                                    taulells.add(aux);
                                }
                            }
                        }
                        auxX=moviment+i;
                        //esquerra
                        if(auxY2>=0){
                            if(!taula[auxX][auxY2].equals(type)){
                                if(taula[auxX][auxY2].equals(otherType)){
                                    auxX+=moviment;
                                    auxY2-=1;
                                    if(auxX>=0 && auxX<=7){
                                        if(auxY2>=0){
                                            if(!taula[auxX][auxY2].equals(otherType) && !taula[auxX][auxY2].equals(type)){
                                                String[][] aux = clone(taula);
                                                if(num1.getType().equals("Black")) {
                                                    updateBoard(num1,num2,i,j,auxX,auxY2,aux);
                                                }else{
                                                    updateBoard(num2,num1,i,j,auxX,auxY2,aux);
                                                }
                                                taulells.add(aux);
                                            }
                                        }
                                    }
                                }else{
                                    String[][] aux = clone(taula);
                                    if(num1.getType().equals("Black")) {
                                        updateBoard(num1,num2,i,j,auxX,auxY2,aux);
                                    }else{
                                        updateBoard(num2,num1,i,j,auxX,auxY2,aux);
                                    }
                                    taulells.add(aux);
                                }
                            }
                        }
                    }
                }
                j++;
            }
            i++;
        }


        return taulells;
    }

    public void changeTurn(Player num1, Player num2){
        if(num1.isTurn()){
            if(num2.getPiece().getNumberPieces()!=num2.getPiece().getBloqiedPieces()){
                num2.setTurn(true);
                num1.setTurn(false);
            }
        }else{
            if(num1.getPiece().getNumberPieces()!=num1.getPiece().getBloqiedPieces()){
                num1.setTurn(true);
                num2.setTurn(false);
            }
        }
    }

    private void updateBoard(Player black, Player white, int posXBefore, int posYBefore, int posX, int posY,String[][] board) {
        board[posXBefore][posYBefore]="?";
        if(black.isTurn()){
            board[posX][posY]="B";
            if((posX-posXBefore==2)){
                if(posY-posYBefore==2){
                    board[posX-1][posY-1]="?";
                }else{
                    board[posX-1][posY+1]="?";
                }
            }
        }else{
            board[posX][posY]="W";
            if((posX-posXBefore==-2)){
                if(posY-posYBefore==2){
                    board[posX+1][posY-1]="?";
                }else{
                    board[posX+1][posY+1]="?";
                }
            }
        }
        pieceCount(board,white,black);
    }

    public String[][] clone(String[][] board){
        String[][] aux = new String[8][8];

        for(int i =0; i<8;i++){
            for(int j = 0; j<8;j++){
                aux[i][j]=board[i][j];
            }
        }

        return aux;
    }
}
