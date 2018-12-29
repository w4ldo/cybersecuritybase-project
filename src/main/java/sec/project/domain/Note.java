package sec.project.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Note extends AbstractPersistable<Long> {

    private String title;
    private String content;

    @ManyToOne
    private Account account;

    public Note() {
        super();
    }

    public Note(String title, String content) {
        this();
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void seTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

    public String toString() {
        return this.getTitle() + " " + this.getContent();
    }

}
