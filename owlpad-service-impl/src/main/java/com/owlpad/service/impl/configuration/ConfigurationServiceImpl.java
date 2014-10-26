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
 *
 * @author Jay Paulynice
 *
 */
@Service
public class ConfigurationServiceImpl implements ConfigurationService {
    private final ConfigurationDao configurationDao;

    @Autowired
    public ConfigurationServiceImpl(final ConfigurationDao configurationDao) {
        this.configurationDao = configurationDao;
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
        final Configuration c = configurationDao.getConfiguration();

        if (c == null) {
            throw new NoConfigFoundException("no configuration found.");
        }
        res.setConfiguration(ConfigMapper.mapConfig(c));

        final GenericEntity<ConfigurationResponse> entity = new GenericEntity<ConfigurationResponse>(res) {
        };
        return Response.ok(entity).build();
    }
}
