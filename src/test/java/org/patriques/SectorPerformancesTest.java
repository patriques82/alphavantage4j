package org.patriques;

import org.junit.Test;
import org.patriques.output.sectorperformances.Sectors;
import org.patriques.output.sectorperformances.data.SectorData;

import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SectorPerformancesTest {

  @Test
  public void sector() {
    String json = "" +
            "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"Information\": \"US Sector Performance (realtime & historical)\",\n" +
            "        \"Last Refreshed\": \"12:54 PM ET 12/06/2017\"\n" +
            "    },\n" +
            "    \"Rank A: Real-Time Performance\": {\n" +
            "        \"Information Technology\": \"6.71%\",\n" +
            "        \"Utilities\": \"0.36%\",\n" +
            "        \"Industrials\": \"0.29%\",\n" +
            "        \"Financials\": \"0.07%\",\n" +
            "        \"Consumer Staples\": \"-0.13%\",\n" +
            "        \"Real Estate\": \"-0.24%\",\n" +
            "        \"Health Care\": \"-0.48%\",\n" +
            "        \"Materials\": \"-0.52%\",\n" +
            "        \"Consumer Discretionary\": \"-0.59%\",\n" +
            "        \"Telecommunication Services\": \"-0.89%\",\n" +
            "        \"Energy\": \"-1.19%\"\n" +
            "    },\n" +
            "    \"Rank I: 5 Year Performance\": {\n" +
            "        \"Information Technology\": \"133.52%\"\n" +
            "    },\n" +
            "    \"Rank J: 10 Year Performance\": {\n" +
            "        \"Consumer Discretionary\": \"185.01%\",\n" +
            "        \"Information Technology\": \"169.73%\",\n" +
            "        \"Health Care\": \"126.38%\",\n" +
            "        \"Consumer Staples\": \"92.89%\",\n" +
            "        \"Industrials\": \"76.14%\",\n" +
            "        \"Materials\": \"45.59%\",\n" +
            "        \"Utilities\": \"27.15%\",\n" +
            "        \"Financials\": \"14.26%\",\n" +
            "        \"Telecommunication Services\": \"-3.16%\",\n" +
            "        \"Energy\": \"-8.67%\"\n" +
            "    }\n" +
            "}";
    SectorPerformances sectorPerformances = new SectorPerformances(apiParameters -> json);
    Sectors resp = sectorPerformances.sector();

    Map<String, String> metaData = resp.getMetaData();
    assertThat(metaData.get("Information"), is(equalTo("US Sector Performance (realtime & historical)")));
    assertThat(metaData.get("Last Refreshed"), is(equalTo("12:54 PM ET 12/06/2017")));

    List<SectorData> sectors = resp.getSectors();
    assertThat(sectors.size(), is(equalTo(3)));

    SectorData data = sectors.get(0);
    assertThat(data.getKey(), equalTo("Rank A: Real-Time Performance"));
    assertThat(data.getInformationTechnology(), equalTo(6.71d));
    assertThat(data.getUtilities(), equalTo(0.36d));
    assertThat(data.getIndustrials(), equalTo(0.29d));
    assertThat(data.getFinancials(), equalTo(0.07d));
    assertThat(data.getConsumerStaples(), equalTo(-0.13d));
    assertThat(data.getRealEstate(), equalTo(-0.24d));
    assertThat(data.getHealthCare(), equalTo(-0.48d));
    assertThat(data.getMaterials(), equalTo(-0.52d));
    assertThat(data.getConsumerDiscretionary(), equalTo(-0.59d));
    assertThat(data.getTelecommunicationServices(), equalTo(-0.89d));
    assertThat(data.getEnergy(), equalTo(-1.19d));

    data = sectors.get(1);
    assertThat(data.getKey(), equalTo("Rank I: 5 Year Performance"));
    assertThat(data.getInformationTechnology(), equalTo(133.52d));
    assertThat(data.getUtilities(), equalTo(0d));
    assertThat(data.getIndustrials(), equalTo(0d));
    assertThat(data.getFinancials(), equalTo(0d));
    assertThat(data.getConsumerStaples(), equalTo(0d));
    assertThat(data.getRealEstate(), equalTo(0d));
    assertThat(data.getHealthCare(), equalTo(0d));
    assertThat(data.getMaterials(), equalTo(0d));
    assertThat(data.getConsumerDiscretionary(), equalTo(0d));
    assertThat(data.getTelecommunicationServices(), equalTo(0d));
    assertThat(data.getEnergy(), equalTo(0d));

    data = sectors.get(2);
    assertThat(data.getKey(), equalTo("Rank J: 10 Year Performance"));
    assertThat(data.getInformationTechnology(), equalTo(169.73d));
    assertThat(data.getUtilities(), equalTo(27.15d));
    assertThat(data.getIndustrials(), equalTo(76.14d));
    assertThat(data.getFinancials(), equalTo(14.26d));
    assertThat(data.getConsumerStaples(), equalTo(92.89d));
    assertThat(data.getRealEstate(), equalTo(0d));
    assertThat(data.getHealthCare(), equalTo(126.38d));
    assertThat(data.getMaterials(), equalTo(45.59d));
    assertThat(data.getConsumerDiscretionary(), equalTo(185.01d));
    assertThat(data.getTelecommunicationServices(), equalTo(-3.16d));
    assertThat(data.getEnergy(), equalTo(-8.67d));
  }

}
