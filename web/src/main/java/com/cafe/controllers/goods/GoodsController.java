package com.cafe.controllers.goods;

import com.cafe.api.services.ICategoryService;
import com.cafe.api.services.IGoodsService;
import com.cafe.api.services.INameGoodsService;
import com.cafe.dto.goods.GoodsDto;
import com.cafe.model.Category;
import com.cafe.model.Goods;
import com.cafe.model.NameGoods;
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
    private ICategoryService categoryService;
    @Autowired
    private INameGoodsService nameGoodsService;

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


    @PutMapping(value = "/test")
    public void create() {
        System.out.println();
    }


    @PutMapping(value = "/")
    public void create(@RequestBody GoodsDto goodsDto) {
        Goods goods = new Goods();
        NameGoods nameGoods = nameGoodsService.getByName(goodsDto.getNameGoodsDto());
        goods.setNameGoods(nameGoods);
        Category category = categoryService.getByName(goodsDto.getCategory());
        goods.setCategory(category);
        goods = goodsDto.toModel();
        goodsService.add(goods);
    }

    @PostMapping(value = "/")
    public void update(@RequestBody GoodsDto goodsDto) {
        goodsService.update(goodsDto.toModel());
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") Long id) {
        goodsService.delete(id);
    }
}
