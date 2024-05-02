package com.cts.deepak.foodDonation.NGO.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ngos")
public class NGO {

	private Long userId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ngoId;
    private String ngoName;
    private String ngoDescription;

}