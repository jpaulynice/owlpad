package com.owlpad.service.impl.configuration;

import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.owlpad.dao.ConfigurationDao;
import com.owlpad.domain.configuration.ConfigurationResponse;
import com.owlpad.domain.exception.NoConfigFoundException;
import com.owlpad.service.configuration.ConfigurationService;
import com.owlpad.service.mapper.ConfigMapper;
import com.owlpad.service.model.Configuration;

/**
 * Default implementation for {@link ConfigurationService}
 *
 * @author Jay Paulynice
 *
 */
@Service
public class ConfigurationServiceImpl implements ConfigurationService {
    private final ConfigurationDao dao;

    /**
     * @param dao
     *            the data access object layer
     */
    @Autowired
    public ConfigurationServiceImpl(final ConfigurationDao dao) {
        this.dao = dao;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.owlpad.service.configuration.ConfigurationService#getConfiguration()
     */
    @Override
    public Response getConfiguration() {
        final ConfigurationResponse res = new ConfigurationResponse();
        final Configuration c = dao.getConfiguration();
        if (c == null) {
            throw new NoConfigFoundException("no configuration found.");
        }
        res.setConfiguration(ConfigMapper.map(c));

        final GenericEntity<ConfigurationResponse> entity = new GenericEntity<ConfigurationResponse>(
                res) {
        };
        return Response.ok(entity).build();
    }
}
