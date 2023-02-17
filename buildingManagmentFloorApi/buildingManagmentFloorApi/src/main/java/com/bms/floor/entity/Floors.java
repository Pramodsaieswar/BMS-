package com.bms.floor.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "FloorsTable")
public class Floors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int floorid;
    private int floorNo;
    private int flatNo;
    private String flatStatus;







}
