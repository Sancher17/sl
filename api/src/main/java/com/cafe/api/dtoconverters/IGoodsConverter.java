package com.cafe.api.dtoconverters;

import com.cafe.dto.goods.GoodsDto;
import com.cafe.model.Goods;
import org.springframework.stereotype.Component;

@Component
public interface IGoodsConverter extends GenericConverter<Goods, GoodsDto> {
}
