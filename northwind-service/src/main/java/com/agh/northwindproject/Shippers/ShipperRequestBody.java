package com.agh.northwindproject.Shippers;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class ShipperRequestBody {
    private String companyName;

    private String phone;
}
