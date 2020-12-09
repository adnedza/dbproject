package com.agh.northwindproject.Territories;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class TerritoriesRequestBody {
    private String territoryDescription;

    private String regionID;
}
