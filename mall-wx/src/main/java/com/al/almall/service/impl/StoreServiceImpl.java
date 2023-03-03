package com.al.almall.service.impl;

import com.al.almall.dao.StoreDao;
import com.al.almall.entity.DO.GetStoreListDO;
import com.al.almall.entity.DTO.GetStoreListDTO;
import com.al.almall.entity.DTO.SearchStoreListDTO;
import com.al.almall.entity.Result;
import com.al.almall.entity.VO.GetStoreListVO;
import com.al.almall.enums.StoreStatusEnum;
import com.al.almall.service.StoreService;
import com.al.almall.utils.GeographyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class StoreServiceImpl implements StoreService {

    private final String DISTANCE = "2";

    @Autowired
    private StoreDao storeDao;

    @Override
    public Result getStoreList(GetStoreListDTO getStoreListDTO) {
        // 首先计算出以当前用户坐标为中心的方圆3公里内的四个经纬度坐标
        String[] neighPosition =
                GeographyUtil.findNeighPosition(getStoreListDTO.getLongitude(),
                        getStoreListDTO.getLatitude(), DISTANCE);
        Map<Integer, String> storeStatusMap = StoreStatusEnum.getStoreStatusMap();
        List<GetStoreListDO> storeListDO = storeDao.getStoreListDO(neighPosition, storeStatusMap);
        List<GetStoreListVO> getStoreListVOS =
                buildStoreListVOS(storeListDO,
                        getStoreListDTO.getLongitude(),
                        getStoreListDTO.getLatitude());
        return Result.success(getStoreListVOS);
    }

    @Override
    public Result searchStoreList(SearchStoreListDTO searchStoreListDTO) {
        Map<Integer, String> storeStatusMap = StoreStatusEnum.getStoreStatusMap();
        List<GetStoreListDO> storeListDO =
                storeDao.searchStoreListDO(searchStoreListDTO.getKeyword(), storeStatusMap);
        List<GetStoreListVO> getStoreListVOS =
                buildStoreListVOS(storeListDO,
                        searchStoreListDTO.getLongitude(),
                        searchStoreListDTO.getLatitude());
        return Result.success(getStoreListVOS);
    }

    private List<GetStoreListVO> buildStoreListVOS(List<GetStoreListDO> storeListDO, String currentLongitude,
                                                   String latitude) {
        ArrayList<GetStoreListVO> getStoreListVOS = new ArrayList<>();
        for (GetStoreListDO getStoreListDO : storeListDO) {
            GetStoreListVO getStoreListVO = new GetStoreListVO();
            getStoreListVO.setId(getStoreListDO.getId());
            getStoreListVO.setStoreName(getStoreListDO.getStoreName());
            getStoreListVO.setStoreAddress(getStoreListDO.getStoreAddress());
            getStoreListVO.setStatus(getStoreListDO.getStatus());
            getStoreListVO.setPhone(getStoreListDO.getPhone());
            getStoreListVO.setOpeningTime(getStoreListDO.getOpeningTime());
            getStoreListVO.setLongitude(getStoreListDO.getLongitude());
            getStoreListVO.setLatitude(getStoreListDO.getLatitude());
            double distance = GeographyUtil.getDistance(Double.parseDouble(currentLongitude)
                    , Double.parseDouble(latitude),
                    Double.parseDouble(getStoreListDO.getLongitude()),
                    Double.parseDouble(getStoreListDO.getLatitude()));
            getStoreListVO.setDistance(String.valueOf(distance).substring(0, 3));
            getStoreListVOS.add(getStoreListVO);
        }
        return getStoreListVOS;
    }

}
