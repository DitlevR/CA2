/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Phone;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rumle
 */
public class PhonesDTO {
    private List<PhoneDTO> all = new ArrayList<>();
    
    public PhonesDTO(List<Phone> phoneEntities) {
        for(Phone p : phoneEntities) {
            all.add(new PhoneDTO(p));
        }
    }
}
