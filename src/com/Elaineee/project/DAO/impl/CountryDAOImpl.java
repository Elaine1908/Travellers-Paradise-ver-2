package com.Elaineee.project.DAO.impl;

import com.Elaineee.project.DAO.DAO;
import com.Elaineee.project.entity.GeocountriesRegionsEntity;
import java.util.List;

public class CountryDAOImpl extends DAO<GeocountriesRegionsEntity> {

    public List<GeocountriesRegionsEntity> getAllCountry(){
        String sql = "SELECT ISO,Country_RegionName,Area FROM geocountries_regions";
        return getList(sql);
    }

}
