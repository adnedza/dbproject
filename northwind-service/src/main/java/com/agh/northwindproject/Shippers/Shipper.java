package com.agh.northwindproject.Shippers;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "shippers")
@NoArgsConstructor
@Getter @Setter
public class Shipper {
    @Id
    private String id;

    private String companyName;

    private String phone;

    public Shipper(ShipperRequestBody shipperRequestBody) {
        this.companyName = shipperRequestBody.getCompanyName();
        this.phone = shipperRequestBody.getPhone();
    }
}
