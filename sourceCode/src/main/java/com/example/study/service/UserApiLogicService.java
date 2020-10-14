package com.example.study.service;

import com.example.study.ifc.CrudInterface;
import com.example.study.model.entity.User;
import com.example.study.model.enumclass.UserStatus;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.UserApiResponse;
import com.example.study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserApiLogicService extends BaseService<UserApiRequest, UserApiResponse, User> {


    // 1. request data 가져오기
    // 2. User 생성
    // 3. 생성된 데이터 -> UserApiReponse return

    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {

        // 1. request data 가져오기
        UserApiRequest userApiRequest = request.getData();

        // 2. User 생성
        User user = User.builder()
                .account(userApiRequest.getAccount())
                .password(userApiRequest.getPassword())
                .status(UserStatus.REGISTERED)
                .email(userApiRequest.getEmail())
                .phoneNumber(userApiRequest.getPhoneNumber())
                .registeredAt(LocalDateTime.now())
                .build();

        User newUser = baseRepository.save(user);

        // 3. 생성된 데이터 -> UserApiReponse return
        return response(newUser);
    }

    @Override
    public Header<UserApiResponse> read(Long id) {

        // 1. Id를 가지고 repository를 통해 getOne 혹은 getById를 통해 해당 정보를 가져온다.
        Optional<User> optional = baseRepository.findById(id);

        // 2. user -> UserApiResponse return
        //
        return optional
                .map(user -> response(user))
                .orElseGet(
                        ()->Header.ERROR("No Data")
                );
        //map: 다른 return 형태로 바꾸는 것.
        //user가 있으면 map에 해당하여 reponse 메서드를 통해 return 되고,
        //orElseGet: user가 없으면 () 메서드 하나 호출하여 헤더에 에러를 넘기면서 데이터가 없다는 것을 return
    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {

        // 1. request data 가져오기
        UserApiRequest userApiRequest = request.getData();

        // 2. id를 가지고 User data 찾기
        Optional<User> optional = baseRepository.findById(userApiRequest.getId());

        return optional.map(user -> {

            // 3. update
            user.setAccount(userApiRequest.getAccount())
                    .setPassword(userApiRequest.getPassword())
                    .setStatus(userApiRequest.getStatus())
                    .setPhoneNumber(userApiRequest.getPhoneNumber())
                    .setEmail(userApiRequest.getEmail())
                    .setRegisteredAt(userApiRequest.getRegisteredAt())
                    .setUnregisteredAt(userApiRequest.getUnregisteredAt());

            return user;
        })
        .map(user -> baseRepository.save(user))         // update -> newUser
        .map(updateUser->response(updateUser))          // userApiResponse
        .orElseGet(()->Header.ERROR("No User"));
    }

    @Override
    public Header delete(Long id) {

        // 1. id를 통해 repository에서 user를 찾는다.
        Optional<User> optional = baseRepository.findById(id);

        // 2. repository를 통해 delete
        // 3. response
        return optional.map(user -> {
            baseRepository.delete(user);
            return Header.OK();
        })
        .orElseGet(()->Header.ERROR("No Data"));


    }

    private Header<UserApiResponse> response(User user){
        // user -> userApiResponse
        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword())      // TODO: 암호화, 길이
                .status(user.getStatus())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .registeredAt(user.getRegisteredAt())
                .unregisteredAt(user.getUnregisteredAt())
                .build();

        // return Header + data
        return Header.OK(userApiResponse);
    }

}
