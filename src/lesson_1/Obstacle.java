package lesson_1;

abstract class Obstacle {

    String name;
    int length;
    float height;

    abstract String getName();

    abstract int getLength();

    abstract float getHeight();
}