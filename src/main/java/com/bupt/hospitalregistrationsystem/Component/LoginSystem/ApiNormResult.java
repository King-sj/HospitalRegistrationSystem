package com.bupt.hospitalregistrationsystem.Component.LoginSystem;

import java.util.Objects;

public record ApiNormResult(Boolean stauts) {
  public ApiNormResult{
    Objects.requireNonNull(stauts);
  }
}
