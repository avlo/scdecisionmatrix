package com.prosilion.scdecisionmatrix.service;

import com.prosilion.scdecisionmatrix.model.entity.AppUser;
import lombok.Data;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Data
@Service
public class BtcBalanceService {  

  public Long getBalance(@NonNull AppUser appUser) {
    return Long.valueOf(100);
  }
}

