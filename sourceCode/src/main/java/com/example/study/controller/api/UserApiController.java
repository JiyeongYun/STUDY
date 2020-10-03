package com.example.study.controller.api;

import com.example.study.ifc.CrudInterface;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.UserApiResponse;
import com.example.study.service.UserApiLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserApiController implements CrudInterface<UserApiRequest,UserApiResponse>{

    @Autowired
    private UserApiLogicService userApiLogicService;

    @Override
    @PostMapping("")        // /api/user
    public Header<UserApiResponse> create(@RequestBody Header<UserApiRequest> request) {
        log.info("{}", request);
        return userApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")    // /api/user/{id}
    public Header read(@PathVariable Long id) {
        return null;
    }

    // 만약 id가 아닌 다른 변수명으로 받고 싶은 경우: @PathVariable 속성 추가
//    @Override
//    @GetMapping({"ids"})
//    public Header read(@PathVariable(name="ids") Long id) {
//        return null;
//    }

    @Override
    @PutMapping("")         // /api/user
    public Header<UserApiResponse> update(@RequestBody Header<UserApiRequest> userApiRequest) {
        return null;
    }

    @Override
    @DeleteMapping("{id}")  // /api/user/{id}
    public Header delete(@PathVariable Long id) {
        return null;
    }

}

