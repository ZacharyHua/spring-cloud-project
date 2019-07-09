package com.study.security.admin.rest;

import com.study.security.admin.biz.UserBiz;
import com.study.security.admin.rpc.service.PermissionService;
import com.study.security.admin.biz.MenuBiz;
import com.study.security.admin.entity.Menu;
import com.study.security.admin.entity.User;
import com.study.security.admin.vo.FrontUser;
import com.study.security.admin.vo.MenuTree;
import com.study.security.auth.client.annotation.IgnoreClientToken;
import com.study.security.auth.client.annotation.IgnoreUserToken;
import com.study.security.common.rest.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ${DESCRIPTION}
 *
 * @author andy
 * @create 2019-05-08 11:51
 */
@RestController
@RequestMapping("user")
public class UserController extends BaseController<UserBiz,User> {
    @Autowired
    private PermissionService permissionService;

    @Autowired
    private MenuBiz menuBiz;

    @RequestMapping(value = "/front/info", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getUserInfo(String token) throws Exception {
        FrontUser userInfo = permissionService.getUserInfo(token);
        if(userInfo==null) {
            return ResponseEntity.status(401).body(false);
        } else {
            return ResponseEntity.ok(userInfo);
        }
    }

    @RequestMapping(value = "/front/menus", method = RequestMethod.GET)
    public @ResponseBody
    List<MenuTree> getMenusByUsername(String token) throws Exception {
        return permissionService.getMenusByUsername(token);
    }

    @RequestMapping(value = "/front/menu/all", method = RequestMethod.GET)
    public @ResponseBody
    List<Menu> getAllMenus() throws Exception {
        return menuBiz.selectListAll();
    }

    @GetMapping("/names")
    @IgnoreUserToken
    @IgnoreClientToken
    public Map<Integer,String> getUserNames(){
        return this.all().stream().collect(Collectors.toMap(item->item.getId(),item->item.getName()));
    }
}
