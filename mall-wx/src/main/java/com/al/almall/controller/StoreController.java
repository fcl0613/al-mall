package com.al.almall.controller;

import com.al.almall.entity.DTO.GetStoreListDTO;
import com.al.almall.entity.DTO.SearchStoreListDTO;
import com.al.almall.entity.Result;
import com.al.almall.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/store")
public class StoreController {
    @Autowired
    private StoreService storeService;

    @PostMapping("/list")
    public Result getStoreList(@RequestBody GetStoreListDTO getStoreListDTO) {
        return storeService.getStoreList(getStoreListDTO);
    }

    @PostMapping("/search")
    public Result searchStoreList(@RequestBody SearchStoreListDTO searchStoreListDTO) {
        return storeService.searchStoreList(searchStoreListDTO);
    }
}
