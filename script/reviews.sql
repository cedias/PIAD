CREATE TABLE IF NOT EXISTS reviews
	(
	review_id INT not null AUTO_INCREMENT,
	user_id VARCHAR(25),
	product_id VARCHAR(20),
	score FLOAT(2),
	time DATE,
	helpfullness INT,
	nb_helpfullness INT,
	summary TEXT,
	text LONGTEXT,
	exact_dup_id INT,
	near_dup_id INT,
	cos_simil_ident DOUBLE,
	honesty_score DOUBLE DEFAULT 1.0 ,
	PRIMARY KEY(review_id),
	INDEX IndUser (user_id),
	INDEX IndProd (product_id),
	INDEX IndCossim (cos_simil_ident),
	INDEX IndExDup (exact_dup_id),
	INDEX IndNeDup (near_dup_id),
	INDEX IndHonest (honesty_score)
	)
	;
