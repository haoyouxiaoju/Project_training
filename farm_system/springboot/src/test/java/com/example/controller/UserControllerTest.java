package com.example.controller;

import com.example.common.Result;
import com.example.entity.User;
import com.example.service.UserService;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

public class UserControllerTest {

    // 使用@InjectMocks注解将被测试的UserController注入进来，Mockito会自动将模拟的依赖注入到这个对象中
    @InjectMocks
    private UserController userController;

    // 使用@Mock注解创建UserService的模拟对象，用于模拟服务层的行为
    @Mock
    private UserService userService;

    @BeforeEach
    public void setup() {
        // 初始化Mockito的模拟对象
        MockitoAnnotations.openMocks(this);
    }

    // 测试新增方法
    @Test
    public void testAdd() {
        User user = new User(); // 创建一个User对象示例，可根据实际情况设置属性，比如用户名、密码等
        doNothing().when(userService).add(user);

        Result result = userController.add(user);

        assertEquals(Result.success().getCode(), result.getCode());
        assertEquals(Result.success().getMsg(), result.getMsg());

        verify(userService).add(user);
    }

    // 测试删除方法
    @Test
    public void testDeleteById() {
        Integer id = 1;
        doNothing().when(userService).deleteById(id);

        Result result = userController.deleteById(id);

        assertEquals(Result.success().getCode(), result.getCode());
        assertEquals(Result.success().getMsg(), result.getMsg());

        verify(userService).deleteById(id);
    }

    // 测试修改方法
    @Test
    public void testUpdateById() {
        User user = new User();
        doNothing().when(userService).updateById(user);

        Result result = userController.updateById(user);

        assertEquals(Result.success().getCode(), result.getCode());
        assertEquals(Result.success().getMsg(), result.getMsg());

        verify(userService).updateById(user);
    }

    // 测试根据ID查询方法
    @Test
    public void testSelectById() {
        Integer id = 1;
        User mockUser = new User(); // 模拟查询到的User对象，可按需设置属性，如用户名、年龄等
        when(userService.selectById(id)).thenReturn(mockUser);

        Result result = userController.selectById(id);

        assertEquals(Result.success(mockUser).getCode(), result.getCode());
        assertEquals(Result.success(mockUser).getMsg(), result.getMsg());
        assertNotNull(result.getData());

        verify(userService).selectById(id);
    }

    // 测试查询所有方法
    @Test
    public void testSelectAll() {
        User filterUser = new User();
        List<User> mockList = new ArrayList<>();
        mockList.add(new User());
        when(userService.selectAll(filterUser)).thenReturn(mockList);

        Result result = userController.selectAll(filterUser);

        assertEquals(Result.success(mockList).getCode(), result.getCode());
        assertEquals(Result.success(mockList).getMsg(), result.getMsg());
        assertNotNull(result.getData());

        verify(userService).selectAll(filterUser);
    }

    // 测试分页查询方法
    @Test
    public void testSelectPage() {
        User filterUser = new User();
        Integer pageNum = 1;
        Integer pageSize = 10;
        PageInfo<User> mockPageInfo = new PageInfo<>();
        mockPageInfo.setList(new ArrayList<>());
        when(userService.selectPage(filterUser, pageNum, pageSize)).thenReturn(mockPageInfo);

        Result result = userController.selectPage(filterUser, pageNum, pageSize);

        assertEquals(Result.success(mockPageInfo).getCode(), result.getCode());
        assertEquals(Result.success(mockPageInfo).getMsg(), result.getMsg());
        assertNotNull(result.getData());

        verify(userService).selectPage(filterUser, pageNum, pageSize);
    }
}