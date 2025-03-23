package com.example.controller;

import com.example.common.Result;
import com.example.entity.Orders;
import com.example.service.OrdersService;
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

public class OrdersControllerTest {

    // 使用@InjectMocks注解将被测试的OrdersController注入进来，Mockito会自动将模拟的依赖注入到这个对象中
    @InjectMocks
    private OrdersController ordersController;

    // 使用@Mock注解创建OrdersService的模拟对象，用于模拟服务层的行为
    @Mock
    private OrdersService ordersService;

    @BeforeEach
    public void setup() {
        // 初始化Mockito的模拟对象
        MockitoAnnotations.openMocks(this);
    }

    // 测试新增方法
    @Test
    public void testAdd() {
        Orders orders = new Orders(); // 创建一个Orders对象示例，可根据实际情况设置属性
        doNothing().when(ordersService).add(orders);

        Result result = ordersController.add(orders);

        assertEquals(Result.success().getCode(), result.getCode());
        assertEquals(Result.success().getMsg(), result.getMsg());

        verify(ordersService).add(orders);
    }

    // 测试删除方法
    @Test
    public void testDeleteById() {
        Integer id = 1;
        doNothing().when(ordersService).deleteById(id);

        Result result = ordersController.deleteById(id);

        assertEquals(Result.success().getCode(), result.getCode());
        assertEquals(Result.success().getMsg(), result.getMsg());

        verify(ordersService).deleteById(id);
    }

    // 测试修改方法
    @Test
    public void testUpdateById() {
        Orders orders = new Orders();
        doNothing().when(ordersService).updateById(orders);

        Result result = ordersController.updateById(orders);

        assertEquals(Result.success().getCode(), result.getCode());
        assertEquals(Result.success().getMsg(), result.getMsg());

        verify(ordersService).updateById(orders);
    }

    // 测试根据ID查询方法
    @Test
    public void testSelectById() {
        Integer id = 1;
        Orders mockOrders = new Orders(); // 模拟查询到的Orders对象，可按需设置属性
        when(ordersService.selectById(id)).thenReturn(mockOrders);

        Result result = ordersController.selectById(id);

        assertEquals(Result.success(mockOrders).getCode(), result.getCode());
        assertEquals(Result.success(mockOrders).getMsg(), result.getMsg());
        assertNotNull(result.getData());

        verify(ordersService).selectById(id);
    }

    // 测试查询所有方法
    @Test
    public void testSelectAll() {
        Orders filterOrders = new Orders();
        List<Orders> mockList = new ArrayList<>();
        mockList.add(new Orders());
        when(ordersService.selectAll(filterOrders)).thenReturn(mockList);

        Result result = ordersController.selectAll(filterOrders);

        assertEquals(Result.success(mockList).getCode(), result.getCode());
        assertEquals(Result.success(mockList).getMsg(), result.getMsg());
        assertNotNull(result.getData());

        verify(ordersService).selectAll(filterOrders);
    }

    // 测试分页查询方法
    @Test
    public void testSelectPage() {
        Orders filterOrders = new Orders();
        Integer pageNum = 1;
        Integer pageSize = 10;
        PageInfo<Orders> mockPageInfo = new PageInfo<>();
        mockPageInfo.setList(new ArrayList<>());
        when(ordersService.selectPage(filterOrders, pageNum, pageSize)).thenReturn(mockPageInfo);

        Result result = ordersController.selectPage(filterOrders, pageNum, pageSize);

        assertEquals(Result.success(mockPageInfo).getCode(), result.getCode());
        assertEquals(Result.success(mockPageInfo).getMsg(), result.getMsg());
        assertNotNull(result.getData());

        verify(ordersService).selectPage(filterOrders, pageNum, pageSize);
    }
}