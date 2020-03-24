package lesson_1;

public class HomeworkOne {
    public static void main(String[] args) {

        Human human = new Human();
        Cat cat = new Cat();
        Robot robot = new Robot();

        RaceTrack raceTrack = new RaceTrack(400);
        Wall wall = new Wall(1.0f);

        System.out.printf("We have a new %s (%d meters length).%n", raceTrack.getName(), raceTrack.getLength());
        System.out.printf("We have a new %s (%.1f meters height).%n", wall.getName(), wall.getHeight());

        CanRunAndJump[] competitors = {human, cat, robot};
        Obstacle[] obstacles = {raceTrack, wall};

        System.out.println("----------");

        for (CanRunAndJump competitor : competitors) {
            System.out.println();
            for (Obstacle obstacle : obstacles) {
                competitor.passObstacle(obstacle);
            }
        }
    }
}