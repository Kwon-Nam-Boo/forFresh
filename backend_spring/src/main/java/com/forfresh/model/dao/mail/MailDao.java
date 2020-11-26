package com.forfresh.model.dao.mail;

public interface MailDao {

    public boolean send(String subject, String text, String from, String to, String filePath);

}
    