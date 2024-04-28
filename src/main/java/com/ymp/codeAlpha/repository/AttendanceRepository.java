package com.ymp.codeAlpha.repository;

import com.ymp.codeAlpha.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

/**
 * Author  - Yoon Myat Phoo
 * created - 4/28/2024
 * project - codeAlpha
 * package - com.ymp.codeAlpha.repository
 */

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance,Long> {

    @Query("SELECT DISTINCT a.studentIdentity FROM Attendance a WHERE a.date = :date AND a.subject = :subject AND a.attendance = false")
    List<String> findAbsentByDateAndSubject(Date date,String subject);

    @Query("SELECT DISTINCT a.studentIdentity FROM Attendance a WHERE a.date = :date AND a.subject = :subject AND a.attendance = true")
    List<String> findPresentByDateAndSubject(Date date,String subject);
}
