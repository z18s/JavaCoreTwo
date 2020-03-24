package lesson_1;

public class Human implements CanRunAndJump {

    private int runMax;
    private float jumpMax;

    public Human() {
        this.runMax = 500;
        this.jumpMax = 2.0f;
    }

    @Override
    public void run(Obstacle obstacle) {
        if (obstacle.getLength() <= runMax) {
            System.out.printf("Human has run through the %s.%n", obstacle.getName());
        } else {
            System.out.printf("Human can't run through the %s! He can run only %d meters.%n", obstacle.getName(), runMax);
        }
    }

    @Override
    public void jump(Obstacle obstacle) {
        if (obstacle.getHeight() <= jumpMax) {
            System.out.printf("Human has jumped over the %s.%n", obstacle.getName());
        } else {
            System.out.printf("Human can't jump over the %s! He can jump only %.1f meters.%n", obstacle.getName(), jumpMax);
        }
    }
}