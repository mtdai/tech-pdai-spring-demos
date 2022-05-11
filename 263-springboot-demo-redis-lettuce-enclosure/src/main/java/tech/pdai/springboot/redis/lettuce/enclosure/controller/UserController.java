package tech.pdai.springboot.redis.lettuce.enclosure.controller;


import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import tech.pdai.springboot.redis.lettuce.enclosure.entity.User;
import tech.pdai.springboot.redis.lettuce.enclosure.entity.response.ResponseResult;

/**
 * @author pdai
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private RedisTemplate<String, User> redisTemplate;

    /**
     * @param user user param
     * @return user
     */
    @ApiOperation("Add")
    @PostMapping("add")
    public ResponseResult<User> add(User user) {
        redisTemplate.opsForValue().set("1", user);
        return ResponseResult.success(redisTemplate.opsForValue().get("1"));
    }

    /**
     * @return user list
     */
    @ApiOperation("Find")
    @GetMapping("find/{userId}")
    public ResponseResult<User> edit(@PathVariable("userId") String userId) {
        return ResponseResult.success(redisTemplate.opsForValue().get(userId));
    }

}