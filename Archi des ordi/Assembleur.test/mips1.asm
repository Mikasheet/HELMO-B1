.data
TEXTE: 			.ascii "HELMO"
LONGUEUR:		.word 5


.text
	la $s0, TEXTE					# $t0  = adresse de debut de chaine 
	lw $t1, LONGUEUR				#$t1 = le nombre de caractères
	
LOOP: 	blez $t1, EXIT   				#si $t1 <= 0 -> exit 				blez = si $t1 inférieur ou egal a 0 = exit 
	lbu $a0, 0($t0)					#si $t0 = code ascii du caractères courant	u = unsigned    a = argument	après la virgule signifie ma position où la valeur est renvoyée
	jal PR_INT 					#Affichage
	subi $t1, $t1, 1 				#$t1  = $t1 - 1					i = imediate  sub = soustraire
	addi $t0, $t0, 1 				#$t0 = 	$t0 + 1					add = adition  i = imediate
	j LOOP						#itération suivante
	
EXIT:	li $v0, 10		# appel systeme exit =>
	syscall


PR_INT:
	li $v0, 1		# appel systeme print integer => 1
	syscall
	li $a0, '\n'
	li $v0, 11		# appel systeme print character => 11
	syscall
	jr $ra
