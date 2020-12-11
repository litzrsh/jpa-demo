package com.example.demo.book.entity;

import com.example.demo.store.model.StoreBooks;
import com.example.demo.store.model.StoreDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

    private Long id;

    @NotEmpty
    @Size(min = 2, max = 100)
    private String title;

    private Integer stock;

    private boolean open = false;

    private List<String> tags = new ArrayList<>();

    private List<StoreDTO> stores = new ArrayList<>();

    public static BookDTO build(Book entity) {
        BookDTO dto = new BookDTO();
        BeanUtils.copyProperties(entity, dto, "tags", "storeBooks");

        convertTags(dto, entity.getTags());

        if (!CollectionUtils.isEmpty(entity.getStoreBooks())) {
            dto.setStores(
                    entity.getStoreBooks().stream()
                            .map(StoreDTO::build)
                            .collect(Collectors.toList()));
        }

        return dto;
    }

    public static BookDTO build(StoreBooks entity) {
        BookDTO dto = new BookDTO();
        BeanUtils.copyProperties(entity.getBook(), dto, "tags", "storeBooks");

        convertTags(dto, entity.getBook().getTags());
        dto.setStock(entity.getStock());

        return dto;
    }

    protected static void convertTags(BookDTO dto, List<BookTag> tags) {
        if (!CollectionUtils.isEmpty(tags)) {
            dto.setTags(
                    tags.stream()
                            .map(BookTag::getTag)
                            .sorted(String::compareToIgnoreCase)
                            .collect(Collectors.toList()));
        }
    }
}
