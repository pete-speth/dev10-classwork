package org.swg.students;

import java.util.Objects;

public class Registration {

    private String course;
    private double pointPercent;
    private GradeType gradType;

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public double getPointPercent() {
        return pointPercent;
    }

    public void setPointPercent(double pointPercent) {
        this.pointPercent = pointPercent;
    }

    public GradeType getGradType() {
        return gradType;
    }

    public void setGradType(GradeType gradType) {
        this.gradType = gradType;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.course);
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.pointPercent) ^ (Double.doubleToLongBits(this.pointPercent) >>> 32));
        hash = 79 * hash + Objects.hashCode(this.gradType);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Registration other = (Registration) obj;
        if (Double.doubleToLongBits(this.pointPercent) != Double.doubleToLongBits(other.pointPercent)) {
            return false;
        }
        if (!Objects.equals(this.course, other.course)) {
            return false;
        }
        if (this.gradType != other.gradType) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Registration{" + "course=" + course + ", pointPercent=" + pointPercent + ", gradType=" + gradType + '}';
    }

}
