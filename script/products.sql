CREATE TABLE IF NOT EXISTS products
	 (
	    product_id VARCHAR(20),
	    product_name TEXT,
	    nb_bursts INT DEFAULT 0,
	    reliability_score DOUBLE DEFAULT 1.0,
	    PRIMARY KEY(product_id),
	    INDEX IndRel (reliability_score)
	 );
