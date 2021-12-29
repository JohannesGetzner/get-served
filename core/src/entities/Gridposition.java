package entities;

public class Gridposition {
    private int row;
    private int col;
    private String type;
    private int rotation;
    
    public Gridposition(int row, int col, String type, int rotation) {
        this.row = row;
        this.col = col;
        this.type = type;
        this.rotation = rotation;
    }
    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public String getType(){
        return type;
    }
    
    public int getRotation() {
    	return rotation;
    }

    @Override
    public String toString() {
        return "Gridposition{" +
                "row=" + row +
                ", col=" + col +
                ", type='" + type + '\'' + ", rotation=" + rotation +
                '}';
    }

    public Gridposition() {
    }
}
