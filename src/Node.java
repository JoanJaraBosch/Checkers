public class Node {
    private int heuristica;
    private String[][] taulell;

    public Node (int heuristica, String[][] taulell){
        this.heuristica=heuristica;
        this.taulell=taulell;
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
