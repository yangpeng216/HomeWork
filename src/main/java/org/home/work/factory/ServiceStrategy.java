package org.home.work.factory;

import org.home.work.service.impl.IdentityServiceImpl;
import org.home.work.service.impl.RoleServiceImpl;
import org.home.work.service.impl.UserServiceImpl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServiceStrategy {

    static Map<String,Object> serviceMap = new ConcurrentHashMap<>();

    static {
        serviceMap.put("userService", new UserServiceImpl());
        serviceMap.put("roleService", new RoleServiceImpl());
        serviceMap.put("identityService", new IdentityServiceImpl());
    }

    public static Object getService(String serviceName) {
        return serviceMap.get(serviceName);
    }
}
