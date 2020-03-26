package lesson_1;

public class HomeworkOne {
    public static void main(String[] args) {

        Human humanThomas = new Human("Thomas");
        Cat catOliver = new Cat("Oliver");
        Robot robotAsh = new Robot("Ash");

        RaceTrack raceTrack = new RaceTrack(400);
        Wall wall = new Wall(1.0f);

        Trial trial = new Trial();

        trial.setCompetitors(humanThomas, catOliver, robotAsh);
        trial.setObstacles(raceTrack, wall);

        trial.start();

        trial.result();
    }
}