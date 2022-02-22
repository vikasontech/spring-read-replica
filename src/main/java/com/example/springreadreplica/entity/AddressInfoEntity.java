package com.example.springreadreplica.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "address_infos")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class AddressInfoEntity implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "id")
  @GeneratedValue
  private Long id;

  @Column(name = "province")
  private String province;

  @Column(name = "district")
  private String district;

  @Column(name = "sub_district")
  private String subDistrict;

  @Column(name = "address")
  private String address;

  @Column(name = "postcode")
  private String postCode;
}
