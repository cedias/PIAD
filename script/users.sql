 CREATE TABLE IF NOT EXISTS users
	 (
	    user_id VARCHAR(25),
	    username VARCHAR(50),
	    trust_score DOUBLE DEFAULT 1.0 ,
	    PRIMARY KEY(user_id),
	    INDEX IndTrust (trust_score),
	    INDEX IndUname (username)
	 );
