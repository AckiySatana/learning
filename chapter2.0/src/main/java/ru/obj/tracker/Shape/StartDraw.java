package ru.obj.tracker.Shape;

public class StartDraw {
    public static void main(String[] args) {
        Paint paint = new Paint();
        paint.draw(new Triangle());
        paint.draw(new Square());
    }
}
