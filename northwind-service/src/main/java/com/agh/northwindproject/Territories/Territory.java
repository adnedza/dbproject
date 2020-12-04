package com.agh.northwindproject.Territories;

import com.agh.northwindproject.Region.Region;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;

@Document(collection = "territories")
@NoArgsConstructor
@Getter @Setter
public class Territory {
    @Id
    private String id;

    private String territoryDescription;

    private Region region;

    public Territory(String territoryDescription, Region region) {
        this.territoryDescription = territoryDescription;
        this.region = region;
    }
}
