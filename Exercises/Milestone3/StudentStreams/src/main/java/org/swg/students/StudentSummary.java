/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.swg.students;

/**
 *
 * @author pspethmann
 */
public class StudentSummary {
    
    private String country;
    private String major;
    private double iq;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public double getIq() {
        return iq;
    }

    public void setIq(double iq) {
        this.iq = iq;
    }
    
    public StudentSummary(Student student){
        this.country = student.getCountry();
        this.major = student.getMajor();
        this.iq = student.getIq();
    }

    @Override
    public String toString() {
        return "StudentSummary{" + "country=" + country + ", major=" + major + ", iq=" + iq + '}';
    }
    
    
}
