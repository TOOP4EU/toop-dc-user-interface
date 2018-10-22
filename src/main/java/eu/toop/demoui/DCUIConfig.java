package eu.toop.demoui;

import com.typesafe.config.*;

import java.util.*;
import java.util.regex.Pattern;

public class DCUIConfig {

  private static ResourceBundle rb = ResourceBundle.getBundle("dcui");
  private final List<Dataset> datasets = new ArrayList<> ();

  public DCUIConfig() {

    ConfigParseOptions opt = ConfigParseOptions.defaults();
    opt.setSyntax(ConfigSyntax.CONF);

    Config conf = ConfigFactory.parseResources("dataset.conf").resolve();

    for (ConfigObject _dataset : conf.getObjectList("Datasets")) {
      Config dataset = _dataset.toConfig();

      datasets.add(new Dataset (dataset));
    }
  }

  public static String getEidModuleURL () {

    return rb.getString("toop.eidmodule.url");
  }

  public static String getDestinationCountryCode () {

    return rb.getString ("destination.country.code");
  }

  public List<Dataset> getDatasets () {

    return datasets;
  }

  public List<Dataset> getDatasetsByIdentifier(String naturalPersonIdentifier, String legalPersonIdentifier) {

    List<Dataset> _datasets = new ArrayList<> ();

    if (naturalPersonIdentifier == null && legalPersonIdentifier == null) {
      return _datasets;
    }

    for (Dataset dataset : datasets) {

      boolean npMatch = true;
      boolean leMatch = true;

      if (naturalPersonIdentifier != null &&
          !dataset.getNaturalPersonIdentifier ().equals (stripCodesFromIdentifier(naturalPersonIdentifier))) {
        npMatch = false;
      }

      if (legalPersonIdentifier != null &&
          !dataset.getLegalPersonIdentifier ().equals (stripCodesFromIdentifier(legalPersonIdentifier))) {
        leMatch = false;
      }

      if (npMatch && leMatch) {
        _datasets.add (dataset);
      }
    }

    return _datasets;
  }

  public List<Dataset> getDatasetsByNaturalPersonIdentifier(String naturalPersonIdentifier) {

    List<Dataset> _datasets = new ArrayList<> ();
    for (Dataset dataset : datasets) {

      if (dataset.getNaturalPersonIdentifier ().equals (stripCodesFromIdentifier(naturalPersonIdentifier))) {
        _datasets.add (dataset);
      }
    }

    return _datasets;
  }

  public List<Dataset> getDatasetsByLegalPersonIdentifier(String legalPersonIdentifier) {

    List<Dataset> _datasets = new ArrayList<> ();
    for (Dataset dataset : datasets) {

      if (dataset.getLegalPersonIdentifier ().equals (stripCodesFromIdentifier(legalPersonIdentifier))) {
        _datasets.add (dataset);
      }
    }

    return _datasets;
  }

  private boolean isValidIdentifier(String identifier) {
    return Pattern.matches("([A-Z][A-Z]\\/){2}.{1,}", identifier);
  }

  private String stripCodesFromIdentifier(String identifier) {

    if (!isValidIdentifier(identifier)) {
      return null;
    }

    return identifier.substring (6);
  }

  public class Dataset {

    private final String naturalPersonIdentifier;
    private final String legalPersonIdentifier;
    private final Map<String, String> concepts = new HashMap<>();

    public Dataset(Config conf) {

      naturalPersonIdentifier = conf.getString ("NaturalPerson.identifier");
      legalPersonIdentifier = conf.getString ("LegalPerson.identifier");

      for(ConfigObject _concept : conf.getObjectList ("Concepts")) {
        Config concept = _concept.toConfig();

        String conceptName = concept.getString("name");
        String conceptValue = concept.getString("value");

        concepts.put(conceptName, conceptValue);
      }
    }

    public String getNaturalPersonIdentifier () {

      return naturalPersonIdentifier;
    }

    public String getLegalPersonIdentifier () {

      return legalPersonIdentifier;
    }

    public Map<String, String> getConcepts () {

      return concepts;
    }

    public String getConceptValue(String conceptName) {

      return concepts.getOrDefault (conceptName, null);
    }
  }
}
