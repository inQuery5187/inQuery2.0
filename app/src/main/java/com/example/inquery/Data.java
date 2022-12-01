package com.example.inquery;

public class Data {
private String name;
private String pwd;
private String username;
private String branch;
private String rollno;
private String gender;
private String phoneno;
private String email;
private String address;

    public String getPhoneno() { return phoneno; }

    public void setPhoneno(String phoneno) { this.phoneno = phoneno; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getGender() { return gender; }

    public void setGender(String gender) { this.gender = gender; }

    public String getRollno() { return rollno; }

    public void setRollno(String rollno) { this.rollno = rollno; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBranch() { return branch; }

    public void setBranch(String branch) { this.branch = branch; }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) { this.pwd = pwd; }


    public Data(){

    }

}
