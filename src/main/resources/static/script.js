// Funksjon for å kjøpe kinobillettene
function kjopBillett() {
    const valgtFilm = $('#movieSelect').val();
    const antall = $('#quantity').val();
    const fornavn = $('#firstName').val();
    const etternavn = $('#lastName').val();
    const telefonNr = $('#phoneNumber').val();
    console.log("Telefonnummer hentet:", telefonNr);
    const epost = $('#email').val();

    // Nullstill feilmeldinger før validering
    $('.error').text('');

    // Valider inndataen
    let isValid = true;

    if (valgtFilm === 'Velg film her') {
        $('#movieSelectError').text('Du må velge en film.');
        isValid = false;
    }

    if (antall <= 0) {
        $('#quantityError').text('Du må oppgi et gyldig antall billetter.');
        isValid = false;
    }

    if (fornavn.trim() === '') {
        $('#firstNameError').text('Du må oppgi fornavn.');
        isValid = false;
    }

    if (etternavn.trim() === '') {
        $('#lastNameError').text('Du må oppgi etternavn.');
        isValid = false;
    }

    const telefonnrRegex = /^[0-9]{8}$/;
    if (!telefonnrRegex.test(telefonNr)) {
        $('#phoneNumberError').text('Du må oppgi gyldig telefonnummer.');
        isValid = false;
    }

    const epostRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!epostRegex.test(epost)) {
        $('#emailError').text('Du må oppgi gyldig e-postadresse.');
        isValid = false;
    }

    if (!isValid) {
        return;
    }

    // Opprett en billettobjekt
    const billett = {
        film: valgtFilm,
        antall: antall,
        fornavn: fornavn,
        etternavn: etternavn,
        telefonNr: telefonNr,
        epost: epost
    };

    // Send billettobjektet til serveren
    lagreBillett(billett);
}

// Send en HTTP POST-forespørsel til serveren for å lagre billettbestillingen
function lagreBillett(billett) {
    $.ajax({
        type: 'POST',
        url: '/lagre',
        data: JSON.stringify(billett),
        contentType: 'application/json',
        success: function(response) {
            // Billettbestillingen ble lagret vellykket
            visMelding('Billettbestillingen ble lagret.');
            // Oppdater visningen av billetter
            visBilletter();
        },
        error: function(xhr, status, error) {
            // Noe gikk galt ved lagring av billettbestillingen
            console.error('Feil ved lagring av billettbestilling:', error);
            visMelding('Feil: Billettbestillingen kunne ikke lagres.');
        }
    });
}

// Vis en melding til brukeren
function visMelding(melding) {
    $('#meldingsområde').text(melding);
    // Slett meldingen etter noen sekunder
    setTimeout(function() {
        $('#meldingsområde').text('');
    }, 5000);
}

// En funksjon for å slette alle billettene
function slettAlleBilletter() {
    $.ajax({
        type: 'DELETE',
        url: '/slettAlle',
        success: function(response) {
            // Alle billetter ble slettet vellykket
            visMelding('Alle billetter ble slettet.');
            // Oppdater visningen av billetter
            visBilletter();
        },
        error: function(xhr, status, error) {
            // Noe gikk galt ved sletting av billetter
            console.error('Feil ved sletting av billetter:', error);
            visMelding('Feil: Kunne ikke slette alle billetter.');
        }
    });
}

// En funksjon for å hente og vise alle billettene
function visBilletter() {
    console.log("Kaller visBilletter()...");
    // Sende en GET-forespørsel til serveren for å hente alle billettene
    $.get('/hentAlle', function(billetter) {
        console.log("Billetter mottatt:", billetter);
        // Vis billettene i grensesnittet
        const billettListe = $('#billettListe');
        billettListe.empty(); // Tøm listen først
        billetter.forEach(function(billett) {
            const li = $('<li>').text(`Film: ${billett.film}, Antall: ${billett.antall}, Navn: ${billett.fornavn} ${billett.etternavn}, Telefon: ${billett.telefonNr}, Epost: ${billett.epost}`);
            billettListe.append(li);
        });
    }).fail(function(xhr, status, error) {
        console.error('Feil ved henting av billetter:', error);
        visMelding('Feil: Kunne ikke hente billetter.');
    });
}

let billetter = [];
$(document).ready(function() {
    // Lyttere for knapper
    $('#kjopBillettBtn').click(kjopBillett);
    $('#slettAlleBilletterBtn').click(slettAlleBilletter);

    visBilletter();
});
