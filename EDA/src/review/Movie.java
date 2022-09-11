package review;

public class Movie implements Comparable<Movie>{
	
	private String title;
	private int id, year;
	
	public Movie(String title, int id, int year) {
		this.title = title;
		this.id = id;
		this.year = year;
	}
	
	@Override
	public String toString() {
		return id + ", " + title + ", " + year;
	}

	@Override
	public int compareTo(Movie o) {
		return Integer.compare(id, o.getId());
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

}
