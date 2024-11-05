package com.cloudy.domain.company.model;

import com.cloudy.domain.company.model.dto.request.CompanyCreateRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "company")
@Getter
@NoArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companyId;

    private String companyName;

    private String businessRegistrationNumber; //사업자 등록 번호

    private Company(String companyName, String businessRegistrationNumber) {
        this.companyName = companyName;
        this.businessRegistrationNumber = businessRegistrationNumber;
    }

    public static Company of(CompanyCreateRequest request) {
        return new Company(request.getCompanyName(),
                request.getBusinessRegistrationNumber());
    }
}
