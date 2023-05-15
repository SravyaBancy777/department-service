package com.department.department;

import com.department.department.Data.DepartmentEntity;
import com.department.department.Exception.NotFoundException;
import com.department.department.Model.DepartmentRequestModel;
import com.department.department.Model.DepartmentResponseModel;
import com.department.department.Shared.DepartmentDto;
import com.department.department.Shared.Utils;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UtilsTest {
    @Autowired
    Utils utils;


    @Test
    public void getDepartmentResponseModelListTest() throws NotFoundException {
        DepartmentDto departmentDto1 = new DepartmentDto(1, "Name", "City", "State","Country", "Zipcode");
        DepartmentDto departmentDto = new DepartmentDto(2, "Name1", "City1","State1", "Country1","Zipcode1");

        List<DepartmentDto> departmentDtoList = new ArrayList<>();
        departmentDtoList.add(departmentDto1);
        departmentDtoList.add(departmentDto);

        List<DepartmentResponseModel> departmentsList = utils.getDepartmentResponseModelList(departmentDtoList);

        assertThat(departmentDtoList.get(0).getName()).isEqualTo(departmentsList.get(0).getName());
        assertThat(departmentDtoList.get(0).getCity()).isEqualTo(departmentsList.get(0).getCity());
        assertThat(departmentDtoList.get(0).getState()).isEqualTo(departmentsList.get(0).getState());
        assertThat(departmentDtoList.get(0).getCountry()).isEqualTo(departmentsList.get(0).getCountry());
        assertThat(departmentDtoList.get(0).getZipcode()).isEqualTo(departmentsList.get(0).getZipcode());
    }


    @Test
    public void getDepartmentResponseModelListNotFoundExceptionTest(){
        List<DepartmentDto> list = null;

        assertThatThrownBy(() -> utils.getDepartmentResponseModelList(list))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("0 records found");
    }

    @Test
    public void getDepartmentResponseModelListNotFoundExceptionTest2(){
        List<DepartmentDto> list = new ArrayList<>();

        assertThatThrownBy(() -> utils.getDepartmentResponseModelList(list))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("0 records found");
    }

    @Test
    public void getDepartmentResponseModelTest() throws NotFoundException{
        DepartmentDto departmentDto = new DepartmentDto(1, "Name", "City", "State", "Country","Zipcode");

        DepartmentResponseModel departmentResponseModel = utils.getDepartmentResponseModel(departmentDto);

        assertThat(departmentDto.getName()).isEqualTo(departmentResponseModel.getName());
        assertThat(departmentDto.getCity()).isEqualTo(departmentResponseModel.getCity());
        assertThat(departmentDto.getState()).isEqualTo(departmentResponseModel.getState());
        assertThat(departmentDto.getCountry()).isEqualTo(departmentResponseModel.getCountry());
        assertThat(departmentDto.getZipcode()).isEqualTo(departmentResponseModel.getZipcode());

    }

    @Test
    public void getDepartmentResponseModelNotFoundExceptionTest(){
        DepartmentDto departmentDto = null;
        assertThatThrownBy(() -> utils.getDepartmentResponseModel(departmentDto))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("Department not found");

    }

    @Test
    public void getDepartmentDtoTest() throws NotFoundException {
        DepartmentRequestModel departmentDetails = new DepartmentRequestModel("Name", "City", "State", "Country","Zipcode");

        DepartmentDto departmentDto = utils.getDepartmentDto(departmentDetails);

        assertThat(departmentDto.getName()).isEqualTo(departmentDetails.getName());
        assertThat(departmentDto.getCity()).isEqualTo(departmentDetails.getCity());
        assertThat(departmentDto.getState()).isEqualTo(departmentDetails.getState());
        assertThat(departmentDto.getCountry()).isEqualTo(departmentDetails.getCountry());
        assertThat(departmentDto.getZipcode()).isEqualTo(departmentDetails.getZipcode());

    }

    @Test
    public void getDepartmentDtoNotFoundExceptionTest(){
        DepartmentRequestModel departmentDetails = null;

        assertThatThrownBy(() -> utils.getDepartmentDto(departmentDetails))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("Request object is null");
    }

    @Test
    public void getDepartmentDtoListTest(){
        DepartmentEntity departmentEntity = new DepartmentEntity(1, "Name", "City", "State","Country", "Zipcode");

        DepartmentEntity departmentEntity1 = new DepartmentEntity(2, "Name1", "City1", "State1", "Country1", "Zipcode1");

        Iterable<DepartmentEntity> departmentEntities = Arrays.asList(departmentEntity, departmentEntity1);

        List<DepartmentDto> allDepartment = utils.getDepartmentDtoList(departmentEntities);

        assertThat(allDepartment.get(0).getName()).isEqualTo(departmentEntity.getName());
        assertThat(allDepartment.get(0).getCity()).isEqualTo(departmentEntity.getCity());
        assertThat(allDepartment.get(0).getState()).isEqualTo(departmentEntity.getState());
        assertThat(allDepartment.get(0).getCountry()).isEqualTo(departmentEntity.getCountry());
        assertThat(allDepartment.get(0).getZipcode()).isEqualTo(departmentEntity.getZipcode());


    }

    @Test
    public void getDepartmentDtoListExceptionTest(){
        Iterable<DepartmentEntity> departmentEntities = Arrays.asList();

        assertThatThrownBy(() -> utils.getDepartmentDtoList(departmentEntities))
                .isInstanceOf(ValidationException.class)
                .hasMessage("List of entities is null or empty");

    }
}
