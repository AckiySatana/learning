package ru.obj.shape;

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
