package com.example.controller;

import com.example.common.Result;
import com.example.entity.Account;
import com.example.entity.User;
import com.example.service.AdminService;
import com.example.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class WebControllerTest {

    // 使用@InjectMocks注解将被测试的WebController注入进来，Mockito会自动将模拟的依赖注入到这个对象中
    @InjectMocks
    private WebController webController;

    // 使用@Mock注解创建AdminService的模拟对象，用于模拟管理员服务层的行为
    @Mock
    private AdminService adminService;

    // 使用@Mock注解创建UserService的模拟对象，用于模拟用户服务层的行为
    @Mock
    private UserService userService;

    @BeforeEach
    public void setup() {
        // 初始化Mockito的模拟对象
        MockitoAnnotations.openMocks(this);
    }

    // 测试默认请求接口（"/"）方法
    @Test
    public void testHello() {
        Result result = webController.hello();
        assertEquals(Result.success().getCode(), result.getCode());
        assertEquals(Result.success().getMsg(), result.getMsg());
    }

    // 测试登录方法
    @Test
    public void testLogin() {
        Account account = new Account();
        account.setRole("ADMIN");

        Account mockAccount = new Account(); // 模拟登录成功返回的Account对象，可按需设置属性

        when(adminService.login(account)).thenReturn(mockAccount);

        Result result = webController.login(account);

        assertEquals(Result.success(mockAccount).getCode(), result.getCode());
        assertEquals(Result.success(mockAccount).getMsg(), result.getMsg());
        assertNotNull(result.getData());

        verify(adminService).login(account);

        // 再测试用户登录情况
        account.setRole("USER");
        when(userService.login(account)).thenReturn(mockAccount);

        result = webController.login(account);

        assertEquals(Result.success(mockAccount).getCode(), result.getCode());
        assertEquals(Result.success(mockAccount).getMsg(), result.getMsg());
        assertNotNull(result.getData());

        verify(userService).login(account);
    }

//    // 测试注册方法
//    @Test
//    public void testRegister() {
//        User user = new User();
//        doNothing().when(userService).register(user);
//
//        Result result = webController.register(user);
//
//        assertEquals(Result.success().getCode(), result.getCode());
//        assertEquals(Result.success().getMsg(), result.getMsg());
//
//        verify(userService).register(user);
//    }

    // 测试修改密码方法
    @Test
    public void testUpdatePassword() {
        Account account = new Account();
        account.setRole("ADMIN");
        doNothing().when(adminService).updatePassword(account);

        Result result = webController.updatePassword(account);

        assertEquals(Result.success().getCode(), result.getCode());
        assertEquals(Result.success().getMsg(), result.getMsg());

        verify(adminService).updatePassword(account);

        // 再测试用户修改密码情况
        account.setRole("USER");
        doNothing().when(userService).updatePassword(account);

        result = webController.updatePassword(account);

        assertEquals(Result.success().getCode(), result.getCode());
        assertEquals(Result.success().getMsg(), result.getMsg());

        verify(userService).updatePassword(account);
    }
}