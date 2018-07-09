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
 * @author manng
 */
@Entity
@Table(name = "goodway_quickly_report")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "QuicklyReport.findAll", query = "SELECT g FROM QuicklyReport g")
    , @NamedQuery(name = "QuicklyReport.findById", query = "SELECT g FROM QuicklyReport g WHERE g.id = :id")
    , @NamedQuery(name = "QuicklyReport.findByName", query = "SELECT g FROM QuicklyReport g WHERE g.name = :name")
    , @NamedQuery(name = "QuicklyReport.findByAccount", query = "SELECT g FROM QuicklyReport g WHERE g.account = :account")
    , @NamedQuery(name = "QuicklyReport.findByEmail", query = "SELECT g FROM QuicklyReport g WHERE g.email = :email")
    , @NamedQuery(name = "QuicklyReport.findByImage", query = "SELECT g FROM QuicklyReport g WHERE g.image = :image")
    , @NamedQuery(name = "QuicklyReport.findByCompanyName", query = "SELECT g FROM QuicklyReport g WHERE g.companyName = :companyName")
    , @NamedQuery(name = "QuicklyReport.findByMainUnit", query = "SELECT g FROM QuicklyReport g WHERE g.mainUnit = :mainUnit")
    , @NamedQuery(name = "QuicklyReport.findByAffiliatedUnit", query = "SELECT g FROM QuicklyReport g WHERE g.affiliatedUnit = :affiliatedUnit")
    , @NamedQuery(name = "QuicklyReport.findByStartTime", query = "SELECT g FROM QuicklyReport g WHERE g.startTime = :startTime")
    , @NamedQuery(name = "QuicklyReport.findByEndTime", query = "SELECT g FROM QuicklyReport g WHERE g.endTime = :endTime")
    , @NamedQuery(name = "QuicklyReport.findByDiscinple", query = "SELECT g FROM QuicklyReport g WHERE g.discinple = :discinple")
    , @NamedQuery(name = "QuicklyReport.findByJobPerformence", query = "SELECT g FROM QuicklyReport g WHERE g.jobPerformence = :jobPerformence")
    , @NamedQuery(name = "QuicklyReport.findByActivitiesParticipate", query = "SELECT g FROM QuicklyReport g WHERE g.activitiesParticipate = :activitiesParticipate")
    , @NamedQuery(name = "QuicklyReport.findByInternshipResult", query = "SELECT g FROM QuicklyReport g WHERE g.internshipResult = :internshipResult")
    , @NamedQuery(name = "QuicklyReport.findByInternshipRank", query = "SELECT g FROM QuicklyReport g WHERE g.internshipRank = :internshipRank")
    , @NamedQuery(name = "QuicklyReport.findByWorkingDays", query = "SELECT g FROM QuicklyReport g WHERE g.workingDays = :workingDays")
    , @NamedQuery(name = "QuicklyReport.findByComments", query = "SELECT g FROM QuicklyReport g WHERE g.comments = :comments")
    , @NamedQuery(name = "QuicklyReport.findByUpdated", query = "SELECT g FROM QuicklyReport g WHERE g.updated = :updated")
    , @NamedQuery(name = "QuicklyReport.findByUpdatedUser", query = "SELECT g FROM QuicklyReport g WHERE g.updatedUser = :updatedUser")
    , @NamedQuery(name = "QuicklyReport.findByUpdatedCommands", query = "SELECT g FROM QuicklyReport g WHERE g.updatedCommands = :updatedCommands")
    , @NamedQuery(name = "QuicklyReport.findByCreated", query = "SELECT g FROM QuicklyReport g WHERE g.created = :created")
    , @NamedQuery(name = "QuicklyReport.findByCreatedbyUsername", query = "SELECT g FROM QuicklyReport g WHERE g.createdbyUsername = :createdbyUsername")
    , @NamedQuery(name = "QuicklyReport.findByLastmodified", query = "SELECT g FROM QuicklyReport g WHERE g.lastmodified = :lastmodified")
    , @NamedQuery(name = "QuicklyReport.findByLastmodifiedbyUsername", query = "SELECT g FROM QuicklyReport g WHERE g.lastmodifiedbyUsername = :lastmodifiedbyUsername")
    , @NamedQuery(name = "QuicklyReport.findByLastmodifiedCommands", query = "SELECT g FROM QuicklyReport g WHERE g.lastmodifiedCommands = :lastmodifiedCommands")})
