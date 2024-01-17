package raf.sk.sk_gym_service.authorization.perm;

public enum Permissions {

    ALL_USER_DATA_ACCESS("Permission used by admins and intra service communication."),


    PERSONAL_USER_DATA_ACCESS("Permission used by clients and gym managers to access and change their own personal data."),

    GYM_DATA_EDIT("Permission used by gym managers to create gyms they manage and edit gyms data.");


    public final String description;
    Permissions(String description) {
        this.description = description;
    }



}
