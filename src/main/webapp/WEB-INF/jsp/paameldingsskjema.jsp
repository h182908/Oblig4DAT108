<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="no">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="icon" href="data:," />
    <link href="main.css" rel="stylesheet" type="text/css" />
    <script src='js/validator.js' type="text/javascript" defer></script>
    <title>Påmelding</title>
  </head>
  <body>
    <h2>Påmelding</h2>    
      <form method="post">
        <fieldset>
          <label for="fornavn">Fornavn:</label>
          <input type="text" name="fornavn" id="fornavn" value=""
               placeholder="Fyll inn fornavn"
               title="Fornavn må starte med stor forbokstav og innholde kun bokstaver. Antall tegn må være mellom 2 og 20."
               required pattern="\s*\p{Lu}\p{Ll}+(\s+\p{Lu}\p{Ll}+)*\s*"
           />           
           	<span class="melding"><c:if test="${schema != null && !schema.isFirstNameValid()}">Ugyldig fornavn</c:if></span>

          <label for="etternavn">Etternavn:</label>
          <input type="text" name="etternavn" id="etternavn" value=""
               placeholder="Fyll inn etternavn"
               title="Etternavn må starte med stor forbokstav og innholde kun bokstaver. Antall tegn må være mellom 2 og 20."
              required pattern="\s*\p{Lu}\p{Ll}+\s*"
           />
           	<span class="melding"><c:if test="${schema != null && !schema.isLastNameValid()}">
           	Ugyldig etternavn</c:if></span>


          <label for="mobil">Mobil (8 siffer):</label>
          <input type="text" name="mobil" id="mobil" value=""
              placeholder="Fyll inn telefonnummer"
              title="Mobilnummer må bestå av nøyaktig 8 siffre."
              required pattern="\s*\+?\s*(\d\s*){8}\s*"
           />
           	<span class="melding"><c:if test="${schema != null && !schema.isPhoneNumberValid()}">Ugyldig mobil</c:if></span>

          <label for="passord">Passord:</label>
          <input type="password" name="passord" id="passord" value=""
              placeholder="Velg et passord"
              title="Passordet må bestå av minst 8 tegn, men bør ha minst 14 tegn."
              required pattern=".{8,}"
           />
           	<span class="melding"><c:if test="${schema != null && !schema.isPasswordValid()}">Ugyldig passord</c:if></span>

          <label for="passordRepetert">Passord repetert:</label>
          <input type="password" name="passordRepetert" id="passordRepetert" value=""
              placeholder="Gjenta passord"
              required  />
           	<span class="melding"><c:if test="${schema != null && !schema.isRepeatedPasswordValid()}">Passordene må være like.</c:if></span>           

          <span class="columnfirst">Kjønn:</span>
          <span>
            <label>
                <input type="radio" name="kjonn" value="mann" checked="checked"/> mann</label>
            <label>
                <input type="radio" name="kjonn" value="kvinne"/>kvinne</label>
          </span>
           	<span class="melding"><c:if test="${schema != null && !schema.isGenderValid()}">Du må oppgi kjonn.</c:if></span>

          <button type="submit">Meld meg på</button>
        </fieldset>
      </form>
  </body>
</html>