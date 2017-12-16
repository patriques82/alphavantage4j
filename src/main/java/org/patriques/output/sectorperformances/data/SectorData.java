package org.patriques.output.sectorperformances.data;

/**
 * Representation of a json object, i.e:
 * "Rank A: Real-Time Performance": {
 *  "Telecommunication Services": "1.52%",
 *  "Health Care": "1.11%",
 *  "Energy": "0.85%",
 *  "Financials": "0.59%",
 *  "Consumer Discretionary": "0.43%",
 *  "Information Technology": "0.42%",
 *  "Industrials": "0.40%",
 *  "Utilities": "0.32%",
 *  "Real Estate": "0.25%",
 *  "Consumer Staples": "0.22%",
 *  "Materials": "-0.02%"
 * }
 */
public class SectorData {

  private final String key;
  private final double information_technology;
  private final double health_care;
  private final double consumer_staples;
  private final double real_estate;
  private final double materials;
  private final double consumer_discretionary;
  private final double energy;
  private final double financials;
  private final double industrials;
  private final double utilities;
  private final double telecommunication_services;

  public SectorData(String key,
                    double information_technology,
                    double health_care,
                    double consumer_staples,
                    double real_estate,
                    double materials,
                    double consumer_discretionary,
                    double energy,
                    double financials,
                    double industrials,
                    double utilities,
                    double telecommunication_services) {
    this.key = key;
    this.information_technology = information_technology;
    this.health_care = health_care;
    this.consumer_staples = consumer_staples;
    this.real_estate = real_estate;
    this.materials = materials;
    this.consumer_discretionary = consumer_discretionary;
    this.energy = energy;
    this.financials = financials;
    this.industrials = industrials;
    this.utilities = utilities;
    this.telecommunication_services = telecommunication_services;
  }

  public String getKey() {
    return key;
  }

  public double getInformationTechnology() {
    return information_technology;
  }

  public double getHealthCare() {
    return health_care;
  }

  public double getConsumerStaples() {
    return consumer_staples;
  }

  public double getRealEstate() {
    return real_estate;
  }

  public double getMaterials() {
    return materials;
  }

  public double getConsumerDiscretionary() {
    return consumer_discretionary;
  }

  public double getEnergy() {
    return energy;
  }

  public double getFinancials() {
    return financials;
  }

  public double getIndustrials() {
    return industrials;
  }

  public double getUtilities() {
    return utilities;
  }

  public double getTelecommunicationServices() {
    return telecommunication_services;
  }
}
