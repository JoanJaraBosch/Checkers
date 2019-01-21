import java.util.Scanner;

public class Main {
    static int dim = 8;
    private static String[][] board = new String[dim][dim];
    private static Checkers checkers;

    public static void iniciali(String[][] board){
        for(int i =0 ; i<dim;i++){
            for(int j = 0;j<dim;j++){
               board[i][j]="?";
               if((i==0 && j%2==1) || (i==1 && j%2==0) || (i==2 && j%2==1)) board[i][j]="B";
               else if((i==5 && j%2==0) || (i==7 && j%2==0) || (i==6 && j%2==1)) board[i][j]="W";
            }
        }
    }

    public static void showBoard(String[][] board){
        System.out.print("  A B C D E F G H");
        for(int i =0; i<dim;i++){
            System.out.print("\n"+(i+1)+" ");
            for(int j = 0; j< dim;j++){
                System.out.print(board[i][j]+" ");
            }
        }
    }

    public static void main(String args[]) throws CloneNotSupportedException {
        int posX, posY, posXBefore, posYBefore, nivell =0;
        Scanner keyboard = new Scanner(System.in);
        int opcio=0;

        while(opcio!=1 && opcio!=2 && opcio!=3){
            System.out.println("Tria una heuristica per a jugar: \n\n");
            System.out.println("\t1-Fitxes blocades");
            System.out.println("\t2-Fitxes no blocades de cada jugador");
            System.out.println("\t3-Fitxes pels voltants per a no ser matades");
            opcio=keyboard.nextInt();
        }

        checkers = new Checkers(opcio, board);
        iniciali(board);
        while(checkers.notEnd(checkers.getWhite(),checkers.getBlack())) {
            showBoard(board);
            System.out.println();
            if (checkers.getBlack().isTurn()) {
                /*System.out.println("Player 2: Choose a black checker. Put the row.");
                posX=keyboard.nextInt()-1;
                System.out.println("Player 2: Choose a black checker. Put the column.");
                keyboard.nextLine();
                String aux = keyboard.next();
                posY=aux.toUpperCase().charAt(0)-65;
                if(posX>-1 && posY>-1 && posX<dim && posY <dim) {
                    if (board[posX][posY].equals("B")) {
                        System.out.println("Player 2: Choose where to move the checker. Put the row.");
                        posXBefore = keyboard.nextInt() - 1;
                        System.out.println("Player 2: Choose where to move the checker. Put the column.");
                        keyboard.nextLine();
                        aux = keyboard.next();
                        posYBefore = aux.toUpperCase().charAt(0)-65;
                        if (posXBefore>-1 && posYBefore>-1 && posXBefore<dim && posYBefore <dim) {
                            int auxX = posXBefore-posX;
                            int auxY = posYBefore-posY;
                            if(auxY<-2 || auxY>2) System.out.println("You have to choose a valid position.");
                            else{
                                if(auxX!=1 && auxX!=2)System.out.println("You have to choose a valid position.");
                                else{
                                    if(auxY<0){
                                        if(auxY==-1){
                                            if(!board[posXBefore][posYBefore].equals("B")&&!board[posXBefore][posYBefore].equals("W")){
                                                //cridar funcio moure
                                                checkers.moveChecker(posX,posY,posXBefore,posYBefore,board);
                                            }
                                        }else{
                                            if(!board[posXBefore][posYBefore].equals("B")&&!board[posXBefore][posYBefore].equals("W") &&
                                                    !board[posXBefore-1][posYBefore+1].equals("B")&&!board[posXBefore-1][posYBefore+1].equals("?")){
                                                //cridar funcio moure
                                                checkers.moveChecker(posX,posY,posXBefore,posYBefore,board);
                                            }else{
                                                System.out.println("You have to choose a valid position.");
                                            }
                                        }
                                    }else{
                                        if(auxY==1){
                                            if(!board[posXBefore][posYBefore].equals("B")&&!board[posXBefore][posYBefore].equals("W")){
                                                //cridar funcio moure
                                                checkers.moveChecker(posX,posY,posXBefore,posYBefore,board);
                                            }
                                        }else{
                                            if(!board[posXBefore][posYBefore].equals("B")&&!board[posXBefore][posYBefore].equals("W") &&
                                                    !board[posXBefore-1][posYBefore-1].equals("B")&&!board[posXBefore-1][posYBefore-1].equals("?")){
                                                //cridar funcio moure
                                                checkers.moveChecker(posX,posY,posXBefore,posYBefore,board);
                                            }else{
                                                System.out.println("You have to choose a valid position.");
                                            }
                                        }
                                    }
                                }
                            }
                        }else{
                            System.out.println("You have to choose a valid position.");
                        }
                    } else {
                        System.out.println("You have to choose a valid checker.");
                    }
                }else {
                    System.out.println("You have to choose a valid checker.");
                }*/
                Node aux = checkers.minimax(board.clone(),0,checkers.getWhite().clone(),checkers.getBlack().clone());
                board=aux.getTaulell().clone();
            } else {
                System.out.println("Player 1: Choose a white checker. Put the row.");
                posX=keyboard.nextInt()-1;
                System.out.println("Player 1: Choose a white checker. Put the column.");
                keyboard.nextLine();
                String aux = keyboard.next();
                posY=aux.toUpperCase().charAt(0)-65;
                if(posX>-1 && posY>-1 && posX<dim && posY <dim) {
                    if (board[posX][posY].equals("W")) {
                        System.out.println("Player 1: Choose where to move the checker. Put the row.");
                        posXBefore = keyboard.nextInt() - 1;
                        System.out.println("Player 1: Choose where to move the checker. Put the column.");
                        keyboard.nextLine();
                        aux = keyboard.next();
                        posYBefore = aux.toUpperCase().charAt(0)-65;
                        if (posXBefore>-1 && posYBefore>-1 && posXBefore<dim && posYBefore <dim) {
                            int auxX = posXBefore-posX;
                            int auxY = posYBefore-posY;
                            if(auxY<-2 || auxY>2) System.out.println("You have to choose a valid position.");
                            else{
                                if(auxX!=-1 && auxX!=-2)System.out.println("You have to choose a valid position.");
                                else{
                                    if(auxY<0){
                                        if(auxY==-1){
                                            if(!board[posXBefore][posYBefore].equals("B")&&!board[posXBefore][posYBefore].equals("W")){
                                                //cridar funcio moure
                                                checkers.moveChecker(posX,posY,posXBefore,posYBefore,board);
                                            }
                                        }else{
                                            if(!board[posXBefore][posYBefore].equals("B")&&!board[posXBefore][posYBefore].equals("W") &&
                                                    !board[posXBefore+1][posYBefore+1].equals("W")&&!board[posXBefore+1][posYBefore+1].equals("?")){
                                                //cridar funcio moure
                                                checkers.moveChecker(posX,posY,posXBefore,posYBefore,board);
                                            }else{
                                                System.out.println("You have to choose a valid position.");
                                            }
                                        }
                                    }else{
                                        if(auxY==1){
                                            if(!board[posXBefore][posYBefore].equals("B")&&!board[posXBefore][posYBefore].equals("W")){
                                                //cridar funcio moure
                                                checkers.moveChecker(posX,posY,posXBefore,posYBefore,board);
                                            }
                                        }else{
                                            if(!board[posXBefore][posYBefore].equals("B")&&!board[posXBefore][posYBefore].equals("W") &&
                                                    !board[posXBefore+1][posYBefore-1].equals("W")&&!board[posXBefore+1][posYBefore-1].equals("?")){
                                                //cridar funcio moure
                                                checkers.moveChecker(posX,posY,posXBefore,posYBefore,board);
                                            }else{
                                                System.out.println("You have to choose a valid position.");
                                            }
                                        }
                                    }
                                }
                            }
                        }else{
                            System.out.println("You have to choose a valid position.");
                        }
                    } else {
                        System.out.println("You have to choose a valid checker.");
                    }
                }else {
                    System.out.println("You have to choose a valid checker.");
                }
            }
        }
        keyboard.close();
        checkers.winner(checkers.getWhite(),checkers.getBlack());
    }
}
