package com.ymp.codeAlpha.service;

import com.ymp.codeAlpha.model.Attendance;
import com.ymp.codeAlpha.model.Classroom;
import com.ymp.codeAlpha.repository.AttendanceRepository;
import com.ymp.codeAlpha.repository.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * Author  - Yoon Myat Phoo
 * created - 4/28/2024
 * project - codeAlpha
 * package - com.ymp.codeAlpha.service
 */

@Service
public class AttendanceServiceImpl implements AttendanceService{

    @Autowired
    ClassroomRepository classroomRepository;

    @Autowired
    AttendanceRepository  attendanceRepository;

    @Override
    public Classroom saveClassroom(Classroom classroom) {
        return classroomRepository.save(classroom);
    }

    @Override
    public Boolean existsStudentByIdentityAndDifferentSubject(String studentIdentity, String subject) {
        return classroomRepository.existsByStudentIdentityAndSubject(studentIdentity, subject);
    }

    @Override
    public List<Classroom> findByRoomAndDate(String room, Date date) {
        return classroomRepository.findByRoomAndDate(room,date);
    }

    @Override
    public void fillAttendanceByRoomAndDate(String room, Date date, String subject,Map<String, Boolean> studentAttendanceMap) {
        List<Classroom> classrooms = classroomRepository.findByRoomAndDateAndSubject(room, date,subject);

        for (Classroom classroom : classrooms) {
            String[] students = classroom.getStudentIdentity().split(","); // Assuming student names are comma-separated
            for (String student : students) {
                Attendance attendance = new Attendance();
                attendance.setStudentIdentity(student);
                attendance.setSubject(classroom.getSubject());
                attendance.setDate(classroom.getDate());
                attendance.setTeacher(classroom.getTeacher());
                attendance.setRoom(classroom.getRoom());

                // Set attendance based on the map values
                Boolean attendanceValue = studentAttendanceMap.get(student);
                if (attendanceValue != null) {
                    attendance.setAttendance(attendanceValue);
                    attendance.setStatus(attendanceValue ? "Present" : "Absent");
                }

                attendanceRepository.save(attendance);
            }
        }
    }

    @Override
    public List<String> findAbsentByDateAndSubject(Date date,String subject) {
        return attendanceRepository.findAbsentByDateAndSubject(date,subject);
    }

    @Override
    public List<String> findPresentByDateAndSubject(Date date,String subject) {
        return attendanceRepository.findPresentByDateAndSubject(date,subject);
    }


}
