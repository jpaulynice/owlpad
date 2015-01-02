package com.owlpad.domain.search;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jay Paulynice
 *
 */
@XmlRootElement(name = "searchRequest")
public class SearchRequest {
    private String keyWord;
    private int hitsPerPage;
    private int resultStart;
    private boolean paging;

    /** Default constructor */
    public SearchRequest() {

    }

    /**
     * @return the keyWord
     */
    public String getKeyWord() {
        return keyWord;
    }

    /**
     * @param keyWord
     *            the keyWord to set
     */
    public void setKeyWord(final String keyWord) {
        this.keyWord = keyWord;
    }

    /**
     * @return the resultStart
     */
    public int getResultStart() {
        return resultStart;
    }

    /**
     * @param resultStart
     *            the resultStart to set
     */
    public void setResultStart(final int resultStart) {
        this.resultStart = resultStart;
    }

    /**
     * @return the hitsPerPage
     */
    public int getHitsPerPage() {
        return hitsPerPage;
    }

    /**
     * @param hitsPerPage
     *            the hitsPerPage to set
     */
    public void setHitsPerPage(final int hitsPerPage) {
        this.hitsPerPage = hitsPerPage;
    }

    /**
     * @return the paging
     */
    public boolean isPaging() {
        return paging;
    }

    /**
     * @param paging
     *            the paging to set
     */
    public void setPaging(final boolean paging) {
        this.paging = paging;
    }

}
