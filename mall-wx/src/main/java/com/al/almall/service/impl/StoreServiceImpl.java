package com.al.almall.service.impl;

import com.al.almall.dao.CartDao;
import com.al.almall.dao.CategoryDao;
import com.al.almall.dao.GoodsDao;
import com.al.almall.dao.StoreDao;
import com.al.almall.entity.DO.CartBaseInfoDO;
import com.al.almall.entity.DO.CategoryListDO;
import com.al.almall.entity.DO.GetStoreListDO;
import com.al.almall.entity.DO.GoodsListDO;
import com.al.almall.entity.DTO.GetStoreListDTO;
import com.al.almall.entity.DTO.SearchStoreListDTO;
import com.al.almall.entity.MallCart;
import com.al.almall.entity.Result;
import com.al.almall.entity.VO.GetStoreListVO;
import com.al.almall.entity.VO.MenuListGoodsVO;
import com.al.almall.entity.VO.MenuListVO;
import com.al.almall.enums.StoreStatusEnum;
import com.al.almall.mapper.MallCartMapper;
import com.al.almall.service.StoreService;
import com.al.almall.utils.GeographyUtil;
import com.al.almall.utils.JwtUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class StoreServiceImpl implements StoreService {

    private final String DISTANCE = "2";

    @Autowired
    private StoreDao storeDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private MallCartMapper mallCartMapper;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    private CartDao cartDao;

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

    @Override
    public Result getMenuList(Integer id, HttpServletRequest request) {
        List<CategoryListDO> categoryList = categoryDao.getCategoryList(id);
        List<GoodsListDO> goodsList = goodsDao.getGoodsList(id);
        // 我们这里还需要购物车的信息
        // 查询条件 storeid userid 找到goodsid和goodscount
        String authorization = request.getHeader("Authorization");
        Integer userId = jwtUtil.getUserId(authorization);
        List<MallCart> mallCarts = mallCartMapper.selectList(new LambdaQueryWrapper<MallCart>()
                .eq(MallCart::getUserId, userId)
                .eq(MallCart::getStoreId, id));
        ArrayList<MenuListVO> menuList = new ArrayList<>();
        for (CategoryListDO categoryListDO : categoryList) {
            MenuListVO menuListVO = new MenuListVO();
            menuListVO.setId(categoryListDO.getId());
            menuListVO.setCategoryName(categoryListDO.getCategoryName());
            menuListVO.setCategoryIcon(categoryListDO.getCategoryIcon());
            ArrayList<MenuListGoodsVO> menuListGoodsVOS = new ArrayList<>();
            for (GoodsListDO goodsListDO : goodsList) {
                if (goodsListDO.getCategoryId() == categoryListDO.getId()) {
                    MenuListGoodsVO menuListGoodsVO = new MenuListGoodsVO();
                    menuListGoodsVO.setId(goodsListDO.getId());
                    menuListGoodsVO.setGoodsDescription(goodsListDO.getDescription());
                    menuListGoodsVO.setGoodsName(goodsListDO.getGoodsName());
                    menuListGoodsVO.setGoodsPic(goodsListDO.getGoodsPic());
                    menuListGoodsVO.setGoodsPrice(goodsListDO.getGoodsPrice());
                    menuListGoodsVO.setGoodsStock(goodsListDO.getStock());
                    for (MallCart mallCart : mallCarts) {
                        if (goodsListDO.getId() == mallCart.getGoodsId()) {
                            menuListGoodsVO.setGoodsCount(mallCart.getGoodsCount());
                            break;
                        }
                    }
                    menuListGoodsVOS.add(menuListGoodsVO);
                }
            }
            menuListVO.setGoodsList(menuListGoodsVOS);
            menuList.add(menuListVO);
        }
        return Result.success(menuList);
    }

    @Override
    public Result getCartBaseInfo(Integer id, HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        Integer userId = jwtUtil.getUserId(authorization);
        CartBaseInfoDO cartBaseInfo = cartDao.getCartBaseInfo(userId, id);
        return Result.success(cartBaseInfo);
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
