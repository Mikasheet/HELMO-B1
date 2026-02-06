.data
TAB:	.word 	1, 2, 3, 4, 5
LEN: 	.word 5 
.text	
	la $t0, TAB
	lw $t1, LEN
	li $t0, 0
	
LOOP: 	beqz $t1, EXIT 		# si $ti <= 0 -> exit
	lh $t2, 0($t2)		#$t2 = valeur del'élément courant
	add $a0, $a0, $t2 	#$t1 = $t1
	subi $t1, $t1, 1
	addi $t0, $t0, 2
	
	j LOOP	



EXIT:	jal PR_INT 			#appel système exit => 10
	syscall
	
#p PR_INT($a0) -> none
PR_INT: 
	li $v0, 1  			#appel système print integer => 1
	syscall
	li $a0, '\n'
	li $v0, 11		# appel systeme print character => 11
	syscall
	jr $ra