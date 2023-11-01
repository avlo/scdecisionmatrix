//package com.prosilion.scdecisionmatrix.model.entity;
//
//import com.prosilion.scdecisionmatrix.model.dto.PayeeDto;
//import edu.mayo.lpea.cad.cadence.security.core.entity.AppUser;
//import jakarta.persistence.Embeddable;
//import jakarta.persistence.Entity;
//import lombok.EqualsAndHashCode;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import org.springframework.beans.BeanUtils;
//
//@Getter
//@Setter
//@EqualsAndHashCode
//@NoArgsConstructor
//@Embeddable
//@Entity
//public class Payee extends ContractAppUser implements Creator {
//
//  @Override
//  public Payee getInstantiatedCustomAppUserType() {
//    return this;
//  }
//
//  @Override
//  public Payee createNewCustomAppUserInstance() {
//    return new Payee();
//  }
//
//  public PayeeDto convertToDto() {
//    PayeeDto payeeDto = new PayeeDto();
//    BeanUtils.copyProperties(payeeDto, this);
//    return payeeDto;
//  }
//}
