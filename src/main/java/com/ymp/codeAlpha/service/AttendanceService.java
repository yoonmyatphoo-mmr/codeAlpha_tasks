package com.ymp.codeAlpha.service;

import com.ymp.codeAlpha.model.Attendance;
import com.ymp.codeAlpha.model.Classroom;

import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * Author  - Yoon Myat Phoo
 * created - 4/28/2024
 * project - codeAlpha
 * package - com.ymp.codeAlpha.service
 */
public interface AttendanceService {
    Classroom saveClassroom(Classroom classroom);

    Boolean existsStudentByIdentityAndDifferentSubject(String studentIdentity,String subject);
    List<Classroom> findByRoomAndDate(String room, Date date);
    void fillAttendanceByRoomAndDate(String room, Date date,String subject, Map<String, Boolean> studentAttendanceMap);

    List<String> findAbsentByDateAndSubject(Date date,String subject);

    List<String> findPresentByDateAndSubject(Date date,String subject);
}
