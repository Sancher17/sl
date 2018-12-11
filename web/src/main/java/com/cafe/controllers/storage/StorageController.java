package com.cafe.controllers.storage;

import com.cafe.api.services.IGoodsService;
import com.cafe.api.services.IStorageService;
import com.cafe.dto.storage.StorageDto;
import com.cafe.model.Goods;
import com.cafe.model.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/storage")
public class StorageController {

    @Autowired
    private IStorageService storageService;

    @Autowired
    private IGoodsService goodsService;

    @GetMapping
    public List<StorageDto> getAll() {
        return storageService.getAll()
                .stream().map(StorageDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public StorageDto getById(@PathVariable("id") Long id) {
        return new StorageDto(storageService.getById(id));
    }

    @PutMapping
    public void create(@RequestBody StorageDto dataDto) {
        Storage storage = new Storage();
        storage = dataDto.toModel();
        Goods goods = goodsService.getByName(dataDto.getGoodsName());
        storage.setGoods(goods);
        storageService.add(storage);
    }

    @PostMapping(value = "/")
    public void update(@RequestBody StorageDto dataDto) {
        storageService.update(dataDto.toModel());
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") Long id) {
        storageService.delete(id);
    }
}