public class QuicklyReport implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name", nullable = false, length = 128)
    private String name;
    @Basic(optional = false)
    @Column(name = "account", nullable = false, length = 64)
    private String account;
    @Basic(optional = false)
    @Column(name = "email", nullable = false, length = 64)
    private String email;
    @Column(name = "image", length = 64)
    private String image;
    @Basic(optional = false)
    @Column(name = "company_name", nullable = false, length = 128)
    private String companyName;
    @Basic(optional = false)
    @Column(name = "main_unit", nullable = false, length = 128)
    private String mainUnit;
    @Basic(optional = false)
    @Column(name = "affiliated_unit", nullable = false, length = 128)
    private String affiliatedUnit;
    @Basic(optional = false)
    @Column(name = "start_time", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date startTime;
    @Basic(optional = false)
    @Column(name = "end_time", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date endTime;
    @Column(name = "discinple")
    private Integer discinple;
    @Column(name = "job_performence")
    private Integer jobPerformence;
    @Column(name = "activities_participate")
    private Integer activitiesParticipate;
    @Column(name = "internship_result")
    private Integer internshipResult;
    @Column(name = "internship_rank", length = 128)
    private String internshipRank;
    @Column(name = "working_days")
    private Integer workingDays;
    @Column(name = "comments", length = 1024)
    private String comments;
    @Column(name = "updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;
    @Column(name = "updated_user", length = 128)
    private String updatedUser;
    @Column(name = "updated_commands", length = 50)
    private String updatedCommands;
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
    @Column(name = "lastmodifiedby_username", length = 128)
    private String lastmodifiedbyUsername;
    @Column(name = "lastmodified_commands", length = 50)
    private String lastmodifiedCommands;

    public QuicklyReport() {
    }

    public QuicklyReport(Integer id) {
        this.id = id;
    }

    public QuicklyReport(Integer id, String name, String account, String email, String companyName, String mainUnit, String affiliatedUnit, Date startTime, Date endTime, Date created, String createdbyUsername) {
        this.id = id;
        this.name = name;
        this.account = account;
        this.email = email;
        this.companyName = companyName;
        this.mainUnit = mainUnit;
        this.affiliatedUnit = affiliatedUnit;
        this.startTime = startTime;
        this.endTime = endTime;
        this.created = created;
        this.createdbyUsername = createdbyUsername;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getMainUnit() {
        return mainUnit;
    }

    public void setMainUnit(String mainUnit) {
        this.mainUnit = mainUnit;
    }

    public String getAffiliatedUnit() {
        return affiliatedUnit;
    }

    public void setAffiliatedUnit(String affiliatedUnit) {
        this.affiliatedUnit = affiliatedUnit;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getDiscinple() {
        return discinple;
    }

    public void setDiscinple(Integer discinple) {
        this.discinple = discinple;
    }

    public Integer getJobPerformence() {
        return jobPerformence;
    }

    public void setJobPerformence(Integer jobPerformence) {
        this.jobPerformence = jobPerformence;
    }

    public Integer getActivitiesParticipate() {
        return activitiesParticipate;
    }

    public void setActivitiesParticipate(Integer activitiesParticipate) {
        this.activitiesParticipate = activitiesParticipate;
    }

    public Integer getInternshipResult() {
        return internshipResult;
    }

    public void setInternshipResult(Integer internshipResult) {
        this.internshipResult = internshipResult;
    }

    public String getInternshipRank() {
        return internshipRank;
    }

    public void setInternshipRank(String internshipRank) {
        this.internshipRank = internshipRank;
    }

    public Integer getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(Integer workingDays) {
        this.workingDays = workingDays;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getUpdatedUser() {
        return updatedUser;
    }

    public void setUpdatedUser(String updatedUser) {
        this.updatedUser = updatedUser;
    }

    public String getUpdatedCommands() {
        return updatedCommands;
    }

    public void setUpdatedCommands(String updatedCommands) {
        this.updatedCommands = updatedCommands;
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

    public String getLastmodifiedCommands() {
        return lastmodifiedCommands;
    }

    public void setLastmodifiedCommands(String lastmodifiedCommands) {
        this.lastmodifiedCommands = lastmodifiedCommands;
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
        if (!(object instanceof QuicklyReport)) {
            return false;
        }
        QuicklyReport other = (QuicklyReport) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return "mksgroups.goodway.entity.QuicklyReport[ id=" + id + " ]";
    }
    
}
