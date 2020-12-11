package com.example.demo.store.model;

import com.example.demo.book.entity.BookDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class StoreDTO implements Serializable {

    private Long id;

    @NotEmpty
    @Size(min = 2, max = 100)
    private String name;

    private Integer stock;

    private List<BookDTO> books = new ArrayList<>();

    public static StoreDTO build(Store entity) {
        StoreDTO dto = new StoreDTO();
        BeanUtils.copyProperties(entity, dto, "storeBooks");

        if (!CollectionUtils.isEmpty(entity.getStoreBooks())) {
            dto.setBooks(
                    entity.getStoreBooks().stream()
                            .map(BookDTO::build)
                            .collect(Collectors.toList()));
        }

        return dto;
    }

    public static StoreDTO build(StoreBooks entity) {
        StoreDTO dto = new StoreDTO();
        BeanUtils.copyProperties(entity.getStore(), dto, "storeBooks");

        // Set stock
        dto.setStock(entity.getStock());

        return dto;
    }
}
