package lesson_1;

public class Robot implements CanRunAndJump {

    private int runMax;
    private float jumpMax;

    public Robot() {
        this.runMax = 900;
        this.jumpMax = 0.5f;
    }

    @Override
    public void run(Obstacle obstacle) {
        if (obstacle.getLength() <= runMax) {
            System.out.printf("Robot has run through the %s.%n", obstacle.getName());
        } else {
            System.out.printf("Robot can't run through the %s! It can run only %d meters.%n", obstacle.getName(), runMax);
        }
    }

    @Override
    public void jump(Obstacle obstacle) {
        if (obstacle.getHeight() <= jumpMax) {
            System.out.printf("Robot has jumped over the %s.%n", obstacle.getName());
        } else {
            System.out.printf("Robot can't jump over the %s! It can jump only %.1f meters.%n", obstacle.getName(), jumpMax);
        }
    }
}