package com.example.controller;

import com.example.common.Result;
import com.example.entity.Admin;
import com.example.service.AdminService;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

public class AdminControllerTest {

    // 使用@InjectMocks注解将被测试的AdminController注入进来，Mockito会自动将模拟的依赖注入到这个对象中
    @InjectMocks
    private AdminController adminController;

    // 使用@Mock注解创建AdminService的模拟对象，用于模拟服务层的行为
    @Mock
    private AdminService adminService;

    @BeforeEach
    public void setup() {
        // 初始化Mockito的模拟对象
        MockitoAnnotations.openMocks(this);
    }

    // 测试新增方法
    @Test
    public void testAdd() {
        Admin admin = new Admin(); // 创建一个Admin对象作为新增的数据示例，可以根据实际情况设置属性
        doNothing().when(adminService).add(admin);
        Result result = adminController.add(admin);

        assertEquals(Result.success().getCode(), result.getCode());
        assertEquals(Result.success().getMsg(), result.getMsg());

        verify(adminService).add(admin);
    }

    // 测试删除方法
    @Test
    public void testDeleteById() {
        Integer id = 1;
        doNothing().when(adminService).deleteById(id);

        Result result = adminController.deleteById(id);

        assertEquals(Result.success().getCode(), result.getCode());
        assertEquals(Result.success().getMsg(), result.getMsg());

        verify(adminService).deleteById(id);
    }

    // 测试修改方法
    @Test
    public void testUpdateById() {
        Admin admin = new Admin();
        doNothing().when(adminService).updateById(admin);

        Result result = adminController.updateById(admin);

        assertEquals(Result.success().getCode(), result.getCode());
        assertEquals(Result.success().getMsg(), result.getMsg());

        verify(adminService).updateById(admin);
    }

    // 测试根据ID查询方法
    @Test
    public void testSelectById() {
        Integer id = 1;
        Admin mockAdmin = new Admin(); // 模拟查询到的Admin对象，可按需设置属性
        when(adminService.selectById(id)).thenReturn(mockAdmin);

        Result result = adminController.selectById(id);

        assertEquals(Result.success(mockAdmin).getCode(), result.getCode());
        assertEquals(Result.success(mockAdmin).getMsg(), result.getMsg());
        assertNotNull(result.getData());

        verify(adminService).selectById(id);
    }

    // 测试查询所有方法
    @Test
    public void testSelectAll() {
        Admin filterAdmin = new Admin();
        List<Admin> mockList = new ArrayList<>();
        mockList.add(new Admin());
        when(adminService.selectAll(filterAdmin)).thenReturn(mockList);

        Result result = adminController.selectAll(filterAdmin);

        assertEquals(Result.success(mockList).getCode(), result.getCode());
        assertEquals(Result.success(mockList).getMsg(), result.getMsg());
        assertNotNull(result.getData());

        verify(adminService).selectAll(filterAdmin);
    }

    // 测试分页查询方法
    @Test
    public void testSelectPage() {
        Admin filterAdmin = new Admin();
        Integer pageNum = 1;
        Integer pageSize = 10;
        PageInfo<Admin> mockPageInfo = new PageInfo<>();
        mockPageInfo.setList(new ArrayList<>());
        when(adminService.selectPage(filterAdmin, pageNum, pageSize)).thenReturn(mockPageInfo);

        Result result = adminController.selectPage(filterAdmin, pageNum, pageSize);

        assertEquals(Result.success(mockPageInfo).getCode(), result.getCode());
        assertEquals(Result.success(mockPageInfo).getMsg(), result.getMsg());
        assertNotNull(result.getData());

        verify(adminService).selectPage(filterAdmin, pageNum, pageSize);
    }
}