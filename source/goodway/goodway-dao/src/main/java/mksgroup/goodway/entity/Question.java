/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mksgroup.goodway.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ThachLN
 */
@Entity
@Table(name = "goodway_question")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Question.findAll", query = "SELECT q FROM Question q")
    , @NamedQuery(name = "Question.findById", query = "SELECT q FROM Question q WHERE q.id = :id")
    , @NamedQuery(name = "Question.findByCategory", query = "SELECT q FROM Question q WHERE q.category = :category")
    , @NamedQuery(name = "Question.findByQuestion", query = "SELECT q FROM Question q WHERE q.question = :question")
    , @NamedQuery(name = "Question.findByAskPerson", query = "SELECT q FROM Question q WHERE q.askPerson = :askPerson")
    , @NamedQuery(name = "Question.findByAskDate", query = "SELECT q FROM Question q WHERE q.askDate = :askDate")
    , @NamedQuery(name = "Question.findByAnswer", query = "SELECT q FROM Question q WHERE q.answer = :answer")
    , @NamedQuery(name = "Question.findByAnswerPerson", query = "SELECT q FROM Question q WHERE q.answerPerson = :answerPerson")
    , @NamedQuery(name = "Question.findByAnswerDate", query = "SELECT q FROM Question q WHERE q.answerDate = :answerDate")
    , @NamedQuery(name = "Question.findByStatus", query = "SELECT q FROM Question q WHERE q.status = :status")
    , @NamedQuery(name = "Question.findByCreated", query = "SELECT q FROM Question q WHERE q.created = :created")
    , @NamedQuery(name = "Question.findByCreatedbyUsername", query = "SELECT q FROM Question q WHERE q.createdbyUsername = :createdbyUsername")
    , @NamedQuery(name = "Question.findByLastmodified", query = "SELECT q FROM Question q WHERE q.lastmodified = :lastmodified")
    , @NamedQuery(name = "Question.findByLastmodifiedbyUsername", query = "SELECT q FROM Question q WHERE q.lastmodifiedbyUsername = :lastmodifiedbyUsername")})
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "category", length = 100)
    private String category;
    @Basic(optional = false)
    @Column(name = "question", nullable = false, length = 1000)
    private String question;
    @Column(name = "ask_person", length = 100)
    private String askPerson;
    @Column(name = "ask_date")
    @Temporal(TemporalType.DATE)
    private Date askDate;
    @Column(name = "answer", length = 1000)
    private String answer;
    @Column(name = "answer_person", length = 100)
    private String answerPerson;
    @Column(name = "answer_date")
    @Temporal(TemporalType.DATE)
    private Date answerDate;
    @Column(name = "status")
    private Boolean status;
    @Basic(optional = false)
    @Column(name = "created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Basic(optional = false)
    @Column(name = "createdby_username", nullable = false, length = 50)
    private String createdbyUsername;
    @Column(name = "lastmodified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastmodified;
    @Column(name = "lastmodifiedby_username", length = 50)
    private String lastmodifiedbyUsername;

    public Question() {
    }

    public Question(Integer id) {
        this.id = id;
    }

    public Question(Integer id, String question, Date created, String createdbyUsername) {
        this.id = id;
        this.question = question;
        this.created = created;
        this.createdbyUsername = createdbyUsername;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAskPerson() {
        return askPerson;
    }

    public void setAskPerson(String askPerson) {
        this.askPerson = askPerson;
    }

    public Date getAskDate() {
        return askDate;
    }

    public void setAskDate(Date askDate) {
        this.askDate = askDate;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswerPerson() {
        return answerPerson;
    }

    public void setAnswerPerson(String answerPerson) {
        this.answerPerson = answerPerson;
    }

    public Date getAnswerDate() {
        return answerDate;
    }

    public void setAnswerDate(Date answerDate) {
        this.answerDate = answerDate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getCreatedbyUsername() {
        return createdbyUsername;
    }

    public void setCreatedbyUsername(String createdbyUsername) {
        this.createdbyUsername = createdbyUsername;
    }

    public Date getLastmodified() {
        return lastmodified;
    }

    public void setLastmodified(Date lastmodified) {
        this.lastmodified = lastmodified;
    }

    public String getLastmodifiedbyUsername() {
        return lastmodifiedbyUsername;
    }

    public void setLastmodifiedbyUsername(String lastmodifiedbyUsername) {
        this.lastmodifiedbyUsername = lastmodifiedbyUsername;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Question)) {
            return false;
        }
        Question other = (Question) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mksgroup.goodway.entity.Question[ id=" + id + " ]";
    }
    
}
