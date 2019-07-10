package com.ruichen.restful.config.shiro;

import com.ruichen.restful.repository.mybatis.entity.PermissionFilterEntity;
import com.ruichen.restful.service.IPermissionFilterService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName  IgnoreFilter
 * @Description
 * @Date  2019/7/10 15:37
 * @author  lixueyun
 */
@Component
public class FilterMapConfig {

    @Autowired
    private IPermissionFilterService permissionFilterService;

    /**
     * @methodName  ignoreFilter
     * @description 忽略的url
     * @param
     * @author  lixueyun
     * @Date  2019/7/10 15:43
     * @return  java.util.List<java.lang.String>
     */
    public List<String> ignoreFilter(){
        List<PermissionFilterEntity> permissionFilters = permissionFilterService.list();
        if (CollectionUtils.isNotEmpty(permissionFilters)) {
            return permissionFilters.stream()
                    .map(PermissionFilterEntity::getUrl)
                    .collect(Collectors.toList());
        }
        return null;
    }

}
