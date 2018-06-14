package bean;

import eu.toop.demoui.bean.Identity;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IdentityTest {
  @Test
  public void testBasic () {

    final String firstName = "Maximillian";
    final String familyName = "Stern";
    final String birthPlace = "Test birth place";
    final LocalDate birthDate = LocalDate.of (2018, 05, 10);
    final String identifier = "SV/GF/12345";
    final String nationality = "SV";

    final Identity identity = new Identity ();
    identity.setFirstName (firstName);
    identity.setFamilyName (familyName);
    identity.setBirthPlace (birthPlace);
    identity.setBirthDate (birthDate);
    identity.setIdentifier (identifier);
    identity.setNationality (nationality);

    assertEquals (firstName, identity.getFirstName ());
    assertEquals (familyName, identity.getFamilyName ());
    assertEquals (birthPlace, identity.getBirthPlace ());
    assertEquals (birthDate, identity.getBirthDate ());
    assertEquals (identifier, identity.getIdentifier ());
    assertEquals (nationality, identity.getNationality ());
  }
}