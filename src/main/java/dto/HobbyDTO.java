/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Hobby;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 *
 * @author Rumle
 */
public class HobbyDTO {
    @Schema(required = true, example = "svømning")
    private String name;
    @Schema(required = true, example = "man svømmer")
    private String description;
    
    public HobbyDTO(Hobby h) {
        this.name = h.getName();
        this.description = h.getDescription();
    }
    
    public HobbyDTO(String name, String description) {
        this.name = name;
        this.name = description;
    }
    
    public HobbyDTO() {
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
    
}
