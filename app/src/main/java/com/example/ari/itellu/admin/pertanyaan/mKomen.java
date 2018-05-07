package com.example.ari.itellu.admin.pertanyaan;

import java.io.Serializable;

/**
 * Created by Tavs on 08/05/2018.
 */

public class mKomen implements Serializable {
    public String name;
    public String email_asker;
    public String comment;

    public mKomen(String name, String email_asker, String comment) {
        this.name = name;
        this.email_asker = email_asker;
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail_asker() {
        return email_asker;
    }

    public void setEmail_asker(String email_asker) {
        this.email_asker = email_asker;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public mKomen() {
    }
}
