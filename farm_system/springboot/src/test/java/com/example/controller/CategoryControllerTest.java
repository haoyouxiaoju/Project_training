package com.example.controller;

import com.example.common.Result;
import com.example.entity.Category;
import com.example.service.CategoryService;
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

public class CategoryControllerTest {

    // 使用@InjectMocks注解将被测试的CategoryController注入进来，Mockito会自动将模拟的依赖注入到这个对象中
    @InjectMocks
    private CategoryController categoryController;

    // 使用@Mock注解创建CategoryService的模拟对象，用于模拟服务层的行为
    @Mock
    private CategoryService categoryService;

    @BeforeEach
    public void setup() {
        // 初始化Mockito的模拟对象
        MockitoAnnotations.openMocks(this);
    }

    // 测试新增方法
    @Test
    public void testAdd() {
        Category category = new Category(); // 创建一个Category对象作为新增的数据示例，可以根据实际情况设置属性
        doNothing().when(categoryService).add(category);

        Result result = categoryController.add(category);

        assertEquals(Result.success().getCode(), result.getCode());
        assertEquals(Result.success().getMsg(), result.getMsg());

        verify(categoryService).add(category);
    }

    // 测试删除方法
    @Test
    public void testDeleteById() {
        Integer id = 1;
        doNothing().when(categoryService).deleteById(id);

        Result result = categoryController.deleteById(id);

        assertEquals(Result.success().getCode(), result.getCode());
        assertEquals(Result.success().getMsg(), result.getMsg());

        verify(categoryService).deleteById(id);
    }

    // 测试修改方法
    @Test
    public void testUpdateById() {
        Category category = new Category();
        doNothing().when(categoryService).updateById(category);

        Result result = categoryController.updateById(category);

        assertEquals(Result.success().getCode(), result.getCode());
        assertEquals(Result.success().getMsg(), result.getMsg());

        verify(categoryService).updateById(category);
    }

    // 测试根据ID查询方法
    @Test
    public void testSelectById() {
        Integer id = 1;
        Category mockCategory = new Category(); // 模拟查询到的Category对象，可按需设置属性
        when(categoryService.selectById(id)).thenReturn(mockCategory);

        Result result = categoryController.selectById(id);

        assertEquals(Result.success(mockCategory).getCode(), result.getCode());
        assertEquals(Result.success(mockCategory).getMsg(), result.getMsg());
        assertNotNull(result.getData());

        verify(categoryService).selectById(id);
    }

    // 测试查询所有方法
    @Test
    public void testSelectAll() {
        Category filterCategory = new Category();
        List<Category> mockList = new ArrayList<>();
        mockList.add(new Category());
        when(categoryService.selectAll(filterCategory)).thenReturn(mockList);

        Result result = categoryController.selectAll(filterCategory);

        assertEquals(Result.success(mockList).getCode(), result.getCode());
        assertEquals(Result.success(mockList).getMsg(), result.getMsg());
        assertNotNull(result.getData());

        verify(categoryService).selectAll(filterCategory);
    }

    // 测试分页查询方法
    @Test
    public void testSelectPage() {
        Category filterCategory = new Category();
        Integer pageNum = 1;
        Integer pageSize = 10;
        PageInfo<Category> mockPageInfo = new PageInfo<>();
        mockPageInfo.setList(new ArrayList<>());
        when(categoryService.selectPage(filterCategory, pageNum, pageSize)).thenReturn(mockPageInfo);

        Result result = categoryController.selectPage(filterCategory, pageNum, pageSize);

        assertEquals(Result.success(mockPageInfo).getCode(), result.getCode());
        assertEquals(Result.success(mockPageInfo).getMsg(), result.getMsg());
        assertNotNull(result.getData());

        verify(categoryService).selectPage(filterCategory, pageNum, pageSize);
    }
}