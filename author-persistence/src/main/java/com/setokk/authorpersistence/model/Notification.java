package com.setokk.authorpersistence.model;

import java.time.LocalDateTime;

public record Notification(String message, LocalDateTime timestamp) {

}
