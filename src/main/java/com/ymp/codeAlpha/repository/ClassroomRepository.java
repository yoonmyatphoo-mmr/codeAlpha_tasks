package com.ymp.codeAlpha.repository;

import com.ymp.codeAlpha.model.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

/**
 * Author  - Yoon Myat Phoo
 * created - 4/28/2024
 * project - codeAlpha
 * package - com.ymp.codeAlpha.repository
 */

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom,Long> {
    List<Classroom> findByRoomAndDate(String room, Date date);

    List<Classroom> findByRoomAndDateAndSubject(String room,Date date,String subject);
    boolean existsByStudentIdentityAndSubject(String studentIdentity, String subject);
}
