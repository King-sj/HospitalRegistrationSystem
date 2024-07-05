package com.bupt.hospitalregistrationsystem.Model;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;


public interface AttendanceInformationRepository extends ReactiveMongoRepository<AttendanceInformation, String> {
  Flux<AttendanceInformation> findByDoctorUsername(String doctorUsername);
}
