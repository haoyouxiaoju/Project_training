package com.example.controller;

import com.example.common.Result;
import com.example.entity.GoodsStock;
import com.example.service.GoodsStockService;
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

public class GoodsStockControllerTest {

    // 使用@InjectMocks注解注入要测试的GoodsStockController，Mockito会自动注入模拟依赖
    @InjectMocks
    private GoodsStockController goodsStockController;

    // 使用@Mock注解创建GoodsStockService的模拟对象
    @Mock
    private GoodsStockService goodsStockService;

    @BeforeEach
    public void setup() {
        // 初始化Mockito模拟对象
        MockitoAnnotations.openMocks(this);
    }

    // 测试新增方法
    @Test
    public void testAdd() {
        GoodsStock goodsStock = new GoodsStock(); // 创建一个GoodsStock对象示例，可按需设置属性
        doNothing().when(goodsStockService).add(goodsStock);

        Result result = goodsStockController.add(goodsStock);

        assertEquals(Result.success().getCode(), result.getCode());
        assertEquals(Result.success().getMsg(), result.getMsg());

        verify(goodsStockService).add(goodsStock);
    }

    // 测试删除方法
    @Test
    public void testDeleteById() {
        Integer id = 1;
        doNothing().when(goodsStockService).deleteById(id);

        Result result = goodsStockController.deleteById(id);

        assertEquals(Result.success().getCode(), result.getCode());
        assertEquals(Result.success().getMsg(), result.getMsg());

        verify(goodsStockService).deleteById(id);
    }

    // 测试修改方法
    @Test
    public void testUpdateById() {
        GoodsStock goodsStock = new GoodsStock();
        doNothing().when(goodsStockService).updateById(goodsStock);

        Result result = goodsStockController.updateById(goodsStock);

        assertEquals(Result.success().getCode(), result.getCode());
        assertEquals(Result.success().getMsg(), result.getMsg());

        verify(goodsStockService).updateById(goodsStock);
    }

    // 测试根据ID查询方法
    @Test
    public void testSelectById() {
        Integer id = 1;
        GoodsStock mockGoodsStock = new GoodsStock(); // 模拟查询到的GoodsStock对象，可设置属性
        when(goodsStockService.selectById(id)).thenReturn(mockGoodsStock);

        Result result = goodsStockController.selectById(id);

        assertEquals(Result.success(mockGoodsStock).getCode(), result.getCode());
        assertEquals(Result.success(mockGoodsStock).getMsg(), result.getMsg());
        assertNotNull(result.getData());

        verify(goodsStockService).selectById(id);
    }

    // 测试查询所有方法
    @Test
    public void testSelectAll() {
        GoodsStock filterGoodsStock = new GoodsStock();
        List<GoodsStock> mockList = new ArrayList<>();
        mockList.add(new GoodsStock());
        when(goodsStockService.selectAll(filterGoodsStock)).thenReturn(mockList);

        Result result = goodsStockController.selectAll(filterGoodsStock);

        assertEquals(Result.success(mockList).getCode(), result.getCode());
        assertEquals(Result.success(mockList).getMsg(), result.getMsg());
        assertNotNull(result.getData());

        verify(goodsStockService).selectAll(filterGoodsStock);
    }

    // 测试分页查询方法
    @Test
    public void testSelectPage() {
        GoodsStock filterGoodsStock = new GoodsStock();
        Integer pageNum = 1;
        Integer pageSize = 10;
        PageInfo<GoodsStock> mockPageInfo = new PageInfo<>();
        mockPageInfo.setList(new ArrayList<>());
        when(goodsStockService.selectPage(filterGoodsStock, pageNum, pageSize)).thenReturn(mockPageInfo);

        Result result = goodsStockController.selectPage(filterGoodsStock, pageNum, pageSize);

        assertEquals(Result.success(mockPageInfo).getCode(), result.getCode());
        assertEquals(Result.success(mockPageInfo).getMsg(), result.getMsg());
        assertNotNull(result.getData());

        verify(goodsStockService).selectPage(filterGoodsStock, pageNum, pageSize);
    }
}