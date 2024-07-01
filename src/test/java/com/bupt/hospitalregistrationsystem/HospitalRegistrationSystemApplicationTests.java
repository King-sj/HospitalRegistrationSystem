package com.bupt.hospitalregistrationsystem;

import com.bupt.hospitalregistrationsystem.Utils.CurrentTime;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootTest
class HospitalRegistrationSystemApplicationTests {

  @Test
  void contextLoads() {
  }
  @Test
  void test() {
    CurrentTime.getCurrentTime();
  }
}
