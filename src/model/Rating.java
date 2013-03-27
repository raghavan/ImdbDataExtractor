package model;

public class Rating {

	private String voteRatio;
	private Integer voteCount;
	private float rating;
	private String movieName;
	private Integer year;

	public String getVoteRatio() {
		return voteRatio;
	}
	public void setVoteRatio(String voteRatio) {
		this.voteRatio = voteRatio;
	}
	public Integer getVoteCount() {
		return voteCount;
	}
	public void setVoteCount(Integer voteCount) {
		this.voteCount = voteCount;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	@Override
	public String toString() {
		return "Rating [voteRatio=" + voteRatio + ", voteCount=" + voteCount + ", rating=" + rating + ", movieName="
				+ movieName + ", year=" + year + "]";
	}

	
	
}
