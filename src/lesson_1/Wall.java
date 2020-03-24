package lesson_1;

public class Wall extends Obstacle {

    private String name;
    private float height;

    public Wall(float height) {
        super();
        this.name = "Wall";
        this.height = height;
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