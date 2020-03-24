package lesson_1;

public class Trial {

    CanRunAndJump[] competitors;
    Obstacle[] obstacles;


    public void addCompetitors(CanRunAndJump... competitors) {
        int i = 0;
        for (CanRunAndJump competitor : competitors) {
            i++;
        }
        this.competitors = new CanRunAndJump[i];
        i = 0;
        for (CanRunAndJump competitor : competitors) {
            this.competitors[i] = competitor;
            i++;
        }
    }

    public void addObstacles(Obstacle... obstacles) {
        int i = 0;
        for (Obstacle obstacle : obstacles) {
            i++;
        }
        this.obstacles = new Obstacle[i];
        i = 0;
        for (Obstacle obstacle : obstacles) {
            this.obstacles[i] = obstacle;
            i++;
        }
    }

    public void start() {
        System.out.println();
        message("The Trial begins!");

        int i = 1;
        for (Obstacle obstacle : obstacles) {
            System.out.printf("%nStage %d. %s.%n", i, obstacle.getName());
            for (CanRunAndJump competitor : competitors) {
                competitor.passObstacle(obstacle);
            }
            i++;
        }

        System.out.println();
        message("The Trial ends!");
        System.out.println();
    }

    public void message(String msg) {
        System.out.println("-----------------");
        System.out.println(msg.toUpperCase());
        System.out.println("-----------------");
    }

    public void result() {
        System.out.println("Trial completers:");
        int i = 0;
        for (CanRunAndJump competitor : competitors) {
            if (competitor.checkCompetition()) {
                System.out.printf(" * %s%n", competitor.getName());
                i++;
            }
        }
        if (i == 0) {
            System.out.printf("-None-%n");
        }
    }
}