package com.study.security.auth.feign;

import com.study.security.auth.configuration.FeignConfiguration;
import com.study.security.auth.util.user.JwtAuthenticationRequest;
import com.study.security.api.vo.user.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * ${DESCRIPTION}
 *
 * @author andy
 * @create 2019-05-21 8:11
 */
@FeignClient(value = "cloud-admin",configuration = FeignConfiguration.class)
public interface IUserService {
  @RequestMapping(value = "/api/user/validate", method = RequestMethod.POST)
  public UserInfo validate(@RequestBody JwtAuthenticationRequest authenticationRequest);
}
