# STRING
# Exercice 1 : afficher les codes ascii des 3 premières lettres d'un mot
.data
TXT:	.ascii	"HELMO"

.text
	la $t0, TXT
	lbu $a0, 0($t0)
	jal PR_INT
	
	lbu $a0, 1($t0)
	jal PR_INT
	
	lbu $a0, 2($t0)
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