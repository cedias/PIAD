package bases;

public interface Base {


	void addReview(
			String pid, String ptitle, String uid, int help,
			int nbhelp, float score,String timestamp, String summary, String text);

	int size();
}
