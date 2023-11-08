package com.zetavn.api.controller.admin;

import com.zetavn.api.model.dto.UserAdminDto;
import com.zetavn.api.payload.response.ApiResponse;
import com.zetavn.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v0/admins/users")
public class UserAdminController {
    @Autowired
    UserService userService;

    @GetMapping()
    public ApiResponse<?> getAllUser(
            @RequestParam(name = "status", defaultValue = " ", required = false) String status,
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize) {
        return userService.getAllUserForAdminByStatus(status, pageNumber, pageSize);
    }

    @GetMapping("/{id}")
    public ApiResponse<?> getOneUser(@PathVariable String id) {
        return userService.getOneUserForAdmin(id);
    }

    @PostMapping()
    public ApiResponse<?> create(@RequestBody UserAdminDto userAdminDto) {
        return userService.createForAdmin(userAdminDto);
    }

    @PutMapping()
    public ApiResponse<?> update(@RequestBody UserAdminDto userAdminDto) {
        return userService.updateForAdmin(userAdminDto);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<?> remove(@RequestBody String id, @RequestBody boolean isDeleted) {
        return userService.removeForAdmin(id, isDeleted);
    }
}
