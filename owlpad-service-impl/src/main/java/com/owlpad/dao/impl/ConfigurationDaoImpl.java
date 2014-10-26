package com.owlpad.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.owlpad.dao.ConfigurationDao;
import com.owlpad.dao.repository.ConfigJPARepository;
import com.owlpad.service.model.Configuration;

/**
 *
 * @author Jay Paulynice
 *
 */
@Repository
public class ConfigurationDaoImpl implements ConfigurationDao {
    private final ConfigJPARepository configJPARepository;

    @Autowired
    public ConfigurationDaoImpl(final ConfigJPARepository configJPARepository) {
        this.configJPARepository = configJPARepository;
        ;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.owlpad.dao.ConfigurationDao#getConfiguration()
     */
    @Override
    public Configuration getConfiguration() {
        final List<Configuration> configs = configJPARepository.findAll();

        if (configs != null && configs.size() > 0) {
            return configs.get(0);
        }
        return null;
    }
}
