package com.cafe.dtoconverters.goods;

import com.cafe.api.dtoconverters.IGoodsConverter;
import com.cafe.api.services.ICategoryService;
import com.cafe.api.services.INameGoodsService;
import com.cafe.dto.goods.GoodsDto;
import com.cafe.model.Category;
import com.cafe.model.Goods;
import com.cafe.model.NameGoods;
import com.cafe.model.enums.GoodsSize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GoodsConverter implements IGoodsConverter {

    @Autowired
    private INameGoodsService nameGoodsService;
    @Autowired
    private ICategoryService categoryService;

    @Override
    public Goods toModel(GoodsDto dto) {
        Goods goods = new Goods();
        goods.setId(dto.getId());
        NameGoods nameGoods = nameGoodsService
                .getByName(dto.getNameGoodsDto());
        goods.setNameGoods(nameGoods);
        Category category = categoryService
                .getByName(dto.getCategory());
        goods.setCategory(category);
        goods.setSizeGoods( GoodsSize.valueOf(dto.getGoodsSize()));
        goods.setPurchasePrice(dto.getPurchasePrice());
        goods.setSellPrice(dto.getSellPrice());
        goods.setVolume(dto.getVolume());
        goods.setWeight(dto.getWeight());
        return goods;
    }

    @Override
    public GoodsDto toDto(Goods entity) {
        return null;
    }

    @Override
    public Goods updateEntity(Goods entity, GoodsDto dto) {
        entity = toModel(dto);
        return entity;
    }

    // TODO: 09.12.2018  - удалить если не понадобиться
    private List<GoodsDto> listGoodsToDto(List<Goods> goodsList){
        return goodsList.stream()
                .map(GoodsDto::new)
                .collect(Collectors.toList());
    }
}
