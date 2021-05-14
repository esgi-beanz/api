package fr.esgi.beanz.api.symbols;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.esgi.beanz.api.symbols.interfaces.QuoteResponseDTO;
import fr.esgi.beanz.api.symbols.interfaces.SearchSymbolsResponseDTO;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/symbols")
@RequiredArgsConstructor
public class SymbolsController {
    @Autowired
    private final InstrumentsService instrumentsService;

    @GetMapping("/search")
    public SearchSymbolsResponseDTO searchSymbols(@RequestParam(value = "q", required = true) String search)
            throws IOException {
        return instrumentsService.searchSymbols(search);
    }

    @GetMapping()
    public List<QuoteResponseDTO> getSymbols(@RequestParam(value = "symbol", required = true) List<String> symbols)
            throws IOException {
        return symbols.stream().map((symbol) -> {
            try {
                return instrumentsService.getSymbol(symbol);
            } catch (IOException e) {
                return null;
            }
        }).collect(Collectors.toList());
    }
}
