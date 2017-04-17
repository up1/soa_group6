package com.teamsmokeweed.model.check.unique.username;

/**
 * Created by jongzazaal on 14/4/2560.
 */
public class CheckUniqueUsernameResponse {
    boolean isUnique;

    public CheckUniqueUsernameResponse(boolean isUnique) {
        this.isUnique = isUnique;
    }

    public CheckUniqueUsernameResponse() {
    }

    public boolean isUnique() {
        return isUnique;
    }

    public void setUnique(boolean unique) {
        isUnique = unique;
    }
}
