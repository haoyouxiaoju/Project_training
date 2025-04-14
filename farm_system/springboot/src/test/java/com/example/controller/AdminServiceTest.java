package com.example.service;

import com.example.entity.Account;
import com.example.entity.Admin;
import com.example.exception.CustomException;
import com.example.mapper.AdminMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {

    @Mock
    private AdminMapper adminMapper;

    @InjectMocks
    private AdminService adminService;

    private Admin testAdmin;
    private Account testAccount;

    @BeforeEach
    public void setUp() {
        testAdmin = new Admin();
        testAdmin.setId(1);
        testAdmin.setUsername("admin");
        testAdmin.setPassword("admin123");
        testAdmin.setName("Super Admin");
        testAdmin.setRole("ADMIN");

        testAccount = new Account();
        testAccount.setUsername("admin");
        testAccount.setPassword("admin123");
        testAccount.setNewPassword("newPassword123");
    }

    // ========== 新增管理员测试 ==========
    @Test
    public void testAdd_Success() {
        when(adminMapper.selectByUsername(anyString())).thenReturn(null);
        when(adminMapper.insert(any(Admin.class))).thenReturn(1);

        Admin newAdmin = new Admin();
        newAdmin.setUsername("newAdmin");
        assertDoesNotThrow(() -> adminService.add(newAdmin));

        verify(adminMapper).insert(argThat(admin ->
                "newAdmin".equals(admin.getUsername()) &&
                        "ADMIN".equals(admin.getRole())
        ));
    }

    @Test
    public void testAdd_UserExists() {
        when(adminMapper.selectByUsername(anyString())).thenReturn(testAdmin);

        Admin existingAdmin = new Admin();
        existingAdmin.setUsername("admin");
        CustomException exception = assertThrows(CustomException.class,
                () -> adminService.add(existingAdmin));

        assertEquals("用户已存在", exception.getMessage());
    }

    @Test
    public void testAdd_DefaultPasswordAndName() {
        when(adminMapper.selectByUsername(anyString())).thenReturn(null);
        when(adminMapper.insert(any(Admin.class))).thenReturn(1);

        Admin admin = new Admin();
        admin.setUsername("test");
        adminService.add(admin);

        verify(adminMapper).insert(argThat(a ->
                "admin".equals(a.getPassword()) &&
                        "test".equals(a.getName())
        ));
    }

    // ========== 删除测试 ==========
    @Test
    public void testDeleteById_Success() {
        // 模拟返回 1（表示成功删除 1 条记录）
        when(adminMapper.deleteById(anyInt())).thenReturn(1);

        assertDoesNotThrow(() -> adminService.deleteById(1));
        verify(adminMapper).deleteById(1);
    }

    // ========== 更新测试 ==========
    @Test
    public void testUpdateById_Success() {
        when(adminMapper.updateById(any(Admin.class))).thenReturn(1);
        assertDoesNotThrow(() -> adminService.updateById(testAdmin));
        verify(adminMapper).updateById(testAdmin);
    }

    // ========== 查询测试 ==========
    @Test
    public void testSelectById_Success() {
        when(adminMapper.selectById(anyInt())).thenReturn(testAdmin);
        Admin result = adminService.selectById(1);
        assertEquals(testAdmin, result);
    }

    @Test
    public void testSelectAll_Success() {
        List<Admin> mockList = Arrays.asList(testAdmin);
        when(adminMapper.selectAll(any(Admin.class))).thenReturn(mockList);

        List<Admin> result = adminService.selectAll(new Admin());
        assertEquals(1, result.size());
        assertEquals("admin", result.get(0).getUsername());
    }

    // ========== 分页查询测试 ==========
    @Test
    public void testSelectPage_Success() {
        try (MockedStatic<PageHelper> pageHelper = mockStatic(PageHelper.class)) {
            List<Admin> mockList = Arrays.asList(testAdmin);
            when(adminMapper.selectAll(any(Admin.class))).thenReturn(mockList);

            PageInfo<Admin> result = adminService.selectPage(new Admin(), 1, 10);
            assertEquals(1, result.getList().size());
            pageHelper.verify(() -> PageHelper.startPage(1, 10));
        }
    }

    // ========== 登录测试 ==========
    @Test
    public void testLogin_Success() {
        when(adminMapper.selectByUsername(anyString())).thenReturn(testAdmin);

        Account loginAccount = new Account();
        loginAccount.setUsername("admin");
        loginAccount.setPassword("admin123");

        Account result = adminService.login(loginAccount);
        assertEquals(testAdmin.getUsername(), result.getUsername());
    }

    @Test
    public void testLogin_UserNotExists() {
        when(adminMapper.selectByUsername(anyString())).thenReturn(null);

        Account loginAccount = new Account();
        loginAccount.setUsername("unknown");
        CustomException exception = assertThrows(CustomException.class,
                () -> adminService.login(loginAccount));

        assertEquals("用户不存在", exception.getMessage());
    }

    @Test
    public void testLogin_WrongPassword() {
        when(adminMapper.selectByUsername(anyString())).thenReturn(testAdmin);

        Account loginAccount = new Account();
        loginAccount.setUsername("admin");
        loginAccount.setPassword("wrong");

        CustomException exception = assertThrows(CustomException.class,
                () -> adminService.login(loginAccount));

        assertEquals("账号或密码错误", exception.getMessage());
    }

    // ========== 修改密码测试 ==========
    @Test
    public void testUpdatePassword_Success() {
        when(adminMapper.selectByUsername(anyString())).thenReturn(testAdmin);
        when(adminMapper.updateById(any(Admin.class))).thenReturn(1);

        testAccount.setPassword("admin123"); // 原密码正确
        assertDoesNotThrow(() -> adminService.updatePassword(testAccount));

        verify(adminMapper).updateById(argThat(admin ->
                "newPassword123".equals(admin.getPassword())
        ));
    }

    @Test
    public void testUpdatePassword_UserNotExists() {
        when(adminMapper.selectByUsername(anyString())).thenReturn(null);

        CustomException exception = assertThrows(CustomException.class,
                () -> adminService.updatePassword(testAccount));

        assertEquals("用户不存在", exception.getMessage());
    }

    @Test
    public void testUpdatePassword_WrongOldPassword() {
        when(adminMapper.selectByUsername(anyString())).thenReturn(testAdmin);

        testAccount.setPassword("wrong"); // 原密码错误
        CustomException exception = assertThrows(CustomException.class,
                () -> adminService.updatePassword(testAccount));

        assertEquals("原密码错误", exception.getMessage());
    }
}