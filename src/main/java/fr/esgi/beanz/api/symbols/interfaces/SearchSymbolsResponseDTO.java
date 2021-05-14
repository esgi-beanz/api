package fr.esgi.beanz.api.symbols.interfaces;

import java.util.List;

import lombok.Data;

@Data
public class SearchSymbolsResponseDTO {
    Integer count;

    List<SearchSymbolsResponseDTOResult> result;
}
