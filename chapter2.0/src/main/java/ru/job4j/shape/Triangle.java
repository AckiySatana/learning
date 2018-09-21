package ru.job4j.shape;

public class Triangle implements Shape {

    @Override
    public String draw() {
        return new StringBuilder()
                .append("   *   \n")
                .append("  ***  \n")
                .append(" ***** \n")
                .append("*******")
                .toString();
    }
}
