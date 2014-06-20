package com.bpanalytics.tasksmanager.persistence.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.bpanalytics.tasksmanager.framework.DateDeserializer;
import com.bpanalytics.tasksmanager.framework.DateSerializer;


@Entity
@NamedQuery(name="Task.findAll", query="SELECT t FROM Task t")
public class Task implements Serializable {

	private static final long serialVersionUID = 3662304816540775609L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String description;

	private boolean done;

	@JsonSerialize(using=DateSerializer.class)
	@JsonDeserialize(using=DateDeserializer.class)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DUE_DATE")
	private Date dueDate;

	@Column(name="NOTIFY_BY_EMAIL")
	private boolean notifyByEmail;

	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	public Task() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isDone() {
		return this.done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public Date getDueDate() {
		return this.dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public boolean isNotifyByEmail() {
		return this.notifyByEmail;
	}

	public void setNotifyByEmail(boolean notifyByEmail) {
		this.notifyByEmail = notifyByEmail;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}