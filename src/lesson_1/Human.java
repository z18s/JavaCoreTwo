package lesson_1;

public class Human implements CanRunAndJump {

    private String name;

    private int runMax;
    private float jumpMax;
    private boolean isCompetitor;

    public Human() {
        this.runMax = 500 + (int) (Math.random() * 200 + 1);
        this.jumpMax = 1.5f + ((int) (Math.random() * 5) + 1) / 10.0f;
        this.isCompetitor = true;
    }

    public Human(String name) {
        this();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run(Obstacle obstacle) {
        if (obstacle.getLength() <= this.runMax) {
            System.out.printf("Human %s has run through the %s.%n", this.getName(), obstacle.getName());
            this.isCompetitor = true;
        } else {
            System.out.printf("Human %s can't run through the %s! He can run only %d meters.%n", this.getName(), obstacle.getName(), this.runMax);
            this.isCompetitor = false;
        }
    }

    @Override
    public void jump(Obstacle obstacle) {
        if (obstacle.getHeight() <= this.jumpMax) {
            System.out.printf("Human %s has jumped over the %s.%n", this.getName(), obstacle.getName());
            this.isCompetitor = true;
        } else {
            System.out.printf("Human %s can't jump over the %s! He can jump only %.1f meters.%n", this.getName(), obstacle.getName(), this.jumpMax);
            this.isCompetitor = false;
        }
    }

    @Override
    public boolean checkCompetition() {
        return isCompetitor;
    }
}