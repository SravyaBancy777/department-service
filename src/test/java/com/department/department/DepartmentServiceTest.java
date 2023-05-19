package com.department.department;

import com.department.department.Data.DepartmentEntity;
import com.department.department.Data.DepartmentRepository;
import com.department.department.Service.DepartmentServiceImpl;
import com.department.department.Shared.DepartmentDto;
import com.department.department.Shared.Utils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DepartmentServiceTest {
    @InjectMocks

    DepartmentServiceImpl departmentService;

    @Mock
    DepartmentRepository departmentRepository;


    @Mock
    Utils utils;

    @Test
    public void getAllDepartmentsTest(){
        DepartmentDto departmentDto1 = new DepartmentDto(1, "Name", "City", "State","Country", "Zipcode");
        DepartmentDto departmentDto = new DepartmentDto(2, "Name1", "City1","State1", "Country1","Zipcode1");


        List<DepartmentDto> departmentDtoList = new ArrayList<>();
        departmentDtoList.add(departmentDto1);
        departmentDtoList.add(departmentDto);

        DepartmentEntity departmentEntity = new DepartmentEntity(1, "Name", "City", "State","Country", "Zipcode");
        DepartmentEntity departmentEntity1 = new DepartmentEntity(1, "Name", "City", "State","Country", "Zipcode");

        Iterable<DepartmentEntity> departmentEntities = Arrays.asList(departmentEntity, departmentEntity1);

        when(departmentRepository.findAll()).thenReturn(departmentEntities);
        when(departmentService.getAllDepartments()).thenReturn(departmentDtoList);
        when(utils.getDepartmentDtoList(departmentEntities)).thenReturn(departmentDtoList);

        List<DepartmentDto> departmentDtos = departmentService.getAllDepartments();

        assertThat(departmentDtos.get(0).getName()).isEqualTo(departmentDto1.getName());
        assertThat(departmentDtos.get(0).getState()).isEqualTo(departmentDto1.getState());
        assertThat(departmentDtos.get(0).getCity()).isEqualTo(departmentDto1.getCity());
        assertThat(departmentDtos.get(0).getCountry()).isEqualTo(departmentDto1.getCountry());
        assertThat(departmentDtos.get(0).getZipcode()).isEqualTo(departmentDto1.getZipcode());


    }

    @Test
    public void getDepartmentByNameTest(){
        DepartmentEntity departmentEntity = new DepartmentEntity();
        String name = "Name";

        when(departmentRepository.findByName(name)).thenReturn(departmentEntity);

        DepartmentDto departmentDto = departmentService.getDepartmentByName(name);

        assertThat(departmentDto.getName()).isEqualTo(departmentEntity.getName());
        assertThat(departmentDto.getCity()).isEqualTo(departmentEntity.getCity());
        assertThat(departmentDto.getState()).isEqualTo(departmentEntity.getState());
        assertThat(departmentDto.getCountry()).isEqualTo(departmentEntity.getCountry());
        assertThat(departmentDto.getZipcode()).isEqualTo(departmentEntity.getZipcode());


    }

    @Test
    public void getDepartmentByNameNullTest(){

        DepartmentEntity departmentEntity = null;

        String name = "Name";

        when(departmentRepository.findByName(name)).thenReturn(departmentEntity);
        DepartmentDto departmentDto = departmentService.getDepartmentByName(name);


        assertThat(departmentDto).isNull();

    }

    @Test
    public void getDepartmentByCityTest(){

        DepartmentDto departmentDto1 = new DepartmentDto(1, "Name", "City", "State","Country", "Zipcode");
        DepartmentDto departmentDto = new DepartmentDto(2, "Name1", "City1","State1", "Country1","Zipcode1");


        List<DepartmentDto> departmentDtoList = new ArrayList<>();
        departmentDtoList.add(departmentDto1);
        departmentDtoList.add(departmentDto);

        DepartmentEntity departmentEntity = new DepartmentEntity(1, "Name", "City", "State","Country", "Zipcode");
        DepartmentEntity departmentEntity1 = new DepartmentEntity(1, "Name", "City", "State","Country", "Zipcode");

        Iterable<DepartmentEntity> departmentEntities = Arrays.asList(departmentEntity, departmentEntity1);

        String city = "City";

        when(departmentRepository.findByCity(city)).thenReturn(departmentEntities);
        when(departmentService.getDepartmentByCity(city)).thenReturn(departmentDtoList);
        when(utils.getDepartmentDtoList(departmentEntities)).thenReturn(departmentDtoList);

        List<DepartmentDto> departmentDtos = departmentService.getDepartmentByCity(city);

        assertThat(departmentDtos.get(0).getName()).isEqualTo(departmentDto1.getName());
        assertThat(departmentDtos.get(0).getState()).isEqualTo(departmentDto1.getState());
        assertThat(departmentDtos.get(0).getCity()).isEqualTo(departmentDto1.getCity());
        assertThat(departmentDtos.get(0).getCountry()).isEqualTo(departmentDto1.getCountry());
        assertThat(departmentDtos.get(0).getZipcode()).isEqualTo(departmentDto1.getZipcode());

    }

    @Test
    public void getDepartmentByStateTest(){
        DepartmentDto departmentDto1 = new DepartmentDto(1, "Name", "City", "State","Country", "Zipcode");
        DepartmentDto departmentDto = new DepartmentDto(2, "Name1", "City1","State1", "Country1","Zipcode1");


        List<DepartmentDto> departmentDtoList = new ArrayList<>();
        departmentDtoList.add(departmentDto1);
        departmentDtoList.add(departmentDto);

        DepartmentEntity departmentEntity = new DepartmentEntity(1, "Name", "City", "State","Country", "Zipcode");
        DepartmentEntity departmentEntity1 = new DepartmentEntity(1, "Name", "City", "State","Country", "Zipcode");

        Iterable<DepartmentEntity> departmentEntities = Arrays.asList(departmentEntity, departmentEntity1);

        String state = "State";

        when(departmentRepository.findByState(state)).thenReturn(departmentEntities);
        when(departmentService.getDepartmentByState(state)).thenReturn(departmentDtoList);
        when(utils.getDepartmentDtoList(departmentEntities)).thenReturn(departmentDtoList);

        List<DepartmentDto> departmentDtos = departmentService.getDepartmentByState(state);

        assertThat(departmentDtos.get(0).getName()).isEqualTo(departmentDto1.getName());
        assertThat(departmentDtos.get(0).getState()).isEqualTo(departmentDto1.getState());
        assertThat(departmentDtos.get(0).getCity()).isEqualTo(departmentDto1.getCity());
        assertThat(departmentDtos.get(0).getCountry()).isEqualTo(departmentDto1.getCountry());
        assertThat(departmentDtos.get(0).getZipcode()).isEqualTo(departmentDto1.getZipcode());

    }

    @Test
    public void getDepartmentByStateAndCityTest(){

        DepartmentDto departmentDto1 = new DepartmentDto(1, "Name", "City", "State","Country", "Zipcode");
        DepartmentDto departmentDto = new DepartmentDto(2, "Name1", "City1","State1", "Country1","Zipcode1");


        List<DepartmentDto> departmentDtoList = new ArrayList<>();
        departmentDtoList.add(departmentDto1);
        departmentDtoList.add(departmentDto);

        DepartmentEntity departmentEntity = new DepartmentEntity(1, "Name", "City", "State","Country", "Zipcode");
        DepartmentEntity departmentEntity1 = new DepartmentEntity(1, "Name", "City", "State","Country", "Zipcode");

        Iterable<DepartmentEntity> departmentEntities = Arrays.asList(departmentEntity, departmentEntity1);

        String state = "State";
        String city = "City";

        when(departmentRepository.findByStateAndCity(state, city)).thenReturn(departmentEntities);
        when(departmentService.getDepartmentByStateAndCity(state, city)).thenReturn(departmentDtoList);
        when(utils.getDepartmentDtoList(departmentEntities)).thenReturn(departmentDtoList);

        List<DepartmentDto> departmentDtos = departmentService.getDepartmentByStateAndCity(state, city);

        assertThat(departmentDtos.get(0).getName()).isEqualTo(departmentDto1.getName());
        assertThat(departmentDtos.get(0).getState()).isEqualTo(departmentDto1.getState());
        assertThat(departmentDtos.get(0).getCity()).isEqualTo(departmentDto1.getCity());
        assertThat(departmentDtos.get(0).getCountry()).isEqualTo(departmentDto1.getCountry());
        assertThat(departmentDtos.get(0).getZipcode()).isEqualTo(departmentDto1.getZipcode());

    }

    @Test
    public void getDepartmentByIdTest(){
        Optional<DepartmentEntity> departmentEntity = Optional.of(new DepartmentEntity(1, "Name", "City", "State","Country", "Zipcode"));

        when(departmentRepository.findById(1L)).thenReturn(departmentEntity);

        DepartmentDto departmentDto1 = departmentService.getDepartmentById(1L);
        assertThat(departmentDto1.getName()).isEqualTo(departmentEntity.get().getName());
        assertThat(departmentDto1.getCity()).isEqualTo(departmentEntity.get().getCity());
        assertThat(departmentDto1.getState()).isEqualTo(departmentEntity.get().getState());
        assertThat(departmentDto1.getCountry()).isEqualTo(departmentEntity.get().getCountry());
        assertThat(departmentDto1.getZipcode()).isEqualTo(departmentEntity.get().getZipcode());


    }
    @Test
    public void getDepartmentByCountryTest(){
        DepartmentDto departmentDto1 = new DepartmentDto(1, "Name", "City", "State","Country", "Zipcode");
        DepartmentDto departmentDto = new DepartmentDto(2, "Name1", "City1","State1", "Country1","Zipcode1");


        List<DepartmentDto> departmentDtoList = new ArrayList<>();
        departmentDtoList.add(departmentDto1);
        departmentDtoList.add(departmentDto);

        DepartmentEntity departmentEntity = new DepartmentEntity(1, "Name", "City", "State","Country", "Zipcode");
        DepartmentEntity departmentEntity1 = new DepartmentEntity(1, "Name", "City", "State","Country", "Zipcode");

        Iterable<DepartmentEntity> departmentEntities = Arrays.asList(departmentEntity, departmentEntity1);

        String country = "Country";

        when(departmentRepository.findByCountry(country)).thenReturn(departmentEntities);
        when(departmentService.getDepartmentByCountry(country)).thenReturn(departmentDtoList);
        when(utils.getDepartmentDtoList(departmentEntities)).thenReturn(departmentDtoList);

        List<DepartmentDto> departmentDtos = departmentService.getDepartmentByCountry(country);

        assertThat(departmentDtos.get(0).getName()).isEqualTo(departmentDto1.getName());
        assertThat(departmentDtos.get(0).getState()).isEqualTo(departmentDto1.getState());
        assertThat(departmentDtos.get(0).getCity()).isEqualTo(departmentDto1.getCity());
        assertThat(departmentDtos.get(0).getCountry()).isEqualTo(departmentDto1.getCountry());
        assertThat(departmentDtos.get(0).getZipcode()).isEqualTo(departmentDto1.getZipcode());

    }
    @Test
    public void getDepartmentByZipcodeTest(){
        DepartmentDto departmentDto1 = new DepartmentDto(1, "Name", "City", "State","Country", "Zipcode");
        DepartmentDto departmentDto = new DepartmentDto(2, "Name1", "City1","State1", "Country1","Zipcode1");


        List<DepartmentDto> departmentDtoList = new ArrayList<>();
        departmentDtoList.add(departmentDto1);
        departmentDtoList.add(departmentDto);

        DepartmentEntity departmentEntity = new DepartmentEntity(1, "Name", "City", "State","Country", "Zipcode");
        DepartmentEntity departmentEntity1 = new DepartmentEntity(1, "Name", "City", "State","Country", "Zipcode");

        Iterable<DepartmentEntity> departmentEntities = Arrays.asList(departmentEntity, departmentEntity1);

        String zipcode = "Zipcode";

        when(departmentRepository.findByZipcode(zipcode)).thenReturn(departmentEntities);
        when(departmentService.getDepartmentByZipcode(zipcode)).thenReturn(departmentDtoList);
        when(utils.getDepartmentDtoList(departmentEntities)).thenReturn(departmentDtoList);

        List<DepartmentDto> departmentDtos = departmentService.getDepartmentByZipcode(zipcode);

        assertThat(departmentDtos.get(0).getName()).isEqualTo(departmentDto1.getName());
        assertThat(departmentDtos.get(0).getState()).isEqualTo(departmentDto1.getState());
        assertThat(departmentDtos.get(0).getCity()).isEqualTo(departmentDto1.getCity());
        assertThat(departmentDtos.get(0).getCountry()).isEqualTo(departmentDto1.getCountry());
        assertThat(departmentDtos.get(0).getZipcode()).isEqualTo(departmentDto1.getZipcode());

    }

    @Test
    public void createDepartmentsTest(){
        DepartmentDto departmentDto = new DepartmentDto(1, "Name", "City", "State","Country", "Zipcode");

        DepartmentDto departmentDto1 = departmentService.createDepartments(departmentDto);

        assertThat(departmentDto1.getName()).isEqualTo(departmentDto.getName());
        assertThat(departmentDto1.getCity()).isEqualTo(departmentDto.getCity());
        assertThat(departmentDto1.getState()).isEqualTo(departmentDto.getState());
        assertThat(departmentDto1.getCountry()).isEqualTo(departmentDto.getCountry());
        assertThat(departmentDto1.getZipcode()).isEqualTo(departmentDto.getZipcode());

    }

    @Test
    public void deleteDepartmentByNameTest(){
        String name = "Name";
        doNothing().when(departmentRepository).deleteByName(name);
        boolean res = departmentService.deleteDepartmentByName(name);
        assertThat(res).isEqualTo(true);
    }
    @Test
    public void deleteUserByNameExceptionTest() {
        String name = "Name";
        doThrow(RuntimeException.class).when(departmentRepository).deleteByName(name);
        boolean res = departmentService.deleteDepartmentByName(name);
        assertThat(res).isEqualTo(false);
    }

}
