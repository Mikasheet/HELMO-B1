# BASE
# Exercice 1 - Additionner 123456 et 987654 et afficher le résultat
.text
BASE1:
	li $t0, 123456
	li $t1, 987654
	add $a0, $t0, $t1
	jal PR_INT
	
EXIT: 
	li $v0, 10
	syscall
	
PR_INT:
	li $v0, 1
	syscall
	li $a0, '\n'
	li $v0, 11
	syscall
	jr $ra
	