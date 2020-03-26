package lesson_1;

public class Wall extends Obstacle {

    private String name;
    private float height;

    public Wall(float height) {
        super();
        this.name = "Wall";
        this.height = height;
        System.out.printf("We have a new %s (%.1f meters height).%n", this.getName(), this.getHeight());
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
    public String getName() {
        return name;
    }
}