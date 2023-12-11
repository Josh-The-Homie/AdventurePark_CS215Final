/**
 * Ride class for adventurepark
 * @author Joshua Henderson
 */
public class Ride {
    private String rideName;
    private int rideDuration;
//constructor
    public Ride(String rideName, int rideDuration) {
        this.rideName = rideName;
        this.rideDuration = rideDuration;
    }

    public String getRideName() {
        return rideName;
    }

    public int getRideDuration() {
        return rideDuration;
    }
}
