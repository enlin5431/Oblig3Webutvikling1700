package com.example.oblig3webutvikling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BestillingsRepository {

    @Autowired
    private JdbcTemplate db;

    public void lagreKunde(Kunde innKunde){
        String sql = "INSERT INTO Kunde (antall, film, fornavn, etternavn, telefonNr, epost) VALUES(?,?,?,?,?,?)";
        db.update(sql, innKunde.getAntall(), innKunde.getFilm(), innKunde.getFornavn(),
                innKunde.getEtternavn(), innKunde.getTelefonnr(), innKunde.getEpost());
    }
    public List<Kunde> hentAlleFilmer(){
        String sql = "SELECT * FROM Kunde ORDER BY etternavn";
        List<Kunde> alleKunde = db.query(sql, new BeanPropertyRowMapper(Kunde.class));
        return alleKunde;
    }
    public void slettAlleKunde(){
        String sql = "DELETE FROM Kunde";
        db.update(sql);
    }
}
