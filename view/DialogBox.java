package view;

/**
 * Defines the elements and viewing mechanism for a dialog box
 */
public interface DialogBox {
    /**
     * Sets the title for the dialog box
     * @param text dialog box title
     */
    public void setTitle(String text);
    
    /**
     * Sets the header for the dialog box
     * @param text dialog box text
     */
    public void setHeaderText(String text);
    
    /**
     * Displays the dialog box on the main thread
     */
    public void show();

}
