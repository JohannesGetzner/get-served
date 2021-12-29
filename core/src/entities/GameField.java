package entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.World;
import exceptions.InputNotValidException;

import java.util.ArrayList;
import java.util.List;

import static com.mygdx.game.MyGdxGame.WORLD_HEIGHT;
import static com.mygdx.game.MyGdxGame.WORLD_WIDTH;

public class GameField {

    private final float CELLSIZE = 5;
    private final int rowNumber = (int) (WORLD_HEIGHT / CELLSIZE);
    private final int colNumber = (int) (WORLD_WIDTH / CELLSIZE);
    private List<Table> tables = new ArrayList<Table>();
    private List<Counter> counters = new ArrayList<>();
    private List<Table> freeTables = new ArrayList<Table>();
    private Walls walls;

    public GameField() {
    }

    /**
     * @param gridPositions the location of the tables on counters to be created
     * @param world         the world to create the physical borders of the tables and counters in
     * @throws InputNotValidException if the number of columns or rows is set incorrectly this exception will be thrown
     */
    public void initializeTables(List<Gridposition> gridPositions, World world) throws InputNotValidException {
        for (Gridposition g : gridPositions
             ) {
            if (g != null) {
                if ((g.getCol() > colNumber) || (g.getRow() > rowNumber) || (g.getRow() < 0) || (g.getCol() < 0)) {
                    throw (new InputNotValidException("Column or Row for " + g.getType() + " too large or too small"));
                }
                float positionY = (g.getRow() * CELLSIZE) - CELLSIZE / 2;
                float positionX = (g.getCol() * CELLSIZE) - CELLSIZE / 2;
                if (g.getType().equals("counter")) {
                    counters.add(new Counter(world, positionX, positionY, g.getRotation()));
                    Gdx.app.log("INFO: ", "Counter created");
                } else if (g.getType().equals("table")) {
                	Table table = new Table(world, positionX, positionY);
                    tables.add(table);
                    freeTables.add(table);
                    Gdx.app.log("INFO: ", "Table created");

                }
            }
        }
    }

    /**
     * this method generates the physical borders of the gamelevel
     * @param world the physical world that the walls are created in
     */
    public void generateWalls(World world) {
        this.walls = new Walls(world);
    }

    @Override
    public String toString() {
        return "GameField{" +
                "CELLSIZE=" + CELLSIZE +
                ", rowNumber=" + rowNumber +
                ", colNumber=" + colNumber +
                ", tables=" + tables +
                ", counters=" + counters +
                '}';
    }

    /*
     * -----------------
     * Getter and Setter
     */
    public List<Table> getTables() {
        return tables;
    }
    
    public List<Table> getFreeTables(){
    	return freeTables;
    }
    
    public void addFreeTable(Table table) {
    	freeTables.add(table);
    }
    
    public void removeFreeTable(Table table) {
    	freeTables.remove(table);
    }
    
    public List<Counter> getCounters(){
        return counters;
    }

}
