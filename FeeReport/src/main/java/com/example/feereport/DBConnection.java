package com.example.feereport;

import java.sql.*;

public class DBConnection {
    private static final String url = "jdbc:mysql://localhost:3306/feeReport";
    private static final String user = "root";
    private static final String password = "root@mysql";

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(url,user,password);
    }

}
    /*
        create database feereport;
        use feereport;
        create table accountants(
        id int primary key auto_increment,
        name varchar(30),
        email varchar(50) unique,
        username varchar(30) unique,
        password varchar(30) );
        create table students(
        id int primary key auto_increment,
        name varchar(30),
        email varchar(50) unique,
        course varchar(30),
        fees int ,
        paid int,
        due int,
        phone long );
        select * from accountants;
        select * from students;
        drop table students;
        drop table accountants;
        drop database feereport;
    */