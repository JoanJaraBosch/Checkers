public class Node {
    private int heuristica;
    private String[][] taulell;
    private Player maquina, jugador;

    public Node (int heuristica, String[][] taulell, Player maquina, Player jugador){
        this.heuristica=heuristica;
        this.taulell=taulell;
        this.maquina=maquina;
        this.jugador=jugador;
    }

    public Player getJugador() {
        return jugador;
    }

    public void setJugador(Player jugador) {
        this.jugador = jugador;
    }

    public Player getMaquina() {
        return maquina;
    }

    public void setMaquina(Player maquina) {
        this.maquina = this.maquina;
    }

    public int getHeuristica() {
        return heuristica;
    }

    public void setHeuristica(int heuristica) {
        this.heuristica = heuristica;
    }

    public String[][] getTaulell() {
        return taulell;
    }

    public void setTaulell(String[][] taulell) {
        this.taulell = taulell;
    }
}
