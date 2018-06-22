package mksgroup.goodway.model;

import java.util.List;

public class AddressModel {

	private List header;
    private List data;
    /**
    * Get value of header.
    * @return the header
    */
    public List getHeader() {
        return header;
    }
    /**
    * Get value of data.
    * @return the data
    */
    public List getData() {
        return data;
    }
    /**
     * Set the value for header.
     * @param header the header to set
     */
    public void setHeader(List header) {
        this.header = header;
    }
    /**
     * Set the value for data.
     * @param data the data to set
     */
    public void setData(List data) {
        this.data = data;
    }

    /**
     * [Explain the description for this method here].
     * @return
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return String.format("AddressModel [header=%s, data=%s]", header, data);
    }
}
