package upload;

/**
 * Uploader: uploads to database.
 * @author charles
 *
 */
public interface Uploader {

	/**
	 * Uploads data string[]
	 * @param data
	 * @return number of time upload was called afterwards.
	 * @throws Exception
	 */
	public int upload(String[] data) throws Exception;
	
	/**
	 * Forces buffer upload
	 * @throws Exception
	 */
	public void flush() throws Exception;
	
	/**
	 * Flush buffer and close upload. Class shouldn't be use afterwards
	 * @throws Exception
	 */
	public void close() throws Exception;
}
