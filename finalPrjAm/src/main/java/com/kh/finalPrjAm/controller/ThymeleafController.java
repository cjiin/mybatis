package com.kh.finalPrjAm.controller;

import com.kh.finalPrjAm.dto.ItemDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/thymeleaf")
public class ThymeleafController {
    @GetMapping("/item")
    public String thymeleafITem(Model model) {
        ItemDto itemDto = new ItemDto();
        itemDto.setItemNm("에어컨");
        itemDto.setItemDetail("에어컨입니다..");
        itemDto.setPrice(200);
        itemDto.setRegTime(LocalDateTime.now());
        model.addAttribute("itemDto",itemDto);
        return "thymeleaf/thymeleafItem";
    }
    @GetMapping("item-list")
    public String thymeleafItemList(Model model) {
        List<ItemDto> itemDtoList = new ArrayList<>();
        for(int i = 1; i < =10; i++) {
            ItemDto itemDto = new ItemDto();
            itemDto.setItemNm("테스트 상품 " + i);
            itemDto.setPrice(1000 * i);
            itemDto.setRegTime(LocalDateTime.now());
            itemDtoList.add(itemDto);
        }
        model.addAttribute("itemDtoList", itemDtoList);
        return "thymeleaf/thymeleafItemList";
        }
    }

