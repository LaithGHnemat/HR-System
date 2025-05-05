package com.laith.hrsystem.laith.service;

import com.laith.hrsystem.laith.enums.LeaveStatus;
import com.laith.hrsystem.laith.exceptions.LeaveNotFoundException;
import com.laith.hrsystem.laith.exceptions.ManagerNotFoundException;
import com.laith.hrsystem.laith.exceptions.NotCorrectManagerException;
import com.laith.hrsystem.laith.model.Employee;
import com.laith.hrsystem.laith.model.Leave;
import com.laith.hrsystem.laith.model.Manager;
import com.laith.hrsystem.laith.repository.LeaveRepository;
import com.laith.hrsystem.laith.repository.ManagerRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Log4j2
public class ManagerService {
    @Autowired
    private LeaveRepository leaveRepository;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ManagerRepository managerRepository;

    @Transactional
    public Leave approveLeave(Long managerId, Long employeeId, Long leaveId) {
        Manager manager = managerRepository.findById(managerId)
                .orElseThrow(() ->
                        new ManagerNotFoundException("there is no manager with  id : " + managerId));
        Employee employee = employeeService.findEmployeeById(employeeId);

        if (!Objects.equals(manager.getId(), employee.getManager().getId())) {
            throw new NotCorrectManagerException("this employee dose not managed by that manager");
        }
        Leave leave = leaveRepository.findById(leaveId)
                .orElseThrow(() ->
                        new LeaveNotFoundException("there is no Leave with  id : " + leaveId));
        if (!Objects.equals(leave.getEmployee().getId(), employeeId)) {
            throw new LeaveNotFoundException("there is no leave for this employee with this id");
        }
        if (leave.getLeaveStatus() != LeaveStatus.PENDING) {
            throw new LeaveNotFoundException("this leave is not pending, you cant approve it!");
        }
        leave.setLeaveStatus(LeaveStatus.APPROVED);
        log.info("Leave has been approved Successfully");
        return leaveRepository.save(leave);

    }

    public List<Leave> displayAllPendingLeaves(Long managerId) {
        Manager manager = managerRepository.findById(managerId)
                .orElseThrow(() -> new ManagerNotFoundException("There is no manager with ID: " + managerId));
        return manager.getEmployees().stream()
                .flatMap(employee -> employee.getLeaves().stream())
                .filter(leave -> leave.getLeaveStatus().toString().equalsIgnoreCase(LeaveStatus.PENDING.toString()))
                .collect(Collectors.toList());
    }
}
