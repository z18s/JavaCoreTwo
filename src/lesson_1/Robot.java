package lesson_1;

public class Robot implements CanRunAndJump {

    private String name;

    private int runMax;
    private float jumpMax;
    private boolean isCompetitor;

    public Robot() {
        this.runMax = 900;
        this.jumpMax = 0.5f;
        this.isCompetitor = true;
    }

    public Robot(String name) {
        this();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run(Obstacle obstacle) {
        if (obstacle.getLength() <= this.runMax) {
            System.out.printf("Robot %s has run through the %s.%n", this.getName(), obstacle.getName());
            this.isCompetitor = true;
        } else {
            System.out.printf("Robot %s can't run through the %s! It can run only %d meters.%n", this.getName(), obstacle.getName(), this.runMax);
            this.isCompetitor = false;
        }
    }

    @Override
    public void jump(Obstacle obstacle) {
        if (obstacle.getHeight() <= this.jumpMax) {
            System.out.printf("Robot %s has jumped over the %s.%n", this.getName(), obstacle.getName());
            this.isCompetitor = true;
        } else {
            System.out.printf("Robot %s can't jump over the %s! It can jump only %.1f meters.%n", this.getName(), obstacle.getName(), this.jumpMax);
            this.isCompetitor = false;
        }
    }

    @Override
    public boolean checkCompetition() {
        return isCompetitor;
    }
}