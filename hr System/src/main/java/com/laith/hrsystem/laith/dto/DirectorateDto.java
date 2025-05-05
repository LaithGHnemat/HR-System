package com.laith.hrsystem.laith.dto;

import com.laith.hrsystem.laith.model.Directorate;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DirectorateDto {

    private Long id;
    private String name;
    private String center;
    private String phone;
    private Long departmentId;

    public static Directorate fromDirectorateDto(DirectorateDto directorateDto){
        Directorate directorate = new Directorate();
        directorate.setId(directorateDto.getId());
        directorate.setName(directorateDto.getName());
        directorate.setPhone(directorateDto.getPhone());
        directorate.setCenter(directorateDto.getCenter());
        // todo change this,cause: it dose not pass the id of department and in some method it will arrive as null
        return directorate;
    }
}
