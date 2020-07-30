package com.Elaineee.project.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "geocontinents", schema = "travel", catalog = "")
public class GeocontinentsEntity {
    private String continentCode;
    private String continentName;
    private Integer geoNameId;

    @Id
    @Column(name = "ContinentCode")
    public String getContinentCode() {
        return continentCode;
    }

    public void setContinentCode(String continentCode) {
        this.continentCode = continentCode;
    }

    @Basic
    @Column(name = "ContinentName")
    public String getContinentName() {
        return continentName;
    }

    public void setContinentName(String continentName) {
        this.continentName = continentName;
    }

    @Basic
    @Column(name = "GeoNameId")
    public Integer getGeoNameId() {
        return geoNameId;
    }

    public void setGeoNameId(Integer geoNameId) {
        this.geoNameId = geoNameId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeocontinentsEntity that = (GeocontinentsEntity) o;
        return Objects.equals(continentCode, that.continentCode) &&
                Objects.equals(continentName, that.continentName) &&
                Objects.equals(geoNameId, that.geoNameId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(continentCode, continentName, geoNameId);
    }
}
