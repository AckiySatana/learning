package ru.obj.shape;

public class Square implements Shape {
    public String draw() {
        return new StringBuilder()
                .append("*********\n")
                .append("*       *\n")
                .append("*       *\n")
                .append("*       *\n")
                .append("*********").toString();
    }
}