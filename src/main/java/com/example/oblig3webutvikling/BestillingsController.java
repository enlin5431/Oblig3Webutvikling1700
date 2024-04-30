package com.example.oblig3webutvikling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BestillingsController {

    @Autowired
    private BestillingsRepository rep; // Instans av BestillingsRepository for databaseoperasjoner

    private final List<Film> filmregister = new ArrayList<>();// Liste for å lagre filmer

    public BestillingsController() {
        // Initialiserer filmregisteret med noen filmer
        Film film1 = new Film("James Bond");
        filmregister.add(film1);
        Film film2 = new Film("Avatar 2");
        filmregister.add(film2);
        Film film3 = new Film("Innsiden Ut");
        filmregister.add(film3);
    }
    // Metode for GET-forespørsel for å hente alle filmer
    @GetMapping("/hentFilmer")
    public List<Film> hentFilmer() {
        return filmregister;
    }
    // Metode for POST-forespørsel for å lagre en kunde
    @PostMapping("/lagre")
    public void lagreKunde(@RequestBody Kunde innKunde){rep.lagreKunde(innKunde);}
    // Metode for GET-forespørsel for å hente alle kunder
    @GetMapping("/hentAlle")
    public List<Kunde> hentAlle(){return rep.hentAlleFilmer();}
    // Metode for GET-forespørsel for å slette alle kunder
    @GetMapping("/slettAlle")
    public void slettAlle(){
        rep.slettAlleKunde();
    }


}
