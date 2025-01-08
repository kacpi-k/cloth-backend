package dev.kkoncki.cloth.product;

import dev.kkoncki.cloth.product.color.Color;
import dev.kkoncki.cloth.product.color.forms.CreateColorForm;
import dev.kkoncki.cloth.product.color.service.ColorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/color")
public class ColorController {
    private final ColorService colorService;

    public ColorController(ColorService colorService) {
        this.colorService = colorService;
    }

    @PostMapping("/save")
    public Color save(@RequestBody CreateColorForm form) {
        return colorService.save(form);
    }
}
