package fr.esgi.beanz.api.symbols.interfaces;

import lombok.Data;

@Data
public class QuoteResponseDTO {
    Number c;
    Number h;
    Number l;
    Number o;
    Number pc;
    Number t;
}
