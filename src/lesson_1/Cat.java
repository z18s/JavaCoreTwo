package lesson_1;

public class Cat implements CanRunAndJump {

    private int runMax;
    private float jumpMax;

    public Cat() {
        this.runMax = 200;
        this.jumpMax = 1.5f;
    }

    @Override
    public void run(Obstacle obstacle) {
        if (obstacle.getLength() <= runMax) {
            System.out.printf("Cat has run through the %s.%n", obstacle.getName());
        } else {
            System.out.printf("Cat can't run through the %s! It can run only %d meters.%n", obstacle.getName(), runMax);
        }
    }

    @Override
    public void jump(Obstacle obstacle) {
        if (obstacle.getHeight() <= jumpMax) {
            System.out.printf("Cat has jumped over the %s.%n", obstacle.getName());
        } else {
            System.out.printf("Cat can't jump over the %s! It can jump only %.1f meters.%n", obstacle.getName(), jumpMax);
        }
    }
}