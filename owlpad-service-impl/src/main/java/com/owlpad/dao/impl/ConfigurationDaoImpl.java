package com.owlpad.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.owlpad.dao.ConfigurationDao;
import com.owlpad.dao.repository.ConfigJPARepository;
import com.owlpad.service.model.Configuration;

/**
 * Default implementation for {@link ConfigurationDao} using Spring/JPA data
 * repository.
 *
 * @author Jay Paulynice
 *
 */
@Repository
public class ConfigurationDaoImpl implements ConfigurationDao {
    private final ConfigJPARepository repository;

    /**
     * @param repository
     *            spring/jpa repository
     */
    @Autowired
    public ConfigurationDaoImpl(final ConfigJPARepository repository) {
        this.repository = repository;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.owlpad.dao.ConfigurationDao#getConfiguration()
     */
    @Override
    public Configuration getConfiguration() {
        final List<Configuration> configs = repository.findAll();

        if (configs != null && configs.size() > 0) {
            return configs.get(0);
        }
        return null;
    }
}
