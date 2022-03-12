package com.company.blog.enums;


public enum UserStatusEnum {
    PENDING(1L), ACTIVE(2L), LOCKED(3L), DELETE(4L);

    private Long statusId;

    UserStatusEnum(Long statusId) {
        this.statusId = statusId;
    }

    public Long getStatusId() {
        return statusId;
    }
}
