package com.bupt.hospitalregistrationsystem.Service;

import com.bupt.hospitalregistrationsystem.Model.AttendanceInformation;
import com.bupt.hospitalregistrationsystem.Model.AttendanceInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MongoAttendanceInformationService {
  private final AttendanceInformationRepository attendanceInformationRepository;
  @Autowired
  public MongoAttendanceInformationService(AttendanceInformationRepository attendanceInformationRepository) {
    this.attendanceInformationRepository = attendanceInformationRepository;
  }
  public Mono<AttendanceInformation> save(AttendanceInformation attendanceInformation) {
    return attendanceInformationRepository.save(attendanceInformation);
  }
  public Flux<AttendanceInformation> findByDoctorUsername(String doctorUsername) {
    return attendanceInformationRepository.findByDoctorUsername(doctorUsername);
  }
  public Mono<AttendanceInformation> findById(String attendanceInformationId) {
    return attendanceInformationRepository.findById(attendanceInformationId);
  }
  public Flux<AttendanceInformation> findAll() {
    return attendanceInformationRepository.findAll();
  }
}
