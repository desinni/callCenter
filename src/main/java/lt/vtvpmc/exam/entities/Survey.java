package lt.vtvpmc.exam.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Survey implements Serializable {
	private static final long serialVersionUID = -8940846415840547904L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Temporal(TemporalType.DATE)
	private Date date;
	private int evaluation;
	private int recommendation;

	@JoinColumn(name = "client_id")
	@ManyToOne(optional = true, cascade = { CascadeType.ALL })
	private Client client;

	public Survey() {
	}

	public Survey(Date date, int evaluation, int recommendation, Client client) {
		super();
		this.date = date;
		this.evaluation = evaluation;
		this.recommendation = recommendation;
		this.client = client;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(int evaluation) {
		this.evaluation = evaluation;
	}

	public int getRecommendation() {
		return recommendation;
	}

	public void setRecommendation(int recommendation) {
		this.recommendation = recommendation;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public String toString() {
		return "Survey: " + date + ", evaluation: " + evaluation + ", recommendation: " + recommendation;
	}
	
}
