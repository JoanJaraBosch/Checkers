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

    public static void main(String args[]){
        checkers = new Checkers();
        iniciali(board);
        showBoard(board);
    }
}
