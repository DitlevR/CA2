///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package entities;
//
//import java.io.Serializable;
//import java.util.List;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.OneToMany;
//
///**
// *
// * @author Ludvig
// */
//@Entity
//public class CityInfo implements Serializable {
//
//    private static final long serialVersionUID = 1L;
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private int id;
//    private String zipCode;
//    private String city;
//    
//    @OneToMany(mappedBy = "ci")
//    private List<Address> address;
//
//    public CityInfo() {
//    }
//
//    public CityInfo(String zipCode, String city) {
////        this.id = id;
//        this.zipCode = zipCode;
//        this.city = city;
//    }
//
//    public String getZipCode() {
//        return zipCode;
//    }
//
//    public String getCity() {
//        return city;
//    }
//
//    public void setZipCode(String zipCode) {
//        this.zipCode = zipCode;
//    }
//
//    public void setCity(String city) {
//        this.city = city;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    @Override
//    public String toString() {
//        return "entities.CityInfo[ id=" + id + " ]";
//    }
//    
//}
