package com.al.almall.cache;

import com.al.almall.entity.VO.DashboardVO;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class MyCache {
    HashMap<String, DashboardVO> map = new HashMap<>();

    public DashboardVO get(String key) {
        return map.get(key);
    }

    public boolean hasKey(String key) {
       return map.containsKey(key);
    }

    public void set(String key, DashboardVO dashboardVO) {
        map.put(key, dashboardVO);
    }

    public void remove(String key) {
        map.remove(key);
    }
}
