package com.ymp.codeAlpha.controller;

import com.ymp.codeAlpha.model.Attendance;
import com.ymp.codeAlpha.model.Classroom;
import com.ymp.codeAlpha.service.AttendanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * Author  - Yoon Myat Phoo
 * created - 4/28/2024
 * project - codeAlpha
 * package - com.ymp.codeAlpha.controller
 */

@Controller
@Slf4j
public class AttendanceController {

    private final AttendanceService attendanceService;

    @Autowired
    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @PostMapping("/saveStudent")
    public ResponseEntity<String> saveStudent(@RequestBody Classroom classroom) {
        // Check if a student with the same identity and subject already exists
        boolean studentExistsForDifferentSubject = attendanceService.existsStudentByIdentityAndDifferentSubject(classroom.getStudentIdentity(), classroom.getSubject());

        if (studentExistsForDifferentSubject) {
            return ResponseEntity.badRequest().body("Student with the same identity already exists for the same subject.");
        }

        // If student doesn't exist for the same subject, save the classroom
        attendanceService.saveClassroom(classroom);

        return ResponseEntity.ok("Save Successfully");
    }

    @GetMapping("/search")
    public ResponseEntity<List<Classroom>> searchByRoomAndDate(@RequestParam String room, @RequestParam Date date) {
        List<Classroom> classrooms = attendanceService.findByRoomAndDate(room, date);
        return ResponseEntity.ok(classrooms);
    }

    @PostMapping("/fill")
    public ResponseEntity<String> fillAttendanceByRoomAndDateAndSubject(
            @RequestParam String room,
            @RequestParam Date date,
            @RequestParam String subject,
            @RequestBody Map<String, Boolean> studentAttendanceMap
    ) {
        log.info("Enter fillAttendanceByRoomAndDate Method...");
        attendanceService.fillAttendanceByRoomAndDate(room, date,subject, studentAttendanceMap);
        log.info("Exit fillAttendanceByRoomAndDate Method...");
        return ResponseEntity.ok("Attendance filled successfully.");
    }

    @GetMapping("/findAbsentByDate")
    public ResponseEntity<List<String>> findAbsentByDateAndSubject(@RequestParam Date date,@RequestParam String subject) {
        List<String> absentStudents = attendanceService.findAbsentByDateAndSubject(date,subject);

        return ResponseEntity.ok(absentStudents);
    }

    @GetMapping("/findPresentByDate")
    public ResponseEntity<List<String>> findPresentByDateAndSubject(@RequestParam Date date,@RequestParam String subject) {
        List<String> presentStudents = attendanceService.findPresentByDateAndSubject(date,subject);

        return ResponseEntity.ok(presentStudents);
    }



}