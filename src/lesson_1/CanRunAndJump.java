package lesson_1;

public interface CanRunAndJump {

    void run(Obstacle obstacle);

    void jump(Obstacle obstacle);

    String getName();

    boolean checkCompetition();

    default void passObstacle(Obstacle obstacle) {
        if (obstacle.getLength() > 0 && this.checkCompetition()) {
            this.run(obstacle);
        }
        if (obstacle.getHeight() > 0 && this.checkCompetition()) {
            this.jump(obstacle);
        }
    }
}