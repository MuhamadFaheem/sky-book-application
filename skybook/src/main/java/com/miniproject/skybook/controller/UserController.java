package com.miniproject.skybook.controller;

import com.miniproject.skybook.constant.ConstantAPIPath;
import com.miniproject.skybook.constant.Messages;
import com.miniproject.skybook.model.dto.request.AuthRequestDTO;
import com.miniproject.skybook.model.dto.request.CustomerUserAuthRequestDTO;
import com.miniproject.skybook.model.dto.response.AuthLoginResponseDTO;
import com.miniproject.skybook.model.dto.response.AuthRegistResponseDTO;
import com.miniproject.skybook.model.dto.response.CommonResponse;
import com.miniproject.skybook.model.dto.response.RegisterResponseDTO;
import com.miniproject.skybook.services.AuthServices;
import com.miniproject.skybook.services.UserCustomerServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(ConstantAPIPath.API+ConstantAPIPath.AUTH+ConstantAPIPath.USER)
public class UserController {
    private final AuthServices authServices;
    private final UserCustomerServices userCustomerServices;

    @RequestMapping(ConstantAPIPath.CUSTOMER+ConstantAPIPath.SIGNUP)
    public ResponseEntity<?> registerCustomer(@RequestBody CustomerUserAuthRequestDTO authRequestDTO){
        try {
            RegisterResponseDTO registerResponse = userCustomerServices.registerCustomer(authRequestDTO);
            CommonResponse<RegisterResponseDTO> response = CommonResponse.<RegisterResponseDTO>builder()
                    .message(Messages.CREATED)
                    .status(HttpStatus.CREATED.value())
                    .data(registerResponse)
                    .build();
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(response);
        }catch (Exception e){
            CommonResponse<RegisterResponseDTO> response = new CommonResponse<>();
            response.setData(null);
            response.setMessage("FAILED");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
    }
    @PostMapping(ConstantAPIPath.ADMIN+ConstantAPIPath.SIGNUP)
    public ResponseEntity<?> registerAppUser(@RequestBody AuthRequestDTO authRequestDTO){
        try {
            AuthRegistResponseDTO registerResponse = authServices.registerAdmin(authRequestDTO);
            CommonResponse<AuthRegistResponseDTO> response = CommonResponse.<AuthRegistResponseDTO>builder()
                    .message(Messages.CREATED)
                    .status(HttpStatus.CREATED.value())
                    .data(registerResponse)
                    .build();
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(response);
        }catch (Exception e){
            CommonResponse<AuthRegistResponseDTO> response = new CommonResponse<>();
            response.setData(null);
            response.setMessage("FAILED");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
    }

    @RequestMapping(ConstantAPIPath.SIGNIN)
    public ResponseEntity<?> loginAppUSER(@RequestBody AuthRequestDTO authRequestDTO){
        try {
            AuthLoginResponseDTO loginAppUser = authServices.login(authRequestDTO);
            CommonResponse<?> response = CommonResponse.<AuthLoginResponseDTO>builder()
                    .message(Messages.CREATED)
                    .status(HttpStatus.OK.value())
                    .data(loginAppUser)
                    .build();
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(response);
        }catch (Exception e){
            CommonResponse<AuthLoginResponseDTO> response = new CommonResponse<>();
            response.setData(null);
            response.setMessage("FAILED");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
    }
}