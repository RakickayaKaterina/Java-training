
public class Passenger {
	private long idPassenger;
	private String name;

	public Passenger(long idPassenger, String name) {
		super();
		this.idPassenger = idPassenger;
		this.name = name;
	}

	public long getIdPassenger() {
		return idPassenger;
	}

	public void setIdPassenger(long idPassenger) {
		this.idPassenger = idPassenger;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Passenger other = (Passenger) obj;
		if (idPassenger != other.idPassenger)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
