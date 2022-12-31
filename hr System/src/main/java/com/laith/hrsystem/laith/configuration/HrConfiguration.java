package com.laith.hrsystem.laith.configuration;

import com.laith.hrsystem.laith.enums.LeaveStatus;
import com.laith.hrsystem.laith.model.*;
import com.laith.hrsystem.laith.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
@Transactional
public class HrConfiguration {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private LeaveRepository leaveRepository;

    @Autowired
    private ManagerRepository managerRepository;


    @Autowired
    private DirectorateRepository directorateRepository;




    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            insertData();
        };
    }

    private void insertData() {

        Directorate directorate1= new Directorate();
        directorate1.setName("Directorate1");
        directorate1.setCenter("dcc");
        directorate1.setPhone("sssssssssss");

        Directorate directorate2= new Directorate();
        directorate2.setName("Directorate1");
        directorate2.setCenter("dcc");
        directorate2.setPhone("sssssssssss");

        Directorate directorate3= new Directorate();
        directorate3.setName("Directorate1");
        directorate3.setCenter("dcc");
        directorate3.setPhone("sssssssssss");








        Department department1 = new Department();
        department1.setId(Long.valueOf(1));
        department1.setLocation("Amman");
        department1.setName("IT");
        department1.setDirectorate(directorate1);

        Department department2 = new Department();
        department2.setId(Long.valueOf(2));
        department2.setLocation("Amman");
        department2.setName("Management");
        department2.setDirectorate(directorate2);

        Department department3 = new Department();
        department3.setId(Long.valueOf(3));
        department3.setLocation("Amman");
        department3.setName("Telecommunications");
        department3.setDirectorate(directorate3);

        directorate1.setDepartment(department1);
        department2.setDirectorate(directorate2);
        department3.setDirectorate(directorate3);
        Manager zedan = new Manager();
        zedan.setName("zedan");
        Manager karlo = new Manager();
        karlo.setName("karlo");


        Employee nedal = new Employee("nedal", "nedal@yahoo.com", 500.2, LocalDate.now().minusDays(1), department1, zedan);
        Employee laith = new Employee("laith", "laith@yahoo.com", 700.2, LocalDate.now().minusDays(1), department2, zedan);
        Employee mohamaad = new Employee("mohamaad", "mohamaad@yahoo.com", 500.2, LocalDate.now().minusDays(2), department3, karlo);

        Leave nedalLeave = new Leave(LocalDate.now(),
                LocalTime.now(), LocalTime.now().plusHours(2),
                "Sick leave", LeaveStatus.PENDING, nedal);
        Leave laithLeave = new Leave(LocalDate.now().minusDays(3),
                LocalTime.now(), LocalTime.now().plusHours(2),
                "Sick leave", LeaveStatus.PENDING, laith);

        Leave laithLeave2 = new Leave(LocalDate.now().minusDays(3),
                LocalTime.now(), LocalTime.now().plusHours(2),
                "for some rest", LeaveStatus.PENDING, laith);

        Set<Leave> forLaith = new HashSet<>();
        forLaith.add(laithLeave);
        forLaith.add(laithLeave2);
        Set<Leave> fornedal = new HashSet<>();
        fornedal.add(nedalLeave);
        managerRepository.saveAll(List.of(karlo, zedan));

       // directorateRepository.saveAll(List.of(directorate1, directorate2,directorate3));

        departmentRepository.saveAll(List.of(department1, department2, department3));
        directorateRepository.saveAll(List.of(directorate1, directorate2,directorate3));

        employeeRepository.saveAll(List.of(nedal, laith, mohamaad));
        leaveRepository.saveAll(List.of(laithLeave, nedalLeave,laithLeave2));
     /*leaveRepository.findAll().forEach((k) -> {
      System.out.print(k.getEmployee().getName() + " ");
     });*/



    }
}
