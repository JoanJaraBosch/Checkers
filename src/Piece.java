public class Piece {
    private int x = 12;
    private int numberPieces = 12;
    private int bloqiedPieces=0;
    private int queenPieces =0;
    private String type;
    private Position[] positionPieces = new Position[x];

    public Piece(String type){
        this.type=type;
        initializePositions(positionPieces);
    }

    public void initializePositions(Position[] table){
        int i;
        int auxX;
        int auxY;

        if(type.equals("White")){
            auxX=0;
            auxY=1;

            for(i=0;i<x;i++){
                table[i].setX(auxX);
                table[i].setY(auxY);
                if(auxY!=7 && auxY!=6)auxY+=2;
                else{
                    if(auxY==7){
                        auxX++;
                        auxY=0;
                    }else{
                        auxX++;
                        auxY=1;
                    }
                }
            }
        }else{
            auxX=5;
            auxY=0;

            for(i=0;i<x;i++){
                table[i].setX(auxX);
                table[i].setY(auxY);
                if(auxY!=7 && auxY!=6)auxY+=2;
                else{
                    if(auxY==7){
                        auxX++;
                        auxY=0;
                    }else{
                        auxX++;
                        auxY=1;
                    }
                }
            }
        }
    }

    public int getNumberPieces() {
        return numberPieces;
    }

    public void setNumberPieces(int numberPieces) {
        this.numberPieces = numberPieces;
    }

    public int getBloqiedPieces() {
        return bloqiedPieces;
    }

    public void setBloqiedPieces(int bloqiedPieces) {
        this.bloqiedPieces = bloqiedPieces;
    }

    public int getQueenPieces() {
        return queenPieces;
    }

    public void setQueenPieces(int queenPieces) {
        this.queenPieces = queenPieces;
    }

    public Position[] getPositionPieces() {
        return positionPieces;
    }

    public void setPositionPieces(Position[] positionPieces) {
        this.positionPieces = positionPieces;
    }
}
