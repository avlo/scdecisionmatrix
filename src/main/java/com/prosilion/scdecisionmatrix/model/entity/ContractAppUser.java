package com.prosilion.scdecisionmatrix.model.entity;

import edu.mayo.lpea.cad.cadence.security.core.entity.AppUser;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Embeddable
@Entity
public class ContractAppUser extends AppUser implements Creator {
  @Override
  public ContractAppUser getInstantiatedCustomAppUserType() {
    return this;
  }

  @Override
  public ContractAppUser createNewCustomAppUserInstance() {
    return new ContractAppUser();
  }

  public ContractAppUser convertToDto() {
    ContractAppUser contractAppUser = new ContractAppUser();
    BeanUtils.copyProperties(contractAppUser, this);
    return contractAppUser;
  }
}
