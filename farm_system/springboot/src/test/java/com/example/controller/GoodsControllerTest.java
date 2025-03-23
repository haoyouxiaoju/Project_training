package com.example.controller;

import com.example.common.Result;
import com.example.entity.Goods;
import com.example.service.GoodsService;
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

public class GoodsControllerTest {

    // 使用@InjectMocks注解将被测试的GoodsController注入进来，Mockito会自动将模拟的依赖注入到这个对象中
    @InjectMocks
    private GoodsController goodsController;

    // 使用@Mock注解创建GoodsService的模拟对象，用于模拟服务层的行为
    @Mock
    private GoodsService goodsService;

    @BeforeEach
    public void setup() {
        // 初始化Mockito的模拟对象
        MockitoAnnotations.openMocks(this);
    }

    // 测试新增方法
    @Test
    public void testAdd() {
        Goods goods = new Goods(); // 创建一个Goods对象示例，可根据实际情况设置属性
        doNothing().when(goodsService).add(goods);

        Result result = goodsController.add(goods);

        assertEquals(Result.success().getCode(), result.getCode());
        assertEquals(Result.success().getMsg(), result.getMsg());

        verify(goodsService).add(goods);
    }

    // 测试删除方法
    @Test
    public void testDeleteById() {
        Integer id = 1;
        doNothing().when(goodsService).deleteById(id);

        Result result = goodsController.deleteById(id);

        assertEquals(Result.success().getCode(), result.getCode());
        assertEquals(Result.success().getMsg(), result.getMsg());

        verify(goodsService).deleteById(id);
    }

    // 测试修改方法
    @Test
    public void testUpdateById() {
        Goods goods = new Goods();
        doNothing().when(goodsService).updateById(goods);

        Result result = goodsController.updateById(goods);

        assertEquals(Result.success().getCode(), result.getCode());
        assertEquals(Result.success().getMsg(), result.getMsg());

        verify(goodsService).updateById(goods);
    }

    // 测试根据ID查询方法
    @Test
    public void testSelectById() {
        Integer id = 1;
        Goods mockGoods = new Goods(); // 模拟查询到的Goods对象，按需设置属性
        when(goodsService.selectById(id)).thenReturn(mockGoods);

        Result result = goodsController.selectById(id);

        assertEquals(Result.success(mockGoods).getCode(), result.getCode());
        assertEquals(Result.success(mockGoods).getMsg(), result.getMsg());
        assertNotNull(result.getData());

        verify(goodsService).selectById(id);
    }

    // 测试查询所有方法
    @Test
    public void testSelectAll() {
        Goods filterGoods = new Goods();
        List<Goods> mockList = new ArrayList<>();
        mockList.add(new Goods());
        when(goodsService.selectAll(filterGoods)).thenReturn(mockList);

        Result result = goodsController.selectAll(filterGoods);

        assertEquals(Result.success(mockList).getCode(), result.getCode());
        assertEquals(Result.success(mockList).getMsg(), result.getMsg());
        assertNotNull(result.getData());

        verify(goodsService).selectAll(filterGoods);
    }

    // 测试分页查询方法
    @Test
    public void testSelectPage() {
        Goods filterGoods = new Goods();
        Integer pageNum = 2;
        Integer pageSize = 10;
        PageInfo<Goods> mockPageInfo = new PageInfo<>();
        mockPageInfo.setList(new ArrayList<>());
        when(goodsService.selectPage(filterGoods, pageNum, pageSize)).thenReturn(mockPageInfo);

        Result result = goodsController.selectPage(filterGoods, pageNum, pageSize);

        assertEquals(Result.success(mockPageInfo).getCode(), result.getCode());
        assertEquals(Result.success(mockPageInfo).getMsg(), result.getMsg());
        assertNotNull(result.getData());

        verify(goodsService).selectPage(filterGoods, pageNum, pageSize);
    }
}