package document.uploadfile;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by jongzazaal on 19/3/2560.
 */
@ConfigurationProperties("storage")
public class StorageProperties {

    /**
     * Folder location for storing files
     */
    private String location = "upload-dir";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}