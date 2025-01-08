package dev.kkoncki.cloth.product.color.service;

import dev.kkoncki.cloth.product.color.Color;
import dev.kkoncki.cloth.product.color.forms.CreateColorForm;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

@Validated
public interface ColorService {
    Color save(@Valid CreateColorForm form);
}
