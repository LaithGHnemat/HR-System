package com.laith.hrsystem.laith.dto;

import com.laith.hrsystem.laith.enums.LeaveStatus;
import com.laith.hrsystem.laith.model.Leave;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeaveDto {
   public Long id;
   public LocalDate creationDate;
   public LocalTime leaveFrom;
   public  LocalTime leaveTo;
   public  String description;
   public String leaveStatus;
   public Long employeeId;

    public static Leave fromLeaveDto(LeaveDto leaveDto){
        Leave leave = new Leave();
        leave.setId(leaveDto.getId());
        leave.setLeaveStatus(LeaveStatus.PENDING);
        leave.setCreationDate(LocalDate.now());
        leave.setLeaveFrom(leaveDto.getLeaveFrom());
        leave.setLeaveTo(leaveDto.getLeaveTo());
        leave.setDescription(leaveDto.getDescription());
        return leave;
    }
}
