public class Piece implements Cloneable {
    private int x = 12;
    private int numberPieces = 12;
    private int bloqiedPieces=0;
    private int queenPieces =0;
    private String type;

    public Piece(String type){
        this.type=type;
    }

    public Piece() {

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

    protected Piece clone() throws CloneNotSupportedException {
        Piece jugador= new Piece();
        jugador.setQueenPieces(this.getQueenPieces());
        jugador.setBloqiedPieces(this.getBloqiedPieces());
        jugador.setNumberPieces(this.getNumberPieces());
        return  jugador;
    }
}
