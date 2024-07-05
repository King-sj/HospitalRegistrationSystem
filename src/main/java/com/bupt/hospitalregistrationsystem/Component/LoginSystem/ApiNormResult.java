package com.bupt.hospitalregistrationsystem.Component.LoginSystem;

import java.util.Objects;

public record ApiNormResult(Boolean status) {
  public ApiNormResult{
    Objects.requireNonNull(status);
  }
}
