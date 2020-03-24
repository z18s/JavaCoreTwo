package lesson_1;

public class RaceTrack extends Obstacle {

    private String name;
    private int length;

    public RaceTrack(int length) {
        super();
        this.name = "Race Track";
        this.length = length;
    }

    @Override
    public int getLength() {
        return this.length;
    }

    @Override
    public float getHeight() {
        return this.height;
    }

    @Override
    String getName() {
        return this.name;
    }
}