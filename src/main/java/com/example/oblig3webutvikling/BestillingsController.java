package com.example.oblig3webutvikling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BestillingsController {

    @Autowired
    private BestillingsRepository rep;

    private final List<Film> filmregister = new ArrayList<>();
    public BestillingsController() {
        Film film1 = new Film("James Bond");
        filmregister.add(film1);
        Film film2 = new Film("Avatar 2");
        filmregister.add(film2);
        Film film3 = new Film("Innsiden Ut");
        filmregister.add(film3);
    }

    @GetMapping("/hentFilmer")
    public List<Film> hentFilmer() {
        return filmregister;
    }

    @PostMapping("/lagre")
    public void lagreKunde(Kunde innKunde){rep.lagreKunde(innKunde);}

    @GetMapping("/hentAlle")
    public List<Kunde> hentAlle(){return rep.hentAlleFilmer();}

    @GetMapping("/slettAlle")
    public void slettAlle(){
        rep.slettAlleKunde();
    }


}
