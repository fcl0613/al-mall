package com.al.almall.controller;


import com.al.almall.entity.DTO.AddStoreDTO;
import com.al.almall.entity.DTO.ChangeStatusDTO;
import com.al.almall.entity.DTO.RemoveStoreDTO;
import com.al.almall.entity.DTO.StoreListDTO;
import com.al.almall.entity.MallStore;
import com.al.almall.entity.Result;
import com.al.almall.serive.MallStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author al
 * @since 2023-02-28
 */
@RestController
@RequestMapping("/store")
public class MallStoreController {
    @Autowired
    private MallStoreService mallStoreService;

    @GetMapping("/list")
    public Result getStoreList(StoreListDTO storeListDTO) {
        return mallStoreService.storeList(storeListDTO);
    }

    @PostMapping("/add")
    public Result addStore(@RequestBody AddStoreDTO addStoreDTO) {
        return mallStoreService.addStore(addStoreDTO);
    }

    @PostMapping("/remove")
    public Result remove(@RequestBody RemoveStoreDTO removeStoreDTO) {
        return mallStoreService.removeStore(removeStoreDTO);
    }

    @PostMapping("/update")
    public Result updateStore(@RequestBody MallStore mallStore) {
        return mallStoreService.updateStore(mallStore);
    }

    @GetMapping("/get/{id}")
    public Result getStoreDetail(@PathVariable Integer id) {
        return mallStoreService.getStoreDetail(id);
    }

    @PostMapping("/changeStatus")
    public Result changeStatus(@RequestBody ChangeStatusDTO changeStatusDTO) {
        return mallStoreService.changeStatus(changeStatusDTO);
    }

    @GetMapping("/searchAll")
    public Result searchAll(String keyword) {
        return mallStoreService.searchAll(keyword);
    }

}

