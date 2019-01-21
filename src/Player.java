public class Player implements Cloneable {
    Piece piece;
    private String type;
    private boolean turn;

    public Player(String type) {
        piece = new Piece(type);
        this.type=type;
        if(type.equals("White")) turn= true;
        else turn = false;
    }

    public Player() {

    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    protected Player clone() throws CloneNotSupportedException {
        Player jugador= new Player();
        jugador.setTurn(this.isTurn());
        jugador.setPiece(this.getPiece());
        jugador.setType(this.getType());
        return  jugador;
    }
}
