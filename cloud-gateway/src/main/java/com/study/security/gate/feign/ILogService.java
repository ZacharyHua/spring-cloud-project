package com.study.security.gate.feign;

import com.study.security.api.vo.log.LogInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * ${DESCRIPTION}
 *
 * @author andy
 * @create 2017-07-01 15:16
 */
@FeignClient("cloud-admin")
public interface ILogService {
  @RequestMapping(value="/api/log/save",method = RequestMethod.POST)
  void saveLog(LogInfo info);
}
