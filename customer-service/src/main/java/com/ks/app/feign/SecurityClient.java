package com.ks.app.feign;

import com.ks.app.model.AuthRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="SECURITY-SERVICE", path = "/sec")
public interface SecurityClient {

    @PostMapping("/generateToken")
    String authenticateAndGetToken(@RequestBody AuthRequest authRequest);
}
