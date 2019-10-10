/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Phone;

/**
 *
 * @author Rumle
 */
public class PhoneDTO {
    private String number;
    
    public PhoneDTO(Phone p) {
        this.number = p.getNumber();
    }
    
}
