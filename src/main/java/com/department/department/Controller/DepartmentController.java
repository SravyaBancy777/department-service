package com.department.department.Controller;

import com.department.department.Model.DepartmentRequestModel;
import com.department.department.Model.DepartmentResponseModel;
import com.department.department.Service.DepartmentService;
import com.department.department.Shared.DepartmentDto;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;
    @GetMapping
    public ResponseEntity<List<DepartmentResponseModel>> getAllDepartments() {
        List<DepartmentDto> departments = departmentService.getAllDepartments();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        List<DepartmentResponseModel> departmentsList = new ArrayList<>();
        departments.forEach((departmentDto -> {
            DepartmentResponseModel departmentResponseModel = modelMapper.map(departmentDto, DepartmentResponseModel.class);
            departmentsList.add(departmentResponseModel);
        }));

        if (departmentsList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(departmentsList, HttpStatus.OK);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DepartmentResponseModel> createDepartments(@Valid @RequestBody DepartmentRequestModel departmentDetails) {

        String name = departmentDetails.getName();
        if (departmentService.getDepartmentByName(name) != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        DepartmentDto departmentDto = modelMapper.map(departmentDetails, DepartmentDto.class);
        DepartmentDto createdValue = departmentService.createDepartments(departmentDto);
        DepartmentResponseModel returnValue = modelMapper.map(createdValue, DepartmentResponseModel.class);

        return new ResponseEntity<>(returnValue, HttpStatus.CREATED);


    }


    @GetMapping("/id/{id}")
    public ResponseEntity<DepartmentResponseModel> getDepartmentById(@PathVariable Integer id) {
        DepartmentDto departmentDto = departmentService.getDepartmentById(id);
        if (departmentDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        DepartmentResponseModel returnValue = modelMapper.map(departmentDto, DepartmentResponseModel.class);

        return new ResponseEntity<>(returnValue, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<DepartmentResponseModel> getDepartmentByName(@PathVariable String name) {
        DepartmentDto departmentDto = departmentService.getDepartmentByName(name);

        if(departmentDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        DepartmentResponseModel returnValue = modelMapper.map(departmentDto, DepartmentResponseModel.class);
        return new ResponseEntity<>(returnValue, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<DepartmentResponseModel>> searchEmployees(
            @RequestParam(value = "state", required = false) String state,
            @RequestParam(value = "city", required = false) String city){
        List<DepartmentDto> departments = null;
        if(state != null && city != null){
            departments = departmentService.getDepartmentByStateAndCity(state,city);
        }else if(state != null){
            departments = departmentService.getDepartmentByState(state);
        }else if(city != null){
            departments = departmentService.getDepartmentByCity(city);
        }

        if(departments == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        List<DepartmentResponseModel> departmentsList = new ArrayList<>();

        departments.forEach(departmentDto -> {
            DepartmentResponseModel departmentResponseModel = modelMapper.map(departmentDto, DepartmentResponseModel.class);
            departmentsList.add(departmentResponseModel);
        });

        return new ResponseEntity<>(departmentsList, HttpStatus.OK);
    }


    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<DepartmentResponseModel> updateDepartment(@Valid @RequestBody DepartmentRequestModel departmentDetails) {
        String name = departmentDetails.getName();
        if (departmentService.getDepartmentByName(name) == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        //Getting existing user
        DepartmentDto existingUser = departmentService.getDepartmentByName(name);

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        DepartmentDto departmentDto = modelMapper.map(departmentDetails, DepartmentDto.class);
        departmentDto.setId(existingUser.getId());

        DepartmentDto createdValue = departmentService.createDepartments(departmentDto);

        DepartmentResponseModel returnValue = modelMapper.map(createdValue, DepartmentResponseModel.class);

        return new ResponseEntity<>(returnValue, HttpStatus.OK);
    }

    @DeleteMapping("/{name}")
    ResponseEntity<Void> delete(@PathVariable String name) {
        if (departmentService.getDepartmentByName(name) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        boolean res = departmentService.deleteDepartmentByName(name);
        if (res) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

