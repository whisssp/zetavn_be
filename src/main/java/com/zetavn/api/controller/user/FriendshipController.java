package com.zetavn.api.controller.user;

import com.zetavn.api.payload.request.FriendshipRequest;
import com.zetavn.api.payload.response.ApiResponse;
import com.zetavn.api.payload.response.FriendshipResponse;
import com.zetavn.api.payload.response.OverallUserResponse;
import com.zetavn.api.payload.response.UserResponse;
import com.zetavn.api.service.FriendshipService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v0")
public class FriendshipController {
    private final FriendshipService friendshipService;
    FriendshipController(FriendshipService friendshipService) {
        this.friendshipService = friendshipService;
    }

    @PostMapping("/add-friend")
    public ApiResponse<FriendshipResponse> sendRequest(@RequestBody FriendshipRequest friendshipRequest) {
        return friendshipService.sendRequest(friendshipRequest);
    }

    @GetMapping("/friend-requests")
    public ApiResponse<List<OverallUserResponse>> getRequest(@RequestParam String id) {
        return friendshipService.getReceiverFriendRequests(id);
    }

    @PutMapping("/friend-requests/accept")
    public ApiResponse<FriendshipResponse> accept(@RequestParam Long id) {
        return friendshipService.accept(id);
    }

    @Transactional
    @PutMapping("/friend-requests/reject")
    public ApiResponse<FriendshipResponse> reject(@RequestParam Long id) {
        return friendshipService.rejected(id);
    }

    @GetMapping("/users/{id}/friends")
    public ApiResponse<List<OverallUserResponse>> friends(@PathVariable("id") String id) {
        return friendshipService.getFriendsByUserId(id);
    }

    @GetMapping("/suggestions")
    public ApiResponse<List<OverallUserResponse>> friendSuggestions(@RequestParam String id) {
        return friendshipService.getFriendSuggestions(id);
    }
}
