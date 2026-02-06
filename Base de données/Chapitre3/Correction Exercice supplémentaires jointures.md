**Correction Exercice supplémentaires en BD**



**ex1**

\*\*SELECT l.titre\*\*

	\*\*FROM livre l\*\*

	\*\*join livre l1 on l1.numcategorie = l.numcategorie\*\*

	\*\*where l1.isbn = '2-253-05575-1';\*\*






**ex2**

\*\*INSERT INTO catemprunteur (numcategorie,designation,cotisation) VALUES (6,'Enseignant',90)\*\*



	\*\*SELECT a.designation\*\*

	\*\*FROM catemprunteur a\*\*

	\*\*LEFT join emprunteur b  ON b.catemprunteur = a.numcategorie\*\*

	\*\*WHERE b.catemprunteur is nulle\*\*


**x3**

\*\*SELECT a.titre, c.nom, e.nom\*\*

	\*\*FROM livre a\*\*

	\*\*LEFT JOIN critique b ON b.numlivre = a.numlivre\*\*

	\*\*JOIN categorie c ON c.numcategorie = a.numcategorie\*\*

	\*\*JOIN redaction d ON d.numlivre = a.numlivre\*\*

	\*\*JOIN auteur e ON e.numauteur = d.numauteur\*\*

	\*\*WHERE b.numlivre is null\*\*


**ex4**

\*\*SELECT distinct emprunteur.nom, emprunteur.prenom\*\*

	\*\*FROM critique\*\*

	\*\*JOIN critique c1 on critique.numemprunteur = c1.numemprunteur\*\*

	\*\*JOIN emprunteur on emprunteur.numemprunteur = critique.numemprunteur\*\*

	\*\*join livre on livre.numlivre = critique.numlivre\*\*

	




**ex5**

\*\*select em.nom || ' ' || em.prenom as nom\\\_Prenom\*\*

	\*\*from emprunteur em\*\*

	\*\*left join location lo on lo.numemprunteur = em.numemprunteur\*\*

	\*\*where lo.numemprunteur is NULL\*\*


