public class Checkers {
    private Player black , white;
    private int turn = 0;

    public Checkers(){
        black= new Player("Black");
        white = new Player("White");
    }

    public boolean moveChecker(int xBefore, String yBefore,int x,String y, String[][] board){
        int posX=x-1;
        int posY=Integer.parseInt(y)-65;
        boolean retorn = false;

        if(turn==0){
            //si es pot fer el moviment posem true actualitzem tauler i actualitzem posicions
        }else{

        }

        return retorn;
    }
}
