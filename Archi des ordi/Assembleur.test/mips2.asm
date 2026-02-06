.data
TEXTE: 	.asciiz "HELMO"



.text
	la $s0, TEXTE					# $t0  = adresse de debut de chaine 
	
LOOP: 	lbu $a0, 0($t0)					
	beqz $a0, EXIT 				#si $t0 = code ascii du caractères courant	u = unsigned    a = argument	après la virgule signifie ma position où la valeur est renvoyée
	jal PR_INT 					#Affichage
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
