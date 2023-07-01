package com.example.feereport;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;

public class Accountant {
    private String name;
    private String email;
    private String username;
    private String password;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField courseField;
    @FXML
    private TextField paidField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField paynameField;
    @FXML
    private TextField payemailField;
    @FXML
    private TextField paycourseField;
    @FXML
    private TextField paypaidField;

    public static void addtoDB(Accountant accountant) throws Exception {
        Connection con = DBConnection.getConnection();
        String query = "insert into accountants(name,email,username,password) values(?,?,?,?);";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, accountant.getName());
        ps.setString(2, accountant.getEmail());
        ps.setString(3, accountant.getUsername());
        ps.setString(4, accountant.getPassword());

        ps.executeUpdate();
    }
    @FXML
    private void Regiser(ActionEvent event ) throws Exception {
        Student student = new Student();
        student.setName(nameField.getText());
        student.setEmail(emailField.getText());
        student.setCourse(courseField.getText());
        student.setPaid(Integer.parseInt(paidField.getText()));
        student.setDue(student.getFees()- student.getPaid());
        student.setPhone(Long.parseLong(phoneField.getText()));
        Student.addtoDB(student);
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("accountanthome.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void MakeaPayment(ActionEvent event) throws Exception {
        String payname = paynameField.getText();
        String payemail = payemailField.getText();
        String paycourse = paycourseField.getText();
        int paidfee = Integer.parseInt(paypaidField.getText());
        paidfee = Student.getPaid(payemail) + paidfee;
        Student.UpdateFee(payemail,paidfee);
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("accountanthome.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void MakePayment(ActionEvent event) throws Exception {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("makepayment.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void logout(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("home.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void back(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("accountanthome.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void addStudent(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("addstudent.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String toString(){
        return getName()+" "+getEmail()+" "+getUsername()+" "+getPassword();
    }
}
