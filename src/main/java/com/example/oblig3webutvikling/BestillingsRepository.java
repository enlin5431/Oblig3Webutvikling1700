package com.example.oblig3webutvikling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // Repository-annotasjonen for å indikere at dette er et repository-komponent
public class BestillingsRepository {

    @Autowired // Autowired-annotasjonen for avhengighetsinjeksjon
    private JdbcTemplate db; // Instans av JdbcTemplate for å utføre databaseoperasjoner

    // Metode for å lagre en kunde i databasen
    public void lagreKunde(Kunde innKunde){
        String sql = "INSERT INTO Kunde (antall, film, fornavn, etternavn, telefonNr, epost) VALUES(?,?,?,?,?,?)";
        // Utfør SQL-oppdatering med data fra innKunde-objektet
        db.update(sql, innKunde.getAntall(), innKunde.getFilm(), innKunde.getFornavn(),
                innKunde.getEtternavn(), innKunde.getTelefonnr(), innKunde.getEpost());
    }
    // Metode for å hente alle kunder fra databasen
    public List<Kunde> hentAlleFilmer(){
        String sql = "SELECT * FROM Kunde ORDER BY etternavn";
        // Utfør SQL-spørring og map resultatet til en liste av Kunde-objekter
        List<Kunde> alleKunde = db.query(sql, new BeanPropertyRowMapper(Kunde.class));
        return alleKunde; // Returnerer listen over alle kunder
    }
    // Metode for å slette alle kunder fra databasen
    public void slettAlleKunde(){
        String sql = "DELETE FROM Kunde"; // SQL-spørring for å slette alle kunder
        db.update(sql); // Utfør SQL-oppdatering for å slette alle kunder
    }
}
