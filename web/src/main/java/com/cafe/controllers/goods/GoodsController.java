package com.cafe.controllers.goods;

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
    private INameGoodsService nameGoodsService;

    @Autowired
    private ICategoryService categoryService;

    @GetMapping(value = "/all")
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
        goodsService.add(dtoToModel(goodsDto));
    }

    @PostMapping(value = "/{id}")
    public void update(@RequestBody GoodsDto dataDto, @PathVariable("id") Long id) {
        Goods goods = goodsService.getById(id);
        goods = dtoToModel(dataDto);
        goodsService.update(goods);
    }
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") Long id) {
        goodsService.delete(id);
    }

    private Goods dtoToModel(GoodsDto dataDto) {
        Goods goods = new Goods();
        goods.setId(dataDto.getId());
        NameGoods nameGoods = nameGoodsService
                .getByName(dataDto.getNameGoodsDto());
        goods.setNameGoods(nameGoods);
        Category category = categoryService
                .getByName(dataDto.getCategory());
        goods.setCategory(category);
        goods.setSizeGoods( GoodsSize.valueOf(dataDto.getGoodsSize()));
        goods.setPurchasePrice(dataDto.getPurchasePrice());
        goods.setSellPrice(dataDto.getSellPrice());
        goods.setVolume(dataDto.getVolume());
        goods.setWeight(dataDto.getWeight());
        return goods;
    }

    private List<GoodsDto> listGoodsToDto(List<Goods> goodsList){
        return goodsList.stream()
                .map(GoodsDto::new)
                .collect(Collectors.toList());
    }
}
