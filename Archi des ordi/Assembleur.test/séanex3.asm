.data
TEXT: 	.asciiz  "LA PROGRAMMATION EN ASSEMBLEUR EST UN ART"
CHAR:	.byte 	'A'

.text
	la $t0, TEXT 	# adresse de début du texte
	lbu $t1, CHAR	# caractère recherche
LOOP:	lbu $t2, 0($t0) # on charge le caractère 
	beqz $t2, EXIT	# si c'est le caractère '\0', on a fini
	bne $t2, $t1, NEXT # si le caractère courant n'est pas celui recherché on passe
	addi $t3, $t3, 1  # on incrémente le compteur 
	
NEXT: addi $t0, $t0, 1 # on passe au caractère suivant 
	j LOOP		# itération suivante
	
EXIT: 	move $a0, $t3	# on met le nb d'occurences dans $a0
	jal PR_INT	# print ()
 	li $v0, 10 	# Fin du programme
	syscall
	
PR_INT: 
	li $v0, 1  			#appel système print integer => 1
	syscall
	li $a0, '\n'
	li $v0, 11		# appel systeme print character => 11
	syscall
	jr $ra