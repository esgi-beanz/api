package fr.esgi.beanz.api.symbols.interfaces;

import lombok.Data;

@Data
public class SearchSymbolsResponseDTOResult {
    String description;

    String displaySymbol;

    String symbol;

    String type;
}
