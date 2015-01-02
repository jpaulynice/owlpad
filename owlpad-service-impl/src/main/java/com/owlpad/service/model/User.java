package com.owlpad.service.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * @author Jay Paulynice
 *
 */
@Entity(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "username")
    private String userName;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Configuration userConfig;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     *            the userName to set
     */
    public void setUserName(final String userName) {
        this.userName = userName;
    }

    /**
     * @return the userConfig
     */
    public Configuration getUserConfig() {
        return userConfig;
    }

    /**
     * @param userConfig
     *            the userConfig to set
     */
    public void setUserConfig(final Configuration userConfig) {
        this.userConfig = userConfig;
    }
}
