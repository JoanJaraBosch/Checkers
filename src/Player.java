public class Player {
    Piece piece;
    private String type;
    private boolean turn;

    public Player(String type) {
        piece = new Piece(type);
        this.type=type;
        if(type.equals("White")) turn= true;
        else turn = false;
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
}
