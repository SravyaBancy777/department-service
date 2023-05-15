package com.department.department;

import com.department.department.Controller.Controller;
import com.department.department.Controller.DepartmentController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DepartmentApplicationTests {
	@Autowired
	Controller controller;

	@Autowired
	DepartmentController departmentController;

	@Test
	void contextLoads() {
		Assertions.assertNotNull(controller);
		Assertions.assertNotNull(departmentController);
	}

	@Test
	public void applicationContextTest(){
		DepartmentApplication.main(new String[]{});
	}

	@Test
	public void controllerTest(){
		assertThat(controller.hello()).isEqualTo("HelloWorld!");
	}



}
