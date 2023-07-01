package com.example.feereport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Student {
    private String name;
    private String email;
    private String course;
    private final int fees =100000;
    private int paid;
    private int due;
    private long phone;

    public static void addtoDB(Student student) throws Exception {
        Connection con = DBConnection.getConnection();
        String query = "insert into students(name,email,course,fees,paid,due,phone) values(?,?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1,student.name);
        ps.setString(2, student.email);
        ps.setString(3, student.course);
        ps.setInt(4,student.fees);
        ps.setInt(5,student.paid);
        ps.setInt(6,student.due);
        ps.setLong(7,student.phone);

        ps.executeUpdate();
    }

    public static void UpdateFee(String payemail, int paidfee) throws Exception {
        Connection con = DBConnection.getConnection();
        String query = "update students set paid = ? where email = ?";
        PreparedStatement ps =con.prepareStatement(query);
        ps.setInt(1,paidfee);
        ps.setString(2,payemail);
        Student.UpdateDue(payemail);
        ps.executeUpdate();
        Student.UpdateDue(payemail);
    }

    private static void UpdateDue(String payemail) throws Exception {
        Connection con = DBConnection.getConnection();
        String query = "update students set due = fees - paid where email = ?";
        PreparedStatement ps =con.prepareStatement(query);
        ps.setString(1,payemail);
        ps.executeUpdate();
    }

    public static int getPaid(String payemail) throws Exception {
        Connection con = DBConnection.getConnection();
        String query = "select paid from students where email = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1,payemail);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int paidfee = rs.getInt("paid");
            return paidfee;
        } else {
            throw new Exception();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getFees() {
        return fees;
    }

    public int getPaid() {
        return paid;
    }

    public void setPaid(int paid) {
        this.paid = paid;
    }

    public int getDue() {
        return due;
    }

    public void setDue(int due) {
        this.due = due;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }
    public String toString(){
        return name+" "+email+" "+course+" "+fees+" "+paid+" "+due+" "+phone;
    }
}
