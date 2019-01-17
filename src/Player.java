public class Player {
    Piece piece;
    private String type;

    public Player(String type) {
        piece = new Piece(type);
        this.type=type;
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
