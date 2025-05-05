package com.laith.hrsystem.laith.controller;

import com.laith.hrsystem.laith.service.ManagerService;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manager")
@Log
public class ManagerController {

     @Autowired
    ManagerService managerService;

    @PostMapping("/approveLeave/{managerId}/{employeeId}/{leaveId}")
    public ResponseEntity<?> approveLeave(
            @PathVariable("managerId") Long managerId, @PathVariable("employeeId")
            Long employeeId, @PathVariable("leaveId") Long leaveId) {
        log.info("Inside Methode approveLeave....");
        return ResponseEntity.ok(managerService.approveLeave(managerId, employeeId, leaveId));
    }
}
