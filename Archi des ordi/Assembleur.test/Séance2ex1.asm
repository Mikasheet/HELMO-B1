.data 
N:	.word	10
M:	.word	3
RES: 	.word 	0:10	# espace mémoire pour stocker les résultats 

.text
	lw $t0, N
	lw $t1, M
	move $t2, $t1
	la $t3, RES
	
LOOP: 	blez $t0, EXIT
	sw $t1, 0($t3)	# on stocke le mutltiple courant dans RES
	add $t1, $t1 ,$t2
	subi $t0, $t0, 1
	addi $t3, $t3, 4	# on avance d'une position dans RES
	j LOOP
	
EXIT: 	la $a0, RES
	lw $a1, N
	jal PR_TAB_INT
	li $v0, 10 	#Fin du programme
	syscall
	
# PR_INT($a0)
# $a0 : adresse de début du tableau
# $a1 : nombre d'éléments
PR_TAB_INT:
	move $t4, $t0
P_LOOP: blez $a1, E_PRINT	# si compteur <= 0 -, on a fini
	lw $a0, 0($t4)		# on charge l'élément courant 
	li $v0, 1
	syscall
	li $a0, '\n'		# On affiche le caractère de retour à la ligne
	li $v0, 11		# appel systeme print character => 11
	syscall
	subi $a1, $a1, 1	# On décrémente le compteur
	addi $t4, $t4, 4	# On avance dans le tableau
E_PRINT:
	jr $ra			# Fin du programme