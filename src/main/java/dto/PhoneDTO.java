/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Phone;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 *
 * @author Rumle
 */
public class PhoneDTO {
    @Schema(required = true, example = "1")
    private int id;
    @Schema(required = true, example = "12365761")
    private String number;
    
    public PhoneDTO(Phone p) {
        this.number = p.getNumber();
    }

    public PhoneDTO(String number) {
        this.number = number;
    }
    
    public PhoneDTO() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
    
    
}
