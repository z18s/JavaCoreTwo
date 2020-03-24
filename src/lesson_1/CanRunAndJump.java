package lesson_1;

public interface CanRunAndJump {

    void run(Obstacle obstacle);

    void jump(Obstacle obstacle);

    default void passObstacle(Obstacle obstacle) {
        if (obstacle.getLength() > 0) {
            this.run(obstacle);
        }
        if (obstacle.getHeight() > 0) {
            this.jump(obstacle);
        }
    }
}