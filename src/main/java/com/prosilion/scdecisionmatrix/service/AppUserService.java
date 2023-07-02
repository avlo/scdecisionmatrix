package com.prosilion.scdecisionmatrix.service;

import com.prosilion.scdecisionmatrix.model.entity.AppUser;
import com.prosilion.scdecisionmatrix.repository.AppUserRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AppUserService {
  private final AppUserRepository appUserRepository;

  @Autowired
  public AppUserService(AppUserRepository appUserRepository) {
    this.appUserRepository = appUserRepository;
  }

  @Transactional
  public AppUser save(@NonNull AppUser appUser) {
    AppUser appUserToSave = findByAppUser(appUser);
    return appUserToSave.getId() != null ? appUserToSave : appUserRepository.save(appUserToSave);
  }

  public AppUser findById(@NonNull Integer id) {
    return appUserRepository.findById(id).get();
  }

  /////////////////
  // PRIVATE METHODS
  /////////////////

  private AppUser findByAppUser(@NonNull AppUser appUser) {
    if (appUser.getId() != null) {
      return findById(appUser.getId());
    }
    return new AppUser();
  }
}
