.data
TAB:	.word 	1, 2, 3, 4, 5
LEN: 	.word 5 
RES:	.word 0:5
.text	
	la $t0, TAB
	lw $t1, LEN
	li $t0, 0
	la $t3, RES
	
LOOP: 	blez $t1, EXIT 		# si $ti <= 0 -> exit
	lw $t2, 0($t0)		#$t2 = valeur del'élément courant
	add $a0, $a0, $t2 	#$a0 = $a0 + $t2
	sw $a0, 0($t3)		#on stocke la somme partielle (â0) dans RES
	subi $t1, $t1, 1
	addi $t0, $t0, 4	#$t0 = $t0 + 4  -> case suivante dans RES
	addi $t3, $t3, 4	#$t3 = $t3 + 4  -> case suivante dans RES
	j LOOP			#itération suivante
	
# PR_TAB_INT ($a0, $a1) -> none
# $a0 : adresse de début du tableau de INT32
# $a1 : nombre d'éléments
PR_TAB_INT:
	move $t0, $a0		# $t0 = adresse courante dans le tableau
LTAB: 	blez $a1, END_TAB	# compteur <= 0 -> fini
	lw $a0, 0($t0)		# $a0 = valeur de l'élément courant de RES
	li $v0, 1  		#appel système print integer => 1
	syscall
	li $a0, '\n'   		# 
	li $v0, 11		# appel systeme print character => 11
	syscall
	addi $a1, $a1, 1	# on décrémente le compteur	
	addi $t0, $t0, 4	# on avance d'un élément dans le tableau
	j LTAB
END_TAB:	jr $ra		# return 

EXIT:	li $v0, 10 			#appel système exit => 10
	syscall
	
#p PR_INT($a0) -> none
PR_INT: 
	li $v0, 1  			#appel système print integer => 1
	syscall
	li $a0, '\n'
	li $v0, 11		# appel systeme print character => 11
	syscall
	jr $ra
