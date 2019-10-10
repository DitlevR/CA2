/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Hobby;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rumle
 */
public class HobbiesDTO {

    private List<HobbyDTO> all = new ArrayList();

    public HobbiesDTO(List<Hobby> hobbyEntities) {
        for(Hobby h : hobbyEntities) {
            all.add(new HobbyDTO(h));
        }
    }
}
