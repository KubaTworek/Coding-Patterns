package pl.jakubtworek.medium.intervals;

import java.util.Objects;

class Point {
    private final int point;
    private final String label;

    public Point(int point, String label) {
        this.point = point;
        this.label = label;
    }

    public int getPoint() {
        return point;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return "Point{" +
                "point=" + point +
                ", label='" + label + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Point point1 = (Point) o;
        return point == point1.point && Objects.equals(label, point1.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(point, label);
    }
}
