package com.cafe.controllers.goods;

import com.cafe.api.dtoconverters.IGoodsConverter;
import com.cafe.api.services.ICategoryService;
import com.cafe.api.services.IGoodsService;
import com.cafe.api.services.INameGoodsService;
import com.cafe.dto.goods.GoodsDto;
import com.cafe.model.Category;
import com.cafe.model.Goods;
import com.cafe.model.NameGoods;
import com.cafe.model.enums.GoodsSize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private IGoodsConverter goodsConverter;

    @GetMapping
    public List<GoodsDto> getAll() {
        return goodsService.getAll()
                .stream().map(GoodsDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public GoodsDto getById(@PathVariable("id") Long id) {
        return new GoodsDto(goodsService.getById(id));
    }

    @PutMapping
    public void create(@RequestBody GoodsDto goodsDto) {
        goodsService.add(goodsConverter.toModel(goodsDto));
    }

    @PostMapping(value = "/{id}")
    public void update(@RequestBody GoodsDto dataDto, @PathVariable("id") Long id) {
        goodsService.update(goodsConverter.updateEntity(goodsService.getById(id), dataDto));
    }
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") Long id) {
        goodsService.delete(id);
    }

}
