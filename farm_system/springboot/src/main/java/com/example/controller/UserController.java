package com.example.controller;

import com.example.common.Result;
import com.example.entity.User;
import com.example.service.UserService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 前端操作接口
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody User user) {
        userService.add(user);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        userService.deleteById(id);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody User user) {

        // 验证头像地址是否合法
        if (user.getAvatar() != null && !user.getAvatar().isEmpty() && !isValidAvatar(user.getAvatar())) {
            return Result.error("头像不合法");
        }

        // 验证电话号码是否合法
        if (user.getPhone() != null && !user.getPhone().isEmpty() && !isValidChinesePhone(user.getPhone())) {
            return Result.error("电话号码不合法");
        }

        // 验证邮箱是否合法
        if (user.getEmail() != null && !user.getEmail().isEmpty() && !isValidEmail(user.getEmail())) {
            return Result.error("邮箱格式不合法，请输入有效的邮箱地址");
        }

        userService.updateById(user);
        return Result.success();
    }

    /**
     * 验证邮箱合法性
     * @param email 邮箱地址
     * @return 是否合法
     */
    private boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }

        // 邮箱正则：允许字母、数字、点、下划线、百分号、加号、减号，后跟@和域名
        String regex = "^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$";
        return email.matches(regex);
    }

    /**
     * 验证头像地址合法性
     * @param avatar 头像地址
     * @return 是否合法
     */
    private boolean isValidAvatar(String avatar) {
        if (avatar == null || avatar.trim().isEmpty()) {
            return false;
        }

        // 简单验证是否为合法的URL或文件路径
        // 允许http/https开头的URL或相对路径
        String urlRegex = "^https?://[\\w.-]+(:\\d+)?(/files/download/\\d+-[\\w-]+\\.(png|jpg|jpeg|gif|webp))$";
        boolean isUrlValid = avatar.matches(urlRegex);

        // 2. 检查文件扩展名是否为图片格式
        String extensionRegex = ".*\\.(png|jpg|jpeg|gif|webp)$";
        boolean isExtensionValid = avatar.toLowerCase().matches(extensionRegex);

        return isUrlValid && isExtensionValid;
    }

    /**
     * 验证中国手机号合法性
     * @param phone 手机号
     * @return 是否合法
     */
    private boolean isValidChinesePhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            return false;
        }

        // 中国手机号正则：1开头，第二位3-9，后面9位数字
        String regex = "^1[3-9]\\d{9}$";
        return phone.matches(regex);
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        User user = userService.selectById(id);
        return Result.success(user);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(User user) {
        List<User> list = userService.selectAll(user);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(User user,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<User> page = userService.selectPage(user, pageNum, pageSize);
        return Result.success(page);
    }

}