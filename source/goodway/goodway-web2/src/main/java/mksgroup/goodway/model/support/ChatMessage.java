package mksgroup.goodway.model.support;

public class ChatMessage {

    private String name;
    private String content;
    /**
     * Get value of name.
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * Get value of content.
     * @return the content
     */
    public String getContent() {
        return content;
    }
    /**
     * Set the value for name.
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Set the value for content.
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

}