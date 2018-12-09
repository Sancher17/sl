package com.cafe.dtoconverters.storage;

import com.cafe.api.dtoconverters.IStorageConverter;
import com.cafe.api.services.IGoodsService;
import com.cafe.dto.storage.StorageDto;
import com.cafe.model.Goods;
import com.cafe.model.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StorageConverter implements IStorageConverter {

    @Autowired
    IGoodsService goodsService;

    @Override
    public Storage toModel(StorageDto dto) {
        Storage storage = new Storage();
        storage.setQuantity(dto.getQuantity());
        Goods goods = goodsService.getById(dto.getGoodsId());
        storage.setGoods(goods);
        return storage;
    }

    @Override
    public StorageDto toDto(Storage entity) {
        return null;
    }

    @Override
    public Storage updateEntity(Storage entity, StorageDto dto) {
        return null;
    }
}
