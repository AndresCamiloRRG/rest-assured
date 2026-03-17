package com.globant.automation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PetDTO {

    private String id;
    private CategoryDTO category;
    private String name;
    private String[] photoUrls;
    private TagDTO[] tags;
    private String status;

}
