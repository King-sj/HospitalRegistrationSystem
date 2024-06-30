package com.bupt.hospitalregistrationsystem.Component.LoginSystem;

import java.util.Objects;

public record Email(String email) {
  public Email{
    Objects.requireNonNull(email);
  }
}
