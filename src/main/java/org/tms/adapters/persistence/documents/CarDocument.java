package org.tms.adapters.persistence.documents;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
@Data
public class CarDocument {
    @Id
    private String policyNumber;
    private String type;
    private LocalDate dischargeDate;
    private LocalDate endDate;
}
