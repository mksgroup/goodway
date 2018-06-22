package mksgroup.goodway.model.support;

import java.util.List;

public class JoinMessage {
    private List codes;
    private List arguments;
    /**
     * Get value of codes.
     * @return the codes
     */
    public List getCodes() {
        return codes;
    }
    /**
     * Get value of arguments.
     * @return the arguments
     */
    public List getArguments() {
        return arguments;
    }
    /**
     * Set the value for codes.
     * @param codes the codes to set
     */
    public void setCodes(List codes) {
        this.codes = codes;
    }
    /**
     * Set the value for arguments.
     * @param arguments the arguments to set
     */
    public void setArguments(List arguments) {
        this.arguments = arguments;
    }
}
