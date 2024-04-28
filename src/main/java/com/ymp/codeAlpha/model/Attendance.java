package com.ymp.codeAlpha.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.sql.Date;

/**
 * Author  - Yoon Myat Phoo
 * created - 4/28/2024
 * project - codeAlpha
 * package - com.ymp.codeAlpha.model
 */

@Entity
@Data
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String studentIdentity;
    private String subject;
    private Date date;
    private String teacher;
    private Boolean attendance;
    private String room;
    private String status;

}
