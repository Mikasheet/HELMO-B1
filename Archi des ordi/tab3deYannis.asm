 .data

TAB:        .word 1,2,3,4,5
LEN:         .word 5
RES:        .word 0:5


.text
    la $t0, TAB             # i
    lw $t1, LEN             # n
    li $t2, 0             # cpt
    la $t3, RES             # addresse de debut du tableau de resultat
    
    
LOOP:     
    blez $t1, EXIT             # conditions d'arret
    lw $t2, 0($t0)            
    add $a0, $a0, $t2        # $a0 += $t2
    sw $a0, 0($t3)

    subi $t1, $t1, 1            # $t1 -= 1
    addi $t0, $t0, 4            # $t0 += 4
    addi $t3, $t3, 4            # $t3 += 4
    j LOOP                 # iteration suivante
    
    

    
    
EXIT:   
    la $a0, RES
    lw $a1, LEN
    jal PR_TAB_INT
    li $v0, 10            # appel systeme exit
    syscall
    
    # PR_TAB_INT($a0,$a1) -> none
    # $a0 : adresse de debit du tableau de int32
    # $a1 : nombre d'elements
    
PR_TAB_INT:
    move $t0, $a0     # $t0 = addresse courante dans le tableau
    
LTAB:    blez $a1, ENDTAB # si compteur <= 0 alors on finit
    lw $a0, 0($t0)    # $a0 = valeur de l'element courant de RES
    li $v0, 1            # appel systeme print integer -> 1
    syscall
    li $a0, '\n'
    li $v0, 11            # appel systeme print character -> 11
    syscall
    subi $a1, $a1, 1 # decrement le compteur
    addi $t0, $t0, 4 # i++
    j LTAB
ENDTAB: jr $ra # return
