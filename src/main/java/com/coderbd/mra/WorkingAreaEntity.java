package com.coderbd.mra;

import jakarta.persistence.*;

@Entity
public class WorkingAreaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // Field for First Table
    @Column(name = "noOfZila")
    private int noOfZila;
    @Column(name = "noOfUpazila")
    private int noOfUpazila;
    @Column(name = "noOfUnion")
    private int noOfUnion;
    @Column(name = "noOfVillage")
    private int noOfVillage;
    @Column(name = "noOfBranch")
    private int noOfBranch;
    @Column(name = "noOfManBase")
    private int noOfManBase;
    @Column(name = "noOfWomanBase")
    private int noOfWomanBase;
    @Column(name = "totalSamity")
    private int totalSamity;
    @Column(name = "noOfman")
    private int noOfman;
    @Column(name = "noOfwoman")
    private int noOfwoman;
    @Column(name = "noOfthirdGender")
    private int noOfthirdGender;
    @Column(name = "totalConsumers")
    private int totalConsumers;
    @Column(name = "noOfManLoanee")
    private int noOfManLoanee;
    @Column(name = "noOfWomanLoanee")
    private int noOfWomanLoanee;
    @Column(name = "thirdGenderLoanee")
    private int thirdGenderLoanee;
    @Column(name = "totalLoanee")
    private int totalLoanee;

    //Field for 2nd Tables
    @Column(name = "zilaName")
    private String zilaName;
    @Column(name = "upaZilaName")
    private String upaZilaName;
    @Column(name = "newJoinAreas")
    private String newJoinAreas;
    @Column(name = "removeAreas")
    private String removeAreas;


}
















