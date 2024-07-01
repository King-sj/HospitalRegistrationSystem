package com.bupt.hospitalregistrationsystem.Utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CurrentTime {
  public static String getCurrentTime() {
    LocalDateTime currentDateTime = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    return currentDateTime.format(formatter);
  }
}
