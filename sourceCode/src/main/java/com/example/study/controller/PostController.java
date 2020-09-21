package com.example.study.controller;

import com.example.study.controller.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PostController {
    // POST를 사용할 때는
    // HTML <Form> 태그를 사용할 때나,
    // ajax 비동기할 때 검색에서 사용됨
    // 추가로 http post body로 데이터를 받을 때(http post body->data)
    // json, xml, multipart-form / text-plain 형태를 지원
    // 그러나, 모든 형태를 지원할 수 없기 때문에 하나만 선택해주어야 함. default: json^R

    // 12,13은 코드 동일
//    @RequestMapping(method = RequestMethod.POST, path = "/postMethod")
    // default: json
    @PostMapping(value = "/postMethod")
    public SearchParam postMethod(@RequestBody SearchParam searchParam){ //request body에 이런 데이터를 매핑시켜 주세요.

        return searchParam;
    }



}
