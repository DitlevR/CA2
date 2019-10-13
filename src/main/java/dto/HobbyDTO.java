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
    @Schema(required = true, example = "1")
    private int id;
    
    public HobbyDTO(Hobby h) {
        this.name = h.getName();
        this.description = h.getDescription();
        this.id = h.getId();
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
