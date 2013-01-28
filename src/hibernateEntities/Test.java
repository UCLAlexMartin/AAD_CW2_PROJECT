package hibernateEntities;

import javax.persistence.Entity;

import org.hibernate.envers.Audited;

@Entity
@Audited
public class Test {
	
	private Integer fir;

	public Integer getFir() {
		return fir;
	}

	public void setFir(Integer fir) {
		this.fir = fir;
	}

}
