package com.zetavn.api.controller.user;

import com.zetavn.api.model.dto.UserMentionDto;
import com.zetavn.api.payload.request.FriendshipRequest;
import com.zetavn.api.payload.response.ApiResponse;
import com.zetavn.api.payload.response.FriendRequestResponse;
import com.zetavn.api.payload.response.FriendshipResponse;
import com.zetavn.api.payload.response.Paginate;
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
    public ApiResponse<Paginate<List<FriendRequestResponse>>> getRequest(@RequestParam String id,
                                                                         @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
                                                                         @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize) {
        return friendshipService.getReceiverFriendRequests(id, pageNumber, pageSize);
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

    @GetMapping("/friends")
    public ApiResponse<Paginate<List<FriendRequestResponse>>> friends(@RequestParam String id,
                                                            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
                                                            @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize) {
        return friendshipService.getFriendsByUserId(id, pageNumber, pageSize);
    }

    @GetMapping("/suggestions")
    public ApiResponse<Paginate<List<FriendRequestResponse>>> friendSuggestions(@RequestParam String id,
                                                                      @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
                                                                      @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize) {
        return friendshipService.getFriendSuggestions(id, pageNumber, pageSize);
    }
}
