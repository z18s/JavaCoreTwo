package lesson_1;

public class RaceTrack extends Obstacle {

    private String name;
    private int length;

    public RaceTrack(int length) {
        super();
        this.name = "Race Track";
        this.length = length;
        System.out.printf("We have a new %s (%d meters length).%n", this.getName(), this.getLength());
    }

    @Override
    public int getLength() {
        return length;
    }

    @Override
    public float getHeight() {
        return height;
    }

    @Override
    String getName() {
        return name;
    }
}