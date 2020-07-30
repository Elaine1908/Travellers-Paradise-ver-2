package com.Elaineee.project.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "geocountries_regions", schema = "travel", catalog = "")
public class GeocountriesRegionsEntity {
    private String iso;
    private String fipsCountryRegionCode;
    private String iso3;
    private String isoNumeric;
    private String country_RegionName;
    private String capital;
    private Integer geoNameId;
    private String area;
    private Integer population;
    private String continent;
    private String topLevelDomain;
    private String currencyCode;
    private String currencyName;
    private String phoneCountryRegionCode;
    private String languages;
    private String postalCodeFormat;
    private String postalCodeRegex;
    private String neighbours;
    private String countryRegionDescription;

    @Id
    @Column(name = "ISO")
    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    @Basic
    @Column(name = "fipsCountry_RegionCode")
    public String getFipsCountryRegionCode() {
        return fipsCountryRegionCode;
    }

    public void setFipsCountryRegionCode(String fipsCountryRegionCode) {
        this.fipsCountryRegionCode = fipsCountryRegionCode;
    }

    @Basic
    @Column(name = "ISO3")
    public String getIso3() {
        return iso3;
    }

    public void setIso3(String iso3) {
        this.iso3 = iso3;
    }

    @Basic
    @Column(name = "ISONumeric")
    public String getIsoNumeric() {
        return isoNumeric;
    }

    public void setIsoNumeric(String isoNumeric) {
        this.isoNumeric = isoNumeric;
    }

    @Basic
    @Column(name = "Country_RegionName")
    public String getCountry_RegionName() {
        return country_RegionName;
    }

    public void setCountry_RegionName(String countryRegionName) {
        this.country_RegionName = countryRegionName;
    }

    @Basic
    @Column(name = "Capital")
    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    @Basic
    @Column(name = "GeoNameID")
    public Integer getGeoNameId() {
        return geoNameId;
    }

    public void setGeoNameId(Integer geoNameId) {
        this.geoNameId = geoNameId;
    }

    @Basic
    @Column(name = "Area")
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Basic
    @Column(name = "Population")
    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    @Basic
    @Column(name = "Continent")
    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    @Basic
    @Column(name = "TopLevelDomain")
    public String getTopLevelDomain() {
        return topLevelDomain;
    }

    public void setTopLevelDomain(String topLevelDomain) {
        this.topLevelDomain = topLevelDomain;
    }

    @Basic
    @Column(name = "CurrencyCode")
    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    @Basic
    @Column(name = "CurrencyName")
    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    @Basic
    @Column(name = "PhoneCountry_RegionCode")
    public String getPhoneCountryRegionCode() {
        return phoneCountryRegionCode;
    }

    public void setPhoneCountryRegionCode(String phoneCountryRegionCode) {
        this.phoneCountryRegionCode = phoneCountryRegionCode;
    }

    @Basic
    @Column(name = "Languages")
    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    @Basic
    @Column(name = "PostalCodeFormat")
    public String getPostalCodeFormat() {
        return postalCodeFormat;
    }

    public void setPostalCodeFormat(String postalCodeFormat) {
        this.postalCodeFormat = postalCodeFormat;
    }

    @Basic
    @Column(name = "PostalCodeRegex")
    public String getPostalCodeRegex() {
        return postalCodeRegex;
    }

    public void setPostalCodeRegex(String postalCodeRegex) {
        this.postalCodeRegex = postalCodeRegex;
    }

    @Basic
    @Column(name = "Neighbours")
    public String getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(String neighbours) {
        this.neighbours = neighbours;
    }

    @Basic
    @Column(name = "Country_RegionDescription")
    public String getCountryRegionDescription() {
        return countryRegionDescription;
    }

    public void setCountryRegionDescription(String countryRegionDescription) {
        this.countryRegionDescription = countryRegionDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeocountriesRegionsEntity that = (GeocountriesRegionsEntity) o;
        return Objects.equals(iso, that.iso) &&
                Objects.equals(fipsCountryRegionCode, that.fipsCountryRegionCode) &&
                Objects.equals(iso3, that.iso3) &&
                Objects.equals(isoNumeric, that.isoNumeric) &&
                Objects.equals(country_RegionName, that.country_RegionName) &&
                Objects.equals(capital, that.capital) &&
                Objects.equals(geoNameId, that.geoNameId) &&
                Objects.equals(area, that.area) &&
                Objects.equals(population, that.population) &&
                Objects.equals(continent, that.continent) &&
                Objects.equals(topLevelDomain, that.topLevelDomain) &&
                Objects.equals(currencyCode, that.currencyCode) &&
                Objects.equals(currencyName, that.currencyName) &&
                Objects.equals(phoneCountryRegionCode, that.phoneCountryRegionCode) &&
                Objects.equals(languages, that.languages) &&
                Objects.equals(postalCodeFormat, that.postalCodeFormat) &&
                Objects.equals(postalCodeRegex, that.postalCodeRegex) &&
                Objects.equals(neighbours, that.neighbours) &&
                Objects.equals(countryRegionDescription, that.countryRegionDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iso, fipsCountryRegionCode, iso3, isoNumeric, country_RegionName, capital, geoNameId, area, population, continent, topLevelDomain, currencyCode, currencyName, phoneCountryRegionCode, languages, postalCodeFormat, postalCodeRegex, neighbours, countryRegionDescription);
    }
}